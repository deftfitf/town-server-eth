package town.townservereth.dto;

import java.math.BigInteger;
import lombok.Value;

@Value
public class LandDto {

  BigInteger descriptor;

  public int getType() {
    if (descriptor.equals(BigInteger.ZERO)) {
      return 0;
    }
    return descriptor.mod(BigInteger.valueOf(2L)).intValue() + 1;
  }
}
