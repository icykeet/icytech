package ru.project.services;
import ru.project.models.TechnologyDto;

import java.util.List;

public interface TechnologyService {
    TechnologyDto creation(TechnologyDto technologyDto);
    TechnologyDto findById(int id);
    void changeRang(int id, String rang);
    void deleteById(int id);
    List<TechnologyDto> getByCriteria(String id, String name, String technologyType, String rang);
}