package skopanko.trainingapi.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import skopanko.trainingapi.entities.Set;
import skopanko.trainingapi.services.EntryService;
import skopanko.trainingapi.services.ExerciseService;
import skopanko.trainingapi.services.SetService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sets")
public class SetController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private SetService setService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Set> getAllSets() {
        return setService.getAllSets();
    }

    @GetMapping("/{setId}")
    public Set getSetById(@PathVariable Long setId) {
        return setService.getSetById(setId).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Set saveSet(@RequestBody Set set) {
        return setService.saveSet(set);
    }

    @DeleteMapping("/{setId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSet(@PathVariable Long setId) {
        setService.deleteSet(setId);
    }


}