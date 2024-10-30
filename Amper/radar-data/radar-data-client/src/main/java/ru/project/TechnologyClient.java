package ru.project;

import ru.project.models.TechnologyDto;

import java.util.List;

public interface TechnologyClient {

    TechnologyDto creation(TechnologyDto technologyDto);

    TechnologyDto findById(int id);

    void deleteById(int id);

    void changeRang(int id, String rang);

    List<TechnologyDto> getByCriteria(String id, String name, String technologyType, String rang);
}
