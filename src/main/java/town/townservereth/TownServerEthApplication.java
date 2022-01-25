package town.townservereth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = TownServerEthApplication.class)
public class TownServerEthApplication {

  public static void main(String[] args) {
    SpringApplication.run(TownServerEthApplication.class, args);
  }

}
