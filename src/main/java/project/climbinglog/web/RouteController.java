package project.climbinglog.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import project.climbinglog.domain.Route;
import project.climbinglog.domain.RouteRepository;
import project.climbinglog.domain.Type;
import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;

@Controller
public class RouteController {

    @Autowired
    private WorkoutRepository workoutRepo;
    @Autowired
    private RouteRepository routeRepo;

    @GetMapping("/details/{workoutid}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String showWorkoutDetails(@PathVariable("workoutid") Long workoutid, Model model, Principal principal) {
        Workout workout = workoutRepo.findById(workoutid)
        .orElseThrow(() -> new IllegalArgumentException());
        String username = principal.getName();
        if (!workout.getUser().getUsername().equals(username)) {
            return "redirect:/error";
        } else {
            model.addAttribute("workout", workout);
            model.addAttribute("routes", workout.getRoutes());
            return "details";
        }
    }

    // add climbed route to spesific workout
    @GetMapping("/details/{workoutid}/addroute")
    @PreAuthorize("hasAuthority('USER')")
    public String addRouteToWorkout(@PathVariable("workoutid") Long workoutid, Model model) {
        Workout workout = workoutRepo.findById(workoutid)
                .orElseThrow(() -> new IllegalArgumentException());
        model.addAttribute("route", new Route());
        model.addAttribute("types", Type.values());
        model.addAttribute("workout", workout);
        return "addroute";
    }

    @PostMapping("/details/{workoutid}/saveroute")
    @PreAuthorize("hasAuthority('USER')")
    public String saveRouteToWorkout(@PathVariable("workoutid") Long workoutid, Route route, Principal principal) {
        Workout workout = workoutRepo.findById(workoutid)
            .orElseThrow(() -> new IllegalArgumentException());
        String username = principal.getName();
        if (!workout.getUser().getUsername().equals(username)) {
            return "redirect:/error";
        } else {
            route.setWorkout(workout);
            routeRepo.save(route);
            return "redirect:/details/" + workout.getWorkoutid();
        }
    }

    @GetMapping("/details/delete/{routeid}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String deleteRoute(@PathVariable("routeid") Long routeid, Model model) {
        Route route = routeRepo.findById(routeid).get();
        Workout workout = route.getWorkout();
        routeRepo.deleteById(routeid);
        return "redirect:/details/" + workout.getWorkoutid();
    }

}
