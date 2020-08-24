package com.bnppf.kata;

import com.bnppf.kata.constants.GameConstants;

public class TennisGame {
    private String firstPlayerName;
    private String secondPlayerName;

    public TennisGame(String firstPlayerName , String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public String getScore() {
        return GameConstants.SCORE_LOVE + GameConstants.TXT_SPACE + GameConstants.TXT_ALL;
    }
}
