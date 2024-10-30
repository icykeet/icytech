package ru.project.filters;

import org.springframework.data.jpa.domain.Specification;
import ru.project.models.Technology;

import java.util.List;

public class TechnologySpecification {
    public static Specification<Technology> createSpecification(TechnologyFilter technologyFilter) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(String.valueOf(technologyFilter.getTechnologyFields())),
                        CastToType.castToType(technologyFilter.getValue(), root.get(String.valueOf(technologyFilter.getTechnologyFields())).getJavaType()));
    }

    public static Specification<Technology> getSpecification(List<TechnologyFilter> technologyFilters) {
        if (technologyFilters == null || technologyFilters.isEmpty()) {
            return null;
        }
        Specification<Technology> specification = createSpecification(technologyFilters.getFirst());
        technologyFilters.remove(technologyFilters.getFirst());
        for (TechnologyFilter technologyFilter : technologyFilters) {
            specification = specification.and(createSpecification(technologyFilter));
        }
        return specification;
    }
}
