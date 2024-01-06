package skopanko.trainingapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skopanko.trainingapi.dto.EntryDTO;
import skopanko.trainingapi.entities.Entry;
import skopanko.trainingapi.entities.Set;
import skopanko.trainingapi.repositories.EntryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    public EntryDTO convertToDTO(Entry entry) {
        EntryDTO dto = new EntryDTO();
        dto.setId(entry.getId());
        dto.setExerciseId(entry.getExercise().getId());
        dto.setMainWeight(entry.getMainWeight());
        dto.setDate(entry.getDate());
        return dto;
    }

    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    public Optional<Entry> getEntryById(Long entryId) {
        return entryRepository.findById(entryId);
    }

    public Entry saveEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    public Entry updateEntry(Long entryId, Entry updatedEntry) {
        Optional<Entry> existingEntryOptional = entryRepository.findById(entryId);
        if (existingEntryOptional.isPresent()) {
            Entry existingEntry = existingEntryOptional.get();
            // Update fields as needed
            existingEntry.setMainWeight(updatedEntry.getMainWeight());
            existingEntry.setDate(updatedEntry.getDate());
            // Update any other fields as needed
            return entryRepository.save(existingEntry);
        } else {
            // Handle the case where the entry with the given ID is not found
            return null;
        }
    }

    public void deleteEntry(Long entryId) {
        entryRepository.deleteById(entryId);
    }

    public List<Set> getSetsByEntryId(Long entryId) {
        Optional<Entry> entryOptional = entryRepository.findById(entryId);
        return entryOptional.map(Entry::getSets).orElse(null);
    }
}
