package prime.calculator.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  // TODO csrf does not work with "grunt serve". Set this to true to disable csrf checks.
  private static final boolean WORKS_WITH_GRUNT_SERVE = true;

  @Autowired
  private AppAuthenticationProvider authenticationProvider;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider);
  }

  @Override
  @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin();
    http.logout();
    http.authorizeRequests().antMatchers("/images/**", "/js/**", "/styles/**","/webjars/**").permitAll();
    http.authorizeRequests().antMatchers("/", "/index.html", "/views/login.html").permitAll();
    http.authorizeRequests().anyRequest().authenticated();

    // TODO check if there is a way to use "grunt serve" with csrf tokens
    if (WORKS_WITH_GRUNT_SERVE) {
      http.csrf().disable();
    } else {
      http.csrf().csrfTokenRepository(csrfTokenRepository());
      http.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
    }

    http.addFilterBefore(accessDeniedInterceptor(), FilterSecurityInterceptor.class);
  }

  private Filter csrfHeaderFilter() {
    return new OncePerRequestFilter() {
      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrf != null) {
          Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
          String token = csrf.getToken();
          if (cookie == null || token != null && !token.equals(cookie.getValue())) {
            cookie = new Cookie("XSRF-TOKEN", token);
            cookie.setPath("/");
            response.addCookie(cookie);
          }
        }
        filterChain.doFilter(request, response);
      }
    };
  }

  /**
   * If the resource is protected the Spring Framework throws an AccessDenied exception which then
   * results in a "redirect to login page" response (302). <br/>
   * This filter catches the exception and creates an "unauthorized" response (401) instead.
   * 
   * @return The filter.
   */
  private Filter accessDeniedInterceptor() {
    return new GenericFilterBean() {
      @Override
      public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException,
          ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        try {
          filterChain.doFilter(request, response);
        } catch (AccessDeniedException accessDeniedException) {
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED, accessDeniedException.getMessage());
        }
      }
    };
  }

  private CsrfTokenRepository csrfTokenRepository() {
    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    repository.setHeaderName("X-XSRF-TOKEN");
    return repository;
  }
}
