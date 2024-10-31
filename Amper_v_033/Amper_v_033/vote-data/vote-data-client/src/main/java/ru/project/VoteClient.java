package ru.project;

import ru.project.models.TechnologyDto;

public interface VoteClient {
    String vote(int userId, int technologyId, String rang);
    void registerNewTechnology(TechnologyDto technologyDto);

    void removeTechnology(int technologyId);
}
