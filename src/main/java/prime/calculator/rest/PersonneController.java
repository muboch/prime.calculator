package prime.calculator.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import prime.calculator.model.PersonneEntity;
import prime.calculator.service.UserService;

@RestController
public class PersonneController {
  
  @Autowired
  private UserService userService;
  
  @RequestMapping(value = "/rest/prime/users", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> getUser(){
    List<PersonneEntity> users = userService.searchUsers();
    return ResponseEntity.ok(users);
  }
  @RequestMapping(value = "/rest/prime/users", method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> createUser(@RequestBody PersonneEntity user){
    PersonneEntity newUser = userService.createUser(user);
    return ResponseEntity.ok(newUser);
  }

}
