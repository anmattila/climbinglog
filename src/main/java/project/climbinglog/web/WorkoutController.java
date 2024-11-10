package project.climbinglog.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;
import project.climbinglog.domain.AppUser;
import project.climbinglog.domain.AppUserRepository;

@Controller
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepo;
    @Autowired
    private AppUserRepository userRepo;

    @GetMapping("/addworkout")
    @PreAuthorize("hasAuthority('USER')")
    public String addWorkout(Model model) {
        model.addAttribute("workout", new Workout());
        return "addworkout";
    }

    @GetMapping("/delete/{workoutid}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String deleteWorkout(@PathVariable("workoutid") Long workoutid, Model model, Principal principal) {
        Workout workout = workoutRepo.findById(workoutid)
                .orElseThrow(() -> new IllegalArgumentException());
        String username = principal.getName();
        if (!workout.getUser().getUsername().equals(username)) {
            return "redirect:/error";
        } else {
            workoutRepo.deleteById(workoutid);
            return "redirect:/home";
        }
    }

    @GetMapping("/edit/{workoutid}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String editWorkout(@PathVariable("workoutid") Long workoutid, Model model, Principal principal) {
        Workout workout = workoutRepo.findById(workoutid)
                .orElseThrow(() -> new IllegalArgumentException());
        String username = principal.getName();
        if (!workout.getUser().getUsername().equals(username)) {
            return "redirect:/error";
        } else {
            model.addAttribute("workout", workout);
            return "editworkout";
        }
    }

    @PostMapping("/saveworkout")
    @PreAuthorize("hasAuthority('USER')")
    public String checkWorkoutSubmit(@Valid @ModelAttribute("workout") Workout workout, BindingResult bindingResult,
            Principal principal, Model model) {
        if (bindingResult.hasErrors()) {
            return "addworkout";
        } else {
            String username = principal.getName();
            AppUser user = userRepo.findByUsername(username);
            workout.setUser(user);
            workoutRepo.save(workout);
            return "redirect:/details/" + workout.getWorkoutid();
        }
    }

    @PostMapping("/saveedit")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String saveEditedWorkout(@Valid Workout workout, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "editworkout";
        } else {
            String username = principal.getName();
            AppUser user = userRepo.findByUsername(username);
            workout.setUser(user);
            workoutRepo.save(workout);
            return "redirect:/details/" + workout.getWorkoutid();
        }
    }

}
