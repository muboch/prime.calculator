package prime.calculator.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  /**
   * Finds a user by a given name.
   * 
   * @param name
   * @return user optional
   */
  Optional<UserEntity> findByName(String name);
}