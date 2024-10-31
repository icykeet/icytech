package ru.project.models;

public enum Rang {
    HOLD(0),
    ACCESS(1),
    TRIAL(2),
    ADOPT(3);
    Rang(int score) { this.score = score; }
    private final int score;
    public int GetScore() {
        return score;
    }
}