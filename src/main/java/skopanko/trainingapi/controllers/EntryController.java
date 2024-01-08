package skopanko.trainingapi.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
@RequestMapping("/api/entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private SetService setService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Entry> getAllEntries() {
        return entryService.getAllEntries();
    }

    @GetMapping("/{entryId}")
    public Entry getEntryById(@PathVariable Long entryId) {
        return entryService.getEntryById(entryId).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry saveEntry(@RequestBody Entry entry) {
        entryService.saveEntry(entry);
        return entry;
    }

    @PutMapping("/{entryId}")
    public Entry updateEntry(@PathVariable Long entryId, @RequestBody Entry updatedEntry) {
        entryService.updateEntry(entryId, updatedEntry);
        return updatedEntry;
    }

    @DeleteMapping("/{entryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable Long entryId) {
        entryService.deleteEntry(entryId);
    }

    @GetMapping("/{entryId}/sets")
    public List<Set> getSetsByEntryId(@PathVariable Long entryId) {
        return entryService.getSetsByEntryId(entryId);
    }
}
