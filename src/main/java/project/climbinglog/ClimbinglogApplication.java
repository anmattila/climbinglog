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

			Workout workout1 = new Workout(LocalDate.of(2024, 1, 1), "Ristikko", "indoors", "versitale session", user1);
			Workout workout2 = new Workout(LocalDate.of(2024, 12, 13), "Ristikko", "indoors", "I was feeling lazy but got something done", user1);
			Workout workout3 = new Workout(LocalDate.of(2024, 10, 9), "Ristikko", "indoors", "feeling better", user2);
			Workout workout4 = new Workout(LocalDate.of(2024, 3, 10), "Ristikko", "indoors", "ok", user2);
			Workout workout5 = new Workout(LocalDate.of(2024, 05, 10), "Ristikko", "indoors", "was fine", user2);

			Route route1 = new Route(Type.BOULDER, "5", 1);
			Route route2 = new Route(Type.BOULDER, "7A", 2);
			Route route3 = new Route(Type.TOP_ROPE, "6+", 1);
			Route route4 = new Route(Type.TOP_ROPE, "6+", 3);
			Route route5 = new Route(Type.TOP_ROPE, "5+", 2);
			Route route6 = new Route(Type.TOP_ROPE, "6-", 2);
			Route route7 = new Route(Type.TOP_ROPE, "7A", 4);
			Route route8 = new Route(Type.TOP_ROPE, "7A", 4);
			Route route9 = new Route(Type.TOP_ROPE, "6+", 1);
			Route route10 = new Route(Type.TOP_ROPE, "5", 1);

			workout1.addRoute(route1);
			workout1.addRoute(route2);
			workout2.addRoute(route3);
			workout2.addRoute(route4);
			workout3.addRoute(route5);
			workout3.addRoute(route6);
			workout4.addRoute(route7);
			workout4.addRoute(route8);
			workout5.addRoute(route9);
			workout5.addRoute(route10);

			workoutRepo.save(workout1);
			workoutRepo.save(workout2);
			workoutRepo.save(workout3);
			workoutRepo.save(workout4);
			workoutRepo.save(workout5);

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
