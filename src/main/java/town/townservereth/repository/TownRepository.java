package town.townservereth.repository;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.web3j.town.Town;
import town.townservereth.dto.LandDto;
import town.townservereth.dto.TownDto;

@RequiredArgsConstructor
public class TownRepository {

  @NonNull
  private final Town townContract;

  public CompletableFuture<TownDto> getTownDto() {
    return townContract.getLands()
        .sendAsync()
        .thenApply(result -> {
          final var fields = new LandDto[16][16];
          for (int i = 0; i < result.size(); i++) {
            final var y = i / 16;
            final var x = i % 16;
            fields[y][x] = new LandDto((BigInteger) result.get(i));
          }
          return new TownDto(fields);
        });
  }

}
