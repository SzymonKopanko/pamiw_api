package skopanko.trainingapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import skopanko.trainingapi.entities.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
