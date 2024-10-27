package project.climbinglog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.climbinglog.domain.ClimbRepository;
import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;

@Controller
public class WorkoutController {

    @Autowired
    private WorkoutRepository reposWorkout;
    @Autowired
    private ClimbRepository reposClimb;

    @RequestMapping("/home")
    public String index() {
        return "home";
    }

    @GetMapping("/workouts")
    public String showAllWorkouts(Model model) {
        model.addAttribute("workouts", reposWorkout.findAll());
        return "workouts";
    }

    @GetMapping("/addworkout")
    public String addWorkout(Model model) {
        model.addAttribute("workout", new Workout());
        return "addworkout";
    }

    @PostMapping("/saveworkout")
    public String saveWorkout(Workout workout) {
        reposWorkout.save(workout);
        return "redirect:/workouts";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
