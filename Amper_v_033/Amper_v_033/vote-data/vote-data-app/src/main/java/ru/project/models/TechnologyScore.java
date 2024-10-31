package ru.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "score")
public class TechnologyScore {
    @Id
    private int technologyId;
    @Column
    private double score;
    @Column
    private int numberOfVotes;
    public TechnologyScore(int technologyId, double score, int numberOfVotes) {
        this.technologyId = technologyId;
        this.score = score;
        this.numberOfVotes = numberOfVotes;
    }
}
