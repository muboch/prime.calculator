package prime.calculator.rest;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import prime.calculator.model.UserEntity;
import prime.calculator.model.UserRepository;

@RestController
@RequestMapping("/users")
public class UserRestController {

  @Autowired
  private UserRepository userRepository;

  @RequestMapping(value = "/current", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
  public UserEntity getUser(Principal user) {
    Optional<UserEntity> userEntity = userRepository.findByName(user.getName());
    return userEntity.orElse(null);
  }
}
