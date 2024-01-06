package skopanko.trainingapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skopanko.trainingapi.entities.Set;
import skopanko.trainingapi.repositories.SetRepository;

import java.util.List;
import java.util.Optional;
import skopanko.trainingapi.dto.SetDTO;

@Service
@Transactional
public class SetService {

    @Autowired
    private SetRepository setRepository;


    public SetDTO convertToDTO(Set set) {
        SetDTO dto = new SetDTO();
        dto.setId(set.getId());
        dto.setExerciseId(set.getExercise().getId());
        dto.setEntryId(set.getEntry().getId());
        dto.setReps(set.getReps());
        dto.setWeight(set.getWeight());
        dto.setRir(set.getRir());
        dto.setOneRM(set.getOneRM());
        return dto;
    }

    public List<Set> getAllSets() {
        return setRepository.findAll();
    }

    public Optional<Set> getSetById(Long setId) {
        return setRepository.findById(setId);
    }

    public Set saveSet(Set set) {
        return setRepository.save(set);
    }

    public void deleteSet(Long setId) {
        setRepository.deleteById(setId);
    }
}
