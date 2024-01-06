package skopanko.trainingapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import skopanko.trainingapi.dto.EntryDTO;
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
@RequestMapping("/api/entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private SetService setService;

    @GetMapping
    public List<EntryDTO> getAllEntries() {
        List<Entry> entries = entryService.getAllEntries();
        return entries.stream()
                .map(entryService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{entryId}")
    public EntryDTO getEntryById(@PathVariable Long entryId) {
        Entry entry = entryService.getEntryById(entryId).orElse(null);
        return entry != null ? entryService.convertToDTO(entry) : null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDTO saveEntry(@RequestBody EntryDTO entryDTO) {
        Entry entry = convertToEntity(entryDTO);
        Entry savedEntry = entryService.saveEntry(entry);
        return entryService.convertToDTO(savedEntry);
    }

    @PutMapping("/{entryId}")
    public EntryDTO updateEntry(@PathVariable Long entryId, @RequestBody EntryDTO updatedEntryDTO) {
        Entry updatedEntry = convertToEntity(updatedEntryDTO);
        Entry resultEntry = entryService.updateEntry(entryId, updatedEntry);
        return resultEntry != null ? entryService.convertToDTO(resultEntry) : null;
    }

    @DeleteMapping("/{entryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable Long entryId) {
        entryService.deleteEntry(entryId);
    }

    @GetMapping("/{entryId}/sets")
    public List<SetDTO> getSetsByEntryId(@PathVariable Long entryId) {
        List<Set> sets = entryService.getSetsByEntryId(entryId);
        return sets.stream()
                .map(setService::convertToDTO)
                .collect(Collectors.toList());
    }

    private Entry convertToEntity(EntryDTO entryDTO) {
        Entry entry = new Entry();
        entry.setId(entryDTO.getId());

        Exercise exercise = exerciseService.getExerciseById(entryDTO.getExerciseId()).orElse(null);
        entry.setExercise(exercise);

        entry.setMainWeight(entryDTO.getMainWeight());
        entry.setDate(entryDTO.getDate());
        List<Set> sets = entryService.getSetsByEntryId(entryDTO.getId());
        entry.setSets(sets);

        return entry;
    }
}
