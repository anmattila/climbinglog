package project.climbinglog;

import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import project.climbinglog.domain.Climb;
import project.climbinglog.domain.ClimbRepository;
import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;

@SpringBootApplication
public class ClimbinglogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimbinglogApplication.class, args);
	}

	@Bean
	public CommandLineRunner logdemo(WorkoutRepository reposWorkout, ClimbRepository reposClimb) {
		return (args) -> {

			SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
			
			Climb demoC1 = new Climb("boulder", "5", "indoors");
			Climb demoC2 = new Climb("top rope", "7A", "indoors");
			reposClimb.save(demoC1);
			reposClimb.save(demoC2);

			Workout demoW1 = new Workout(dateformat.parse("23.10.2024"), "Ristikko", "versitale session");
			reposWorkout.save(demoW1);

			for (Climb climb : reposClimb.findAll()) {
				System.out.println(climb.toString());
			}

			for (Workout workout : reposWorkout.findAll()) {
				System.out.println(workout.toString());
			}
		};
	}

}
 