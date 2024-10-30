package ru.project.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Technology")
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private TechnologyType technologyType;
    @Column
    private String link;
    @Column
    private String version;
    @Column
    private Rang rang;
    public Technology(String name, TechnologyType technologyType, String link, String version, Rang rang) {
        this.name = name;
        this.link = link;
        this.version = version;
        this.rang = rang;
        this.technologyType = technologyType;
    }
}