package ru.project.services;

import org.springframework.stereotype.Service;
import ru.project.models.TechnologyScore;
import ru.project.models.Rang;
import ru.project.models.TechnologyDto;
import ru.project.models.VoteInfo;
import ru.project.repositories.TechnologyScoreRepository;
import ru.project.repositories.VotingRepository;

import java.util.List;

@Service
public class VotingService {
    private final VotingRepository votingRepository;
    private final TechnologyScoreRepository technologyScoreRepository;

    public VotingService(VotingRepository votingRepository, TechnologyScoreRepository technologyScoreRepository) {
        this.votingRepository = votingRepository;
        this.technologyScoreRepository = technologyScoreRepository;
    }
    public String vote(int userId, int technologyId, String rang) {
        if (votingRepository.existsByUserIdAndTechnologyId(userId, technologyId)) {
            VoteInfo voteInfo = votingRepository.findByUserIdAndTechnologyId(userId, technologyId);
            voteInfo.setRang(Rang.valueOf(rang));
            votingRepository.save(voteInfo);
        } else {
            VoteInfo voteInfo = new VoteInfo(userId, technologyId, Rang.valueOf(rang));
            votingRepository.save(voteInfo);
        }

        return countRang(technologyId).name();
    }

    public void registerNewTechnology(TechnologyDto technologyDto) {
        technologyScoreRepository.save(
                new TechnologyScore(technologyDto.getId(),
                        Rang.valueOf(technologyDto.getRang()).GetScore(),
                        0));
    }

    public void removeTechnology(int technologyId) {
        technologyScoreRepository.deleteById(technologyId);
        votingRepository.deleteAllByTechnologyId(technologyId);
    }

    private Rang countRang(int technologyId) {
        TechnologyScore technologyScore = technologyScoreRepository.getReferenceById(technologyId);
        List<VoteInfo> voteInfos = votingRepository.findByTechnologyId(technologyId);
        int numberOfVotes = voteInfos.size();
        double sum = 0;
        for (VoteInfo voteInfo : voteInfos) {
            sum += voteInfo.getRang().GetScore();
        }
        double score = sum / numberOfVotes;
        technologyScore.setScore(score);
        technologyScore.setNumberOfVotes(numberOfVotes);
        technologyScoreRepository.save(technologyScore);
        if (score > 2) {
            return Rang.ADOPT;
        } else if (score > 1) {
            return Rang.TRIAL;
        } else if (score > 0) {
            return Rang.ACCESS;
        } else {
            return Rang.HOLD;
        }
    }
}
