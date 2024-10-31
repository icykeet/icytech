package ru.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "vote")
public class VoteInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int userId;
    @Column
    private int technologyId;
    @Column
    private Rang rang;
    public VoteInfo(int userId, int technologyId, Rang rang) {
        this.userId = userId;
        this.technologyId = technologyId;
        this.rang = rang;
    }
}
