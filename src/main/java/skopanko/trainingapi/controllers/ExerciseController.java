package skopanko.trainingapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import skopanko.trainingapi.dto.EntryDTO;
import skopanko.trainingapi.dto.ExerciseDTO;
import skopanko.trainingapi.dto.SetDTO;
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

    @GetMapping
    public List<ExerciseDTO> getAllExercises() {
        List<Exercise> exercises = exerciseService.getAllExercises();
        return exercises.stream()
                .map(exerciseService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{exerciseId}")
    public ExerciseDTO getExerciseById(@PathVariable Long exerciseId) {
        Exercise exercise = exerciseService.getExerciseById(exerciseId).orElse(null);
        return exercise != null ? exerciseService.convertToDTO(exercise) : null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseDTO saveExercise(@RequestBody ExerciseDTO exerciseDTO) {
        Exercise exercise = convertToEntity(exerciseDTO);
        Exercise savedExercise = exerciseService.saveExercise(exercise);
        return exerciseService.convertToDTO(savedExercise);
    }

    @PutMapping("/{exerciseId}")
    public ExerciseDTO updateExercise(@PathVariable Long exerciseId, @RequestBody ExerciseDTO updatedExerciseDTO) {
        Exercise updatedExercise = convertToEntity(updatedExerciseDTO);
        Exercise resultExercise = exerciseService.updateExercise(exerciseId, updatedExercise);
        return resultExercise != null ? exerciseService.convertToDTO(resultExercise) : null;
    }

    @DeleteMapping("/{exerciseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercise(@PathVariable Long exerciseId) {
        exerciseService.deleteExercise(exerciseId);
    }

    @GetMapping("/{exerciseId}/entries")
    public List<EntryDTO> getEntriesByExerciseId(@PathVariable Long exerciseId) {
        List<Entry> entries = exerciseService.getEntriesByExerciseId(exerciseId);
        return entries.stream()
                .map(entryService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{exerciseId}/sets")
    public List<SetDTO> getSetsByExerciseId(@PathVariable Long exerciseId) {
        List<Set> sets = exerciseService.getSetsByExerciseId(exerciseId);
        return sets.stream()
                .map(setService::convertToDTO)
                .collect(Collectors.toList());
    }

    private Exercise convertToEntity(ExerciseDTO exerciseDTO) {
        Exercise exercise = new Exercise();
        exercise.setId(exerciseDTO.getId());
        exercise.setDate(exerciseDTO.getDate());
        exercise.setName(exerciseDTO.getName());
        exercise.setNotes(exerciseDTO.getNotes());
        List<Entry> entries = exerciseService.getEntriesByExerciseId(exerciseDTO.getId());
        exercise.setEntries(entries);

        return exercise;
    }
}
