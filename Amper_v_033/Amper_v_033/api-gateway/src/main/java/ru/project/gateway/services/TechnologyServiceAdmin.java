package ru.project.gateway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.TechnologyClient;
import ru.project.VoteClient;
import ru.project.models.TechnologyDto;

@Service
public class TechnologyServiceAdmin {
    private TechnologyClient technologyClient;
    private VoteClient voteClient;

    @Autowired
    public TechnologyServiceAdmin(TechnologyClient technologyClient, VoteClient voteClient) {
        this.technologyClient = technologyClient;
        this.voteClient = voteClient;
    }

    public void creation(TechnologyDto technologyDto) {
        TechnologyDto newTechnology = technologyClient.creation(technologyDto);
        voteClient.registerNewTechnology(newTechnology);
    }

    public void deleteById(int id) {
        technologyClient.deleteById(id);
        voteClient.removeTechnology(id);
    }
}