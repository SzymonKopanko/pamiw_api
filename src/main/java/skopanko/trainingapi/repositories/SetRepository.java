package skopanko.trainingapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import skopanko.trainingapi.entities.Set;

public interface SetRepository extends JpaRepository<Set, Long> {

}
