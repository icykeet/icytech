package ru.project.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.models.VoteInfo;

import java.util.List;

@Repository
public interface VotingRepository extends JpaRepository<VoteInfo, Integer> {
    boolean existsByUserIdAndTechnologyId(int userId, int technologyId);
    VoteInfo findByUserIdAndTechnologyId(int userId, int technologyId);
    List<VoteInfo> findByTechnologyId(int technologyId);
    void deleteAllByTechnologyId(int technologyId);
}