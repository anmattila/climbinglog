package project.climbinglog;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import project.climbinglog.domain.AppUser;
import project.climbinglog.domain.AppUserRepository;
import project.climbinglog.domain.Route;
import project.climbinglog.domain.Type;
import project.climbinglog.domain.RouteRepository;
import project.climbinglog.domain.Workout;
import project.climbinglog.domain.WorkoutRepository;

@SpringBootApplication
public class ClimbinglogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimbinglogApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(ClimbinglogApplication.class);

	@Bean
	public CommandLineRunner logdemo(WorkoutRepository workoutRepo, RouteRepository routeRepo,
			AppUserRepository userRepo) {
		return (args) -> {

			Workout demoW1 = new Workout(LocalDate.of(2024, 1, 1), "Ristikko", "indoors", "versitale session");
			Workout demoW2 = new Workout(LocalDate.of(2024, 12, 13),"Ristikko", "indoors", "I was feeling lazy but got something done");
			Workout demoW3 = new Workout(LocalDate.of(2024, 10, 9),"Ristikko", "indoors", "feeling better");
			Workout demoW4 = new Workout(LocalDate.of(2024, 3, 10),"Ristikko", "indoors", "ok");
			Workout demoW5 = new Workout(LocalDate.of(2024, 05, 10),"Ristikko", "indoors", "was fine");

			Route demoR1 = new Route(Type.BOULDER, "5", "good warm-up");
			Route demoR2 = new Route(Type.BOULDER, "7A", "need to work");
			Route demoR3 = new Route(Type.TOP_ROPE, "6+", "");
			Route demoR4 = new Route(Type.TOP_ROPE, "6+", "");
			Route demoR5 = new Route(Type.TOP_ROPE, "5+", "");
			Route demoR6 = new Route(Type.TOP_ROPE, "6-", "");
			Route demoR7 = new Route(Type.TOP_ROPE, "7A", "");
			Route demoR8 = new Route(Type.TOP_ROPE, "7A", "");
			Route demoR9 = new Route(Type.TOP_ROPE, "6+", "");
			Route demoR10 = new Route(Type.TOP_ROPE, "5", "");			

			demoW1.addRoute(demoR1);
			demoW1.addRoute(demoR2);
			demoW2.addRoute(demoR3);
			demoW2.addRoute(demoR4);
			demoW3.addRoute(demoR5);
			demoW3.addRoute(demoR6);
			demoW4.addRoute(demoR7);
			demoW4.addRoute(demoR8);
			demoW5.addRoute(demoR9);
			demoW5.addRoute(demoR10);

			workoutRepo.save(demoW1);
			workoutRepo.save(demoW2);
			workoutRepo.save(demoW3);
			workoutRepo.save(demoW4);
			workoutRepo.save(demoW5);

			// could do?
			// var workout1 =workoutRepo.save(new Workout(fdate.parse("01.09.2024"), "Ristikko", "indoors", "versitale session") {{
			// 	addRoute(new Route(Type.BOULDER, "5", "good warm-up"));
			// 	addRoute(new Route(Type.BOULDER, "7A", "need to work"));

			// }});


			AppUser user1 = new AppUser("", "user", "user");
			AppUser user2 = new AppUser("", "", "user");
			AppUser admin = new AppUser("admin", "admin", "admin");
			userRepo.save(user1);
			userRepo.save(user2);
			userRepo.save(admin);

			log.info("fecth all climbs");
			for (Route route : routeRepo.findAll()) {
				log.info(route.toString());
			}
			log.info("fecth all workouts");
			for (Workout workout : workoutRepo.findAll()) {
				log.info(workout.toString());			
			}
			log.info("fecth all users");
			for (AppUser appUser : userRepo.findAll()) {
				log.info(appUser.toString());			
			}

		};
	}

}
