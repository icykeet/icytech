package ru.project.services;

import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.project.filters.TechnologyFields;
import ru.project.filters.TechnologyFilter;
import ru.project.mappers.Mapper;
import ru.project.models.Technology;
import ru.project.filters.TechnologySpecification;
import ru.project.models.Rang;
import ru.project.models.TechnologyType;
import ru.project.repositories.TechnologyRepository;
import ru.project.models.TechnologyDto;

import java.util.ArrayList;
import java.util.List;

@Service
@ExtensionMethod(Mapper.class)
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }
    public TechnologyDto creation(TechnologyDto technologyDto) {
        Technology technology = new Technology(technologyDto.getName(),
                TechnologyType.valueOf(technologyDto.getTechnologyType()),
                technologyDto.getLink(),
                technologyDto.getVersion(),
                Rang.valueOf(technologyDto.getRang()));
        return technologyRepository.save(technology).toDto();
    }
    public TechnologyDto findById(int id) {
        return technologyRepository.getReferenceById(id).toDto();
    }
    public void changeRang(int id, String rang) {
        Technology technology = technologyRepository.getReferenceById(id);
        technology.setRang(Rang.valueOf(rang));
        technologyRepository.save(technology);
    }
    public void deleteById(int id) {
        technologyRepository.deleteById(id);
    }
    public List<TechnologyDto> getByCriteria(String id, String name, String technologyType, String rang) {
        List<TechnologyFilter> technologyFilters = new ArrayList<>();
        if (rang != null) {
            technologyFilters.add(new TechnologyFilter(TechnologyFields.rang, rang));
        }
        if (technologyType != null) {
            technologyFilters.add(new TechnologyFilter(TechnologyFields.technologyType, technologyType));
        }
        if (id != null) {
            technologyFilters.add(new TechnologyFilter(TechnologyFields.id, id));
        }
        if (name != null) {
            technologyFilters.add(new TechnologyFilter(TechnologyFields.name, name));
        }
        Specification<Technology> specification = TechnologySpecification.getSpecification(technologyFilters);
        return technologyRepository.findAll(specification).stream().map(technology -> technology.toDto()).toList();
    }
}
