package ru.project.gateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.gateway.services.TechnologyServiceUser;
import ru.project.models.TechnologyDto;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final TechnologyServiceUser technologyServiceUser;

    @Autowired
    public UserController(TechnologyServiceUser technologyServiceUser) {
        this.technologyServiceUser = technologyServiceUser;
    }

    @PutMapping("/{id}/vote")
    public void vote(@PathVariable(name = "id") int id, @RequestParam(name = "ring") String rang) {
        technologyServiceUser.vote(id, rang);
    }

    @GetMapping("/{id}")
    public TechnologyDto findById(@PathVariable(name = "id") int id) {
        return technologyServiceUser.findById(id);
    }

    @GetMapping
    public List<TechnologyDto> getByCriteria(@RequestParam(name = "id", required = false) String id,
                                             @RequestParam(name = "name", required = false) String name,
                                             @RequestParam(name = "technologyType", required = false) String technologyType,
                                             @RequestParam(name = "rang", required = false) String rang) {
        return technologyServiceUser.getByCriteria(id, name, technologyType, rang);
    }
}
