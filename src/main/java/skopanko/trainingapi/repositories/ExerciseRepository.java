package skopanko.trainingapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import skopanko.trainingapi.entities.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}

