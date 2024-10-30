package ru.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.models.TechnologyScore;

@Repository
public interface TechnologyScoreRepository extends JpaRepository<TechnologyScore, Integer> {
}
