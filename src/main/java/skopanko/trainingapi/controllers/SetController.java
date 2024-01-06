package skopanko.trainingapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import skopanko.trainingapi.entities.Set;
import skopanko.trainingapi.services.SetService;

import java.util.List;

@RestController
@RequestMapping("/api/sets")
public class SetController {

    @Autowired
    private SetService setService;

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
