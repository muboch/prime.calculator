package prime.calculator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.calculator.model.PersonneEntity;
import prime.calculator.model.PersonneEntityRepository;


@Component
public class UserService {
  
  @Autowired
  private PersonneEntityRepository personneRepo;
  
  public List<PersonneEntity> searchUsers(){
    List<PersonneEntity> allPer = personneRepo.findAll();
    return allPer;
  }
  
  public PersonneEntity createUser(PersonneEntity user){
    PersonneEntity newUser = new PersonneEntity();
    newUser.setFirstName(user.getFirstName());
    newUser.setLastName(user.getLastName());
    newUser.setAdresse(user.getAdresse());
    personneRepo.save(newUser);
    return user ;
  }

}
