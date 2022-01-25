package town.townservereth.setting;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "town.contract")
@Data
@NoArgsConstructor
public class TownContractSetting {

  @NonNull
  private String address;
}
