package ru.project.filters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TechnologyFilter {
    private TechnologyFields technologyFields;
    private String value;
}
