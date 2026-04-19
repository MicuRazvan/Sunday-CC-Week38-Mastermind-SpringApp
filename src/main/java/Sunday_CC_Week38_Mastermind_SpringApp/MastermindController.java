package Sunday_CC_Week38_Mastermind_SpringApp;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MastermindController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/new-game")
    public String newGame(@RequestParam(defaultValue = "standard") String difficulty, HttpSession session) {
        session.setAttribute("game", new Mastermind(difficulty));
        return "redirect:/game";
    }

    @GetMapping("/game")
    public String game(HttpSession session, Model model) {
        Mastermind game = (Mastermind) session.getAttribute("game");
        if (game == null) return "redirect:/";

        model.addAttribute("game", game);
        model.addAttribute("availableColors", Mastermind.COLORS);
        return "game";
    }

    @PostMapping("/guess")
    public String guess(@RequestParam List<String> colors, HttpSession session) {
        Mastermind game = (Mastermind) session.getAttribute("game");
        if (game != null && !game.isWon()) {
            game.makeGuess(colors);
        }
        return "redirect:/game";
    }
}
