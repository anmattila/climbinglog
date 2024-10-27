package project.climbinglog.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ClimbRepository extends CrudRepository<Climb, Long> {

    List<Climb> findByClimbid(Long climbid);
}
