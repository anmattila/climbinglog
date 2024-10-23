package project.climbinglog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.climbinglog.domain.ClimbRepository;
import project.climbinglog.domain.WorkoutRepository;

@Controller
public class LogController {

    @Autowired
    private WorkoutRepository reposWorkout;
    @Autowired
    private ClimbRepository reposClimb;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/add")


}
