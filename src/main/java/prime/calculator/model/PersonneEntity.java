package prime.calculator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="PERSONNELS")
public class PersonneEntity {
  
	@Id
	@GeneratedValue
	@Column(name="PER_PK")
	private Long id;
	
	@Column(name="NOM")
	private String lastName;
	
	@Column(name="PRENOM")
	private String firstName;
	
	@Column(name="ADRESSE")
	private String adresse;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
}
