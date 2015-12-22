package prime.calculator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USERS")
public class UserEntity {

  @Id
  @GeneratedValue
  @Column(name = "USERS_PK")
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "PW", nullable = false)
  @JsonIgnore
  // from the hash computation
  @Size(max = 96)
  private String password;
  
  public UserEntity(){
    
  }
  
  public UserEntity(String name, String password) {
    this.name = name;
    this.password = password;
  }


  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }
}
