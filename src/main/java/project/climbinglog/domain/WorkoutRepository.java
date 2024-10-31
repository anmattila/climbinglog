package project.climbinglog.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface WorkoutRepository extends CrudRepository<Workout, Long>{

    List<Workout> findByWorkoutid(Long workoutid);

}
