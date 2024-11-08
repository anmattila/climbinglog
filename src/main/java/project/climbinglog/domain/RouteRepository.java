package project.climbinglog.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface RouteRepository extends CrudRepository<Route, Long> {

    List<Route> findByRouteId(Long routeid);
    List<Route> findAllByWorkoutid(Long workoutid);
}
