package EwasteManagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import EwasteManagement.model.EWaste;
import EwasteManagement.repository.EWasteRepository;

@RestController
@RequestMapping("/api/ewaste")
@CrossOrigin(origins = "*")
public class EWasteController {

    private final EWasteRepository repository;

    public EWasteController(EWasteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public EWaste addEWaste(@RequestBody EWaste ewaste) {
        return repository.save(ewaste);
    }

    @GetMapping
    public List<EWaste> getAllEWaste() {
        return repository.findAll();
    }

    @PutMapping("/{id}/status")
    public EWaste updateStatus(
            @PathVariable String id,
            @RequestParam String status) {

        EWaste ewaste = repository.findById(id).orElse(null);

        if (ewaste != null) {
            ewaste.setStatus(status);
            return repository.save(ewaste);
        }

        return null;
    }
}