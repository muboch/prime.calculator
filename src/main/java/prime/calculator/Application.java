/**
 * File Name: Application.java
 * 
 */

package prime.calculator;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 * The main entry point for prime calculator Server
 *
 * @author Marouane
 */
@SpringBootApplication
@RestController
public class Application {

  /**
   * The main method being started.
   * 
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public DataSource createDataSource() {
    DBProperties properties = new DBProperties();
    DataSourceBuilder factory = DataSourceBuilder.create() //
        .driverClassName(properties.getDriverClassName()) //
        .url(properties.getUrl()) //
        .username(properties.getUsername()) //
        .password(properties.getPassword());
    return factory.build();
  }

  @RequestMapping("/user")
  public Principal user(Principal user) {
    return user;
  }
  
  // DataSourceBuilder factory = DataSourceBuilder.create().driverClassName("org.postgresql.Driver")//
  // .url("jdbc:postgresql://localhost:5432/prime")//
  // .username("postgres")//
  // .password("postgres");
  // return factory.build();
  // }

}
