package skopanko.trainingapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skopanko.trainingapi.dto.ExerciseDTO;
import skopanko.trainingapi.entities.Entry;
import skopanko.trainingapi.entities.Exercise;
import skopanko.trainingapi.entities.Set;
import skopanko.trainingapi.repositories.ExerciseRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public ExerciseDTO convertToDTO(Exercise exercise) {
        ExerciseDTO dto = new ExerciseDTO();
        dto.setId(exercise.getId());
        dto.setName(exercise.getName());
        dto.setDate(exercise.getDate());
        dto.setWeight(exercise.getWeight());
        dto.setReps(exercise.getReps());
        dto.setOneRM(exercise.getOneRM());
        dto.setNotes(exercise.getNotes());
        return dto;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> getExerciseById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId);
    }

    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise updateExercise(Long exerciseId, Exercise updatedExercise) {
        Optional<Exercise> existingExerciseOptional = exerciseRepository.findById(exerciseId);
        if (existingExerciseOptional.isPresent()) {
            Exercise existingExercise = existingExerciseOptional.get();
            existingExercise.setDate(updatedExercise.getDate());
            existingExercise.setName(updatedExercise.getName());
            existingExercise.setNotes(updatedExercise.getNotes());
            return exerciseRepository.save(existingExercise);
        } else {
            // Handle the case where the exercise with the given ID is not found
            return null;
        }
    }

    public void deleteExercise(Long exerciseId) {
        exerciseRepository.deleteById(exerciseId);
    }

    public List<Entry> getEntriesByExerciseId(Long exerciseId) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
        return exerciseOptional.map(Exercise::getEntries).orElse(null);
    }

    public List<Set> getSetsByExerciseId(Long exerciseId) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
        return exerciseOptional.map(Exercise::getSets).orElse(null);
    }
}
