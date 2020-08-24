package com.bnppf.kata;

import com.bnppf.kata.constants.GameConstants;
import com.bnppf.kata.interfaces.TennisGameInterface;

public class TennisGame implements TennisGameInterface {
    private String firstPlayerName;
    private String secondPlayerName;
    private int firstPlayerScore;

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

    public void increaseAPointForFirstPlayer() {
        firstPlayerScore++;
    }

    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }
}
