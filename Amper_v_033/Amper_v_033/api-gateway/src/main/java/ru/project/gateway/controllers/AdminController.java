package ru.project.gateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.gateway.services.TechnologyServiceAdmin;
import ru.project.gateway.services.TechnologyServiceUser;
import ru.project.models.TechnologyDto;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final TechnologyServiceAdmin technologyServiceAdmin;
    private final TechnologyServiceUser technologyServiceUser;

    @Autowired
    public AdminController(TechnologyServiceAdmin technologyServiceAdmin, TechnologyServiceUser technologyServiceUser) {
        this.technologyServiceAdmin = technologyServiceAdmin;
        this.technologyServiceUser = technologyServiceUser;
    }
    @PostMapping
    public void creation(@RequestBody TechnologyDto technologyDto) {
        technologyServiceAdmin.creation(technologyDto);
    }

    @PutMapping("/{id}/vote")
    public void vote(@PathVariable(name = "id") int id, @RequestParam(name = "rang") String rang) {
        technologyServiceUser.vote(id, rang);
    }

    @GetMapping("/{id}")
    public TechnologyDto findById(@PathVariable(name = "id") int id) {
        return technologyServiceUser.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") int id) {
        technologyServiceAdmin.deleteById(id);
    }

    @GetMapping
    public List<TechnologyDto> getByCriteria(@RequestParam(name = "id", required = false) String id,
                                             @RequestParam(name = "name", required = false) String name,
                                             @RequestParam(name = "technologyType", required = false) String technologyType,
                                             @RequestParam(name = "rang", required = false) String rang) {
        return technologyServiceUser.getByCriteria(id, name, technologyType, rang);
    }
}