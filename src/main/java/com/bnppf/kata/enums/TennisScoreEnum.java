package com.bnppf.kata.enums;

import java.util.Arrays;

public enum TennisScoreEnum {
    LOVE("Love" , 0), FIFTEEN("Fifteen" , 1), THIRTY("Thirty" , 2), FORTY("Forty" , 3);

    private String score;
    int point;

    TennisScoreEnum(String score , int point) {
        this.score = score;
        this.point = point;
    }

    public static TennisScoreEnum fromScore(int point) {
        return Arrays.stream(TennisScoreEnum.values()).filter(tennisScore -> tennisScore.point == point).findFirst().orElse(null);
    }

    public String getScore() {
        return score;
    }
}
