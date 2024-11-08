package project.climbinglog.web;

import java.security.Principal;
import java.util.PrimitiveIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.climbinglog.domain.WorkoutRepository;

@Controller
public class LogController implements ErrorController {

    private static final String PATH = "/error";
    @RequestMapping(PATH)
    public String handleError() {
        return "error";
    }
        
    @Autowired
    private WorkoutRepository workoutRepo;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/workouts")
    public String showAllWorkouts(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("username", username);
        }
        model.addAttribute("workouts", workoutRepo.findAll());
        return "workouts";
    }

}
 