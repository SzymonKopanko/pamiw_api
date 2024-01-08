package skopanko.trainingapi.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import skopanko.trainingapi.entities.Entry;
import skopanko.trainingapi.entities.Exercise;
import skopanko.trainingapi.entities.Set;
import skopanko.trainingapi.services.EntryService;
import skopanko.trainingapi.services.ExerciseService;
import skopanko.trainingapi.services.SetService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private SetService setService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/{exerciseId}")
    public Exercise getExerciseById(@PathVariable Long exerciseId) {
        return exerciseService.getExerciseById(exerciseId).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Exercise saveExercise(@RequestBody Exercise exercise) {
        return exerciseService.saveExercise(exercise);
    }

    @PutMapping("/{exerciseId}")
    public Exercise updateExercise(@PathVariable Long exerciseId, @RequestBody Exercise updatedExercise) {
        return exerciseService.updateExercise(exerciseId, updatedExercise);
    }

    @DeleteMapping("/{exerciseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercise(@PathVariable Long exerciseId) {
        exerciseService.deleteExercise(exerciseId);
    }

    @GetMapping("/{exerciseId}/entries")
    public List<Entry> getEntriesByExerciseId(@PathVariable Long exerciseId) {
        return exerciseService.getEntriesByExerciseId(exerciseId);
    }

    @GetMapping("/{exerciseId}/sets")
    public List<Set> getSetsByExerciseId(@PathVariable Long exerciseId) {
        return exerciseService.getSetsByExerciseId(exerciseId);
    }
}
