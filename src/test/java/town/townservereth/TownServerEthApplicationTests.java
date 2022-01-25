package town.townservereth;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TownServerEthApplicationTests {

  @Autowired
  private TownServerEthApplication townServerEthApplication;

  @Test
  void test() {
    assertThat(townServerEthApplication).isNotNull();
  }

}
