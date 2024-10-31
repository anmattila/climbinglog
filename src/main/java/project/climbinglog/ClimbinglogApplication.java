package project.climbinglog;

import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import project.climbinglog.domain.AppUser;
import project.climbinglog.domain.AppUserRepository;
import project.climbinglog.domain.Climb;
import project.climbinglog.domain.Type;
import project.climbinglog.domain.ClimbRepository;
import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;

@SpringBootApplication
public class ClimbinglogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimbinglogApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(ClimbinglogApplication.class);

	@Bean
	public CommandLineRunner logdemo(WorkoutRepository workoutRepo, ClimbRepository climbRepo,
			AppUserRepository userRepo) {
		return (args) -> {

			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			Workout demoW1 = new Workout(LocalDate.of(2024, 9, 1), "Ristikko", "indoors", "versitale session");
			Workout demoW2 = new Workout(LocalDate.of(2024, 9, 5),"Ristikko", "indoors", "I was feeling lazy but got something done");
			Workout demoW3 = new Workout(LocalDate.of(2024, 9, 21),"Ristikko", "indoors", "feeling better");
			Workout demoW4 = new Workout(LocalDate.of(2024, 10, 4),"Ristikko", "indoors", "ok");
			Workout demoW5 = new Workout(LocalDate.of(2024, 10, 10),"Ristikko", "indoors", "ok");

			Climb demoC1 = new Climb(Type.BOULDER, "5", "something");
			Climb demoC2 = new Climb(Type.TOP_ROPE, "7A", "nice");
			Climb demoC3 = new Climb(Type.TOP_ROPE, "6+", "");
			Climb demoC4 = new Climb(Type.TOP_ROPE, "6+", "");
			Climb demoC5 = new Climb(Type.TOP_ROPE, "5+", "");
			Climb demoC6 = new Climb(Type.TOP_ROPE, "6-", "");
			Climb demoC7 = new Climb(Type.TOP_ROPE, "7A", "");
			Climb demoC8 = new Climb(Type.TOP_ROPE, "7A", "");
			Climb demoC9 = new Climb(Type.TOP_ROPE, "6+", "");
			Climb demoC10 = new Climb(Type.TOP_ROPE, "5", "");			

			demoW1.addClimb(demoC1);
			demoW1.addClimb(demoC2);
			demoW2.addClimb(demoC3);
			demoW3.addClimb(demoC4);
			demoW3.addClimb(demoC5);
			demoW4.addClimb(demoC6);
			demoW4.addClimb(demoC7);
			demoW4.addClimb(demoC8);
			demoW5.addClimb(demoC9);
			demoW5.addClimb(demoC10);

			workoutRepo.save(demoW1);
			workoutRepo.save(demoW2);
			workoutRepo.save(demoW3);
			workoutRepo.save(demoW4);
			workoutRepo.save(demoW5);

			AppUser registered = new AppUser("registered", "user", "user");
			AppUser admin = new AppUser("admin", "admin", "admin");
			userRepo.save(registered);
			userRepo.save(admin);

			log.info("show all workouts, climbs and users");
			for (Climb climb : climbRepo.findAll()) {
				System.out.println(climb.toString());
			}
			for (Workout workout : workoutRepo.findAll()) {
				System.out.println(workout.toString());
			}
			for (AppUser appUser : userRepo.findAll()) {
				System.out.println(appUser.toString());
			}

		};
	}

}
