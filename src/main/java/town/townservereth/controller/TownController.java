package town.townservereth.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import town.townservereth.repository.TownRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TownController {

  @NonNull
  private final TownRepository townRepository;

  @GetMapping("/")
  public String home(@ModelAttribute("model") ModelMap model) {
    final var townDto = townRepository.getTownDto().join();
    log.info("townDto: {}", townDto);

    model.addAttribute("town", townDto);
    return "home";
  }

}
