package prime.calculator.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import prime.calculator.model.UserEntity;
import prime.calculator.model.UserRepository;

@Service
public class AppAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository accountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) {
      String username = authentication.getName();
      String password = authentication.getCredentials().toString();

      Optional<UserEntity> person = accountRepository.findByName(username);
      if (person.isPresent()) {

        String hashedPw = PasswordHash.computeHash(password);
        if (hashedPw.equals(person.get().getPassword())) {
          return new UsernamePasswordAuthenticationToken(username, "password", null);
        }

        // the client whether the username or password was wrong.
        throw new BadCredentialsException("Wrong password");
      }

      throw new UsernameNotFoundException("Username " + username + " not found");
    }

    @Override
    public boolean supports(Class<?> authentication) {
      return true;
    }

//    private Collection<? extends GrantedAuthority> getGrantedAuthorities(Set<Roles> roles) {
//      return roles.stream().map(Roles::toString).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//    }
  }


