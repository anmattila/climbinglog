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

			AppUser user1 = new AppUser("climber1", "$2a$15$xKGLDF1C4VBc8RyXk86kr.z1AXzFraty3yxAPFjWLapEg5KmTjwpy", "USER");
			AppUser user2 = new AppUser("ilovecats", "$2a$15$6MvQRT7mLUnONK08rkXzXeH1QB7ePTF4vvziggn.ehQUInVgffa4W", "USER");
			AppUser admin = new AppUser("admin", "$2a$15$gZ9MzbkWtzpmyE24GwHw2O3RfP.FnkQrlUPzKx2EMkNcS/slQ54hi", "ADMIN");
			userRepo.save(user1);
			userRepo.save(user2);
			userRepo.save(admin);

			Workout demoW1 = new Workout(LocalDate.of(2024, 1, 1), "Ristikko", "indoors", "versitale session", user1);
			Workout demoW2 = new Workout(LocalDate.of(2024, 12, 13), "Ristikko", "indoors", "I was feeling lazy but got something done", user1);
			Workout demoW3 = new Workout(LocalDate.of(2024, 10, 9), "Ristikko", "indoors", "feeling better", user2);
			Workout demoW4 = new Workout(LocalDate.of(2024, 3, 10), "Ristikko", "indoors", "ok", user2);
			Workout demoW5 = new Workout(LocalDate.of(2024, 05, 10), "Ristikko", "indoors", "was fine", user2);

			Route demoR1 = new Route(Type.BOULDER, "5", 1);
			Route demoR2 = new Route(Type.BOULDER, "7A", 2);
			Route demoR3 = new Route(Type.TOP_ROPE, "6+", 1);
			Route demoR4 = new Route(Type.TOP_ROPE, "6+", 3);
			Route demoR5 = new Route(Type.TOP_ROPE, "5+", 2);
			Route demoR6 = new Route(Type.TOP_ROPE, "6-", 2);
			Route demoR7 = new Route(Type.TOP_ROPE, "7A", 4);
			Route demoR8 = new Route(Type.TOP_ROPE, "7A", 4);
			Route demoR9 = new Route(Type.TOP_ROPE, "6+", 1);
			Route demoR10 = new Route(Type.TOP_ROPE, "5", 1);

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
			// var workout1 =workoutRepo.save(new Workout(fdate.parse("01.09.2024"),
			// "Ristikko", "indoors", "versitale session") {{
			// addRoute(new Route(Type.BOULDER, "5", "good warm-up"));
			// addRoute(new Route(Type.BOULDER, "7A", "need to work"));

			// }});

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
