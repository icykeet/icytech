package ru.project.controllers;

import org.springframework.web.bind.annotation.*;
import ru.project.services.VotingService;
import ru.project.models.TechnologyDto;

@RestController
@RequestMapping("/vote")
public class VoteController {
    private final VotingService votingService;

    public VoteController(VotingService votingService) {
        this.votingService = votingService;
    }

    @PutMapping
    public String vote(@RequestParam(name = "userId") int userId,
                       @RequestParam(name = "technologyId") int technologyId,
                       @RequestParam(name = "rang") String rang) {
        return votingService.vote(userId, technologyId, rang);
    }

    @PostMapping
    public void registerNewTechnology(@RequestBody TechnologyDto technologyDto) {
        votingService.registerNewTechnology(technologyDto);
    }

    @DeleteMapping("/{id}")
    public void removeTechnology(@PathVariable(name = "id") int technologyId) {
        votingService.removeTechnology(technologyId);
    }
}
