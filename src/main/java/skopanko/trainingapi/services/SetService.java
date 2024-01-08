package skopanko.trainingapi.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skopanko.trainingapi.entities.Set;
import skopanko.trainingapi.repositories.SetRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SetService {

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private ModelMapper modelMapper;


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
