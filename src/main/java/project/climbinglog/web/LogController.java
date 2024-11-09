package project.climbinglog.web;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;
import project.climbinglog.domain.AppUser;
import project.climbinglog.domain.AppUserRepository;

@Controller
public class LogController implements ErrorController {

    private static final String PATH = "/error";
    @RequestMapping(PATH)
    public String handleError() {
        return "error";
    }
        
    @Autowired
    private WorkoutRepository workoutRepo;
    @Autowired 
    private AppUserRepository userRepo;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String showAllWorkouts(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("username", username);
        }
        model.addAttribute("workouts", workoutRepo.findAll());
        return "workouts";
    }

    // rest-haku, tulostaa json-muodossa workout-oliot ja siihen kuuluvat käyttäjät ja reitit
    @RequestMapping("workouts")
    public @ResponseBody List<Workout> getWorkoutRest() {
        List<Workout> workouts = (List<Workout>) workoutRepo.findAll();
        return workouts;
    }

    // rest haku käyttäjän kautta
    @GetMapping("/users/{userid}")
    public @ResponseBody Optional<AppUser> findAppUserRest(@PathVariable("userid") Long userid) {
        return userRepo.findById(userid);
    }

}
 