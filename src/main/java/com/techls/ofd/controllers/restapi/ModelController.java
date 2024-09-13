package com.techls.ofd.controllers.restapi;

import com.techls.ofd.entities.Model;
import com.techls.ofd.services.model.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/models")
public class ModelController {
    private final ModelService modelService;

    @GetMapping
    public List<Model> getAll() {
        return modelService.getAll();
    }

    @GetMapping("/combo")
    public List<Model> getCombo() {
        return modelService.getCombo();
    }

    @PostMapping
    public Long create(@RequestBody Model model) {
        return modelService.create(model);
    }

    @PutMapping
    public void update(@RequestBody Model model) {
        modelService.update(model);
    }

    @PutMapping("/recover")
    public void recover(@RequestParam Long id) {
        modelService.recover(id);
    }

    @DeleteMapping
    public void archive(@RequestParam Long id) {
        modelService.archive(id);
    }
}
