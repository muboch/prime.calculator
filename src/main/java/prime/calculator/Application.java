/**
 * File Name: Application.java
 * 
 */

package prime.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
