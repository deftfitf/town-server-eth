package town.townservereth.config;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.town.Town;
import org.web3j.tx.gas.DefaultGasProvider;
import town.townservereth.repository.TownRepository;
import town.townservereth.setting.EthNetworkSetting;
import town.townservereth.setting.TownContractSetting;
import town.townservereth.setting.WalletSetting;

@Slf4j
@Configuration
@EnableConfigurationProperties({
    EthNetworkSetting.class,
    TownContractSetting.class,
    WalletSetting.class
})
public class Web3jConfiguration {

  @Bean
  public Web3j web3j(@NotNull EthNetworkSetting networkSetting) {
    final var web3jService = new HttpService(networkSetting.getUri());
    return Web3j.build(web3jService);
  }

  @Bean
  public Credentials credentials(@NotNull WalletSetting setting) {
    try {
      return WalletUtils.loadCredentials(setting.getPassword(), setting.getWalletFilePath());
    } catch (IOException | CipherException e) {
      log.error("Failed to load wallet credential", e);
      throw new RuntimeException(e);
    }
  }

  @Bean
  public TownRepository townRepository(
      Web3j web3j,
      Credentials credentials,
      TownContractSetting setting
  ) {
    final var townContract = Town.load(setting.getAddress(), web3j, credentials,
        new DefaultGasProvider());
    try {
      townContract.isValid();
    } catch (IOException e) {
      log.error("Actual town contract abi may not correspond to local one. please check.", e);
      throw new RuntimeException(e);
    }

    return new TownRepository(townContract);
  }

}
