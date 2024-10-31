package ru.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.services.TechnologyService;
import ru.project.models.TechnologyDto;

import java.util.List;

@RestController
@RequestMapping("/technology")
public class TechnologyController {
    private final TechnologyService technologyService;

    @Autowired
    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping
    public TechnologyDto creation(@RequestBody TechnologyDto technologyDto) {
        return technologyService.creation(technologyDto);
    }

    @PutMapping("/{id}")
    public void changeRang(@PathVariable(name = "id") int id, @RequestParam(name = "rang") String rang) {
        technologyService.changeRang(id, rang);
    }

    @GetMapping("/{id}")
    public TechnologyDto findById(@PathVariable(name = "id") int id) {
        return technologyService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") int id) {
        technologyService.deleteById(id);
    }

    @GetMapping
    public List<TechnologyDto> getByCriteria(@RequestParam(name = "id", required = false) String id,
                                             @RequestParam(name = "name", required = false) String name,
                                             @RequestParam(name = "technologyType", required = false) String technologyType,
                                             @RequestParam(name = "rang", required = false) String rang) {
        return technologyService.getByCriteria(id, name, technologyType, rang);
    }
}