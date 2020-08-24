package com.bnppf.kata;

import com.bnppf.kata.constants.GameConstants;
import com.bnppf.kata.exception.TennisException;
import com.bnppf.kata.interfaces.TennisGameInterface;

public class TennisGame implements TennisGameInterface {
    private String firstPlayerName;
    private String secondPlayerName;
    private int firstPlayerScore;
    private int secondPlayerScore;

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

    public void increasePlayerScore(String pointWinnerPlayer) {
        if (!isPlayerValid(pointWinnerPlayer)) {
            throw new TennisException("Invalid Player Name");
        }

        if (pointWinnerPlayer.equalsIgnoreCase(firstPlayerName)) {
            firstPlayerScore++;
        } else {
            secondPlayerScore++;
        }
    }

    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }

    private boolean isPlayerValid(String playerName) {
        return null != playerName && !"".equals(playerName) && (playerName.equalsIgnoreCase(firstPlayerName) || playerName.equalsIgnoreCase(secondPlayerName));
    }
}
