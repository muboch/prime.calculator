/**
 * File Name: Application.java
 * 
 */

package prime.calculator;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

/**
 * The main entry point for prime calculator Server
 *
 * @author Marouane
 */
@SpringBootApplication
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
  public DataSource createDataSource(){
	  DataSourceBuilder factory = DataSourceBuilder.create().driverClassName("org.postgresql.Driver")//
			  .url("jdbc:postgresql://localhost:5432/prime")//
			  .username("postgres")//
			  .password("postgres");
	  return factory.build();
  }

}
