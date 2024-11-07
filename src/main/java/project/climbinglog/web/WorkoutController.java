package project.climbinglog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;

@Controller
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepo;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/workouts")
    public String showAllWorkouts(Model model) {
        model.addAttribute("workouts", workoutRepo.findAll());
        return "workouts";
    }

    @GetMapping("/addworkout")

    public String addWorkout(Model model) {
        model.addAttribute("workout", new Workout());
        return "addworkout";
    }

    @GetMapping("/delete/{workoutid}")
    public String deleteWorkout(@PathVariable("workoutid") Long workoutid, Model model) {
        workoutRepo.deleteById(workoutid);
        return "redirect:/workouts";
    }

    @GetMapping("/edit/{workoutid}")
    public String editWorkout(@PathVariable("workoutid") Long workoutid, Model model) {
        model.addAttribute("workout", workoutRepo.findById(workoutid));
        return "editworkout";
    }

    @PostMapping("/saveworkout")
    public String checkWorkoutSubmit(@Valid @ModelAttribute("workout") Workout workout, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "addworkout";
        } else {
            model.addAttribute("workout", workout);
            workoutRepo.save(workout);
            return "redirect:/details/" + workout.getWorkoutid();
        }
    }

    @PostMapping("/saveedit")
    public String saveEditedWorkout(@Valid Workout workout, BindingResult bindingResult ) {
        // if (bindingResult.hasErrors()) {
        //     return "editworkout";
        // } else {
        //     workoutRepo.save(workout);
        //     return "redirect:/details/" + workout.getWorkoutid();
        // }
            workoutRepo.save(workout);
            return "redirect:/details/" + workout.getWorkoutid();
    }
}
