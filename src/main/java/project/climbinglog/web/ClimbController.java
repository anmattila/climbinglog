package project.climbinglog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import project.climbinglog.domain.Climb;
import project.climbinglog.domain.ClimbRepository;
import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;

@Controller
public class ClimbController {

    @Autowired
    private WorkoutRepository reposWorkout;
    @Autowired
    private ClimbRepository reposClimb;

    // find all climbs from one workout
    @GetMapping("/details/{workoutid}")
    public String showWorkoutDetails(@PathVariable("workoutid") Long workoutid, Model model) {
        Workout workout = reposWorkout.findById(workoutid)
                .orElseThrow(() -> new IllegalArgumentException());
        model.addAttribute("workout", workout);
        model.addAttribute("climbs", workout.getClimbs());
        return "details";
    }

    // add climb to spesific workout
    @GetMapping("/details/{workoutid}/addclimb")
    public String addClimbToWorkout(@PathVariable("workoutid") Long workoutid, Model model) {
        Workout workout = reposWorkout.findById(workoutid)
                .orElseThrow(() -> new IllegalArgumentException());
        model.addAttribute("climb", new Climb());
        model.addAttribute("workout", workout);
        return "addclimb";
    }

    @PostMapping("/details/{workoutid}/saveclimb")
    public String saveClimbToWorkout(@PathVariable("workoutid") Long workoutid, Climb climb){
        Workout workout = reposWorkout.findById(workoutid)
            .orElseThrow(() -> new IllegalArgumentException());
       climb.setWorkout(workout);
       reposClimb.save(climb);
       // reposWorkout.save(workout);
        return "redirect:/details/"+workoutid;
    }

}
