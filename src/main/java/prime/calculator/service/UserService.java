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
  
  public List<PersonneEntity> searchPersonnes(){
    List<PersonneEntity> allPer = personneRepo.findAll();
    return allPer;
  }
  
  public PersonneEntity createPersonne(PersonneEntity per){
    PersonneEntity newUser = new PersonneEntity();
    newUser.setFirstName(per.getFirstName());
    newUser.setLastName(per.getLastName());
    newUser.setAdresse(per.getAdresse());
    personneRepo.save(newUser);
    return per ;
  }

}
