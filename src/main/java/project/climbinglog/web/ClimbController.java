package project.climbinglog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import project.climbinglog.domain.Climb;
import project.climbinglog.domain.ClimbRepository;
import project.climbinglog.domain.Type;
import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;

@Controller
public class ClimbController {

    @Autowired
    private WorkoutRepository workoutRepo;
    @Autowired
    private ClimbRepository climbRepo;

    @GetMapping("/details/{workoutid}")
    public String showWorkoutDetails(@PathVariable("workoutid") Long workoutid, Model model) {
        Workout workout = workoutRepo.findById(workoutid)
                .orElseThrow(() -> new IllegalArgumentException());
        model.addAttribute("workout", workout);
        model.addAttribute("climbs", workout.getClimbs());
        return "details";
    }

    // add climb to spesific workout
    @GetMapping("/details/{workoutid}/addclimb")
    public String addClimbToWorkout(@PathVariable("workoutid") Long workoutid, Model model) {
        Workout workout = workoutRepo.findById(workoutid)
                .orElseThrow(() -> new IllegalArgumentException());
        model.addAttribute("climb", new Climb());
        model.addAttribute("workout", workout);
        model.addAttribute("types", Type.values());
        return "addclimb";
    }

    @PostMapping("/details/{workoutid}/saveclimb")
    public String saveClimbToWorkout(@PathVariable("workoutid") Long workoutid, Climb climb) {
        Workout workout = workoutRepo.findById(workoutid)
                .orElseThrow(() -> new IllegalArgumentException());
        climb.setWorkout(workout);
        climbRepo.save(climb);
        return "redirect:/details/" + workoutid;
    }
}
