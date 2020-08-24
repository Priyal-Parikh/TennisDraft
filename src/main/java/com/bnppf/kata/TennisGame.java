package com.bnppf.kata;

import com.bnppf.kata.constants.GameConstants;
import com.bnppf.kata.entity.TennisPlayer;
import com.bnppf.kata.exception.TennisException;
import com.bnppf.kata.interfaces.TennisGameInterface;

public class TennisGame implements TennisGameInterface {
    private TennisPlayer firstPlayer;
    private TennisPlayer secondPlayer;

    public TennisGame(TennisPlayer firstPlayer , TennisPlayer secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public TennisPlayer getFirstPlayer() {
        return firstPlayer;
    }

    public TennisPlayer getSecondPlayer() {
        return secondPlayer;
    }

    public String getScore() {
        String currentGameScore;
        String firstPlayerTennisScore = getTennisFormatScore(firstPlayer.getScoredPoint());
        String secondPlayerTennisScore = getTennisFormatScore(secondPlayer.getScoredPoint());

        if (firstPlayerTennisScore.equalsIgnoreCase(secondPlayerTennisScore))
            currentGameScore = firstPlayerTennisScore + GameConstants.TXT_SPACE + GameConstants.TXT_ALL;
        else
            currentGameScore = firstPlayerTennisScore + GameConstants.TXT_COLON + secondPlayerTennisScore;

        return currentGameScore;
    }

    public void increasePlayerScore(String pointWinnerPlayer) {
        if (!isPlayerValid(pointWinnerPlayer)) {
            throw new TennisException(GameConstants.INVALID_PLAYER_NAME);
        }

        if (pointWinnerPlayer.equalsIgnoreCase(firstPlayer.getName())) {
            firstPlayer.setScoredPoint(firstPlayer.getScoredPoint() + 1);
        } else {
            secondPlayer.setScoredPoint(secondPlayer.getScoredPoint() + 1);
        }
    }

    private boolean isPlayerValid(String playerName) {
        return isPlayerExists(playerName) && isPlayerNameCorrect(playerName);
    }

    private boolean isPlayerNameCorrect(String playerName) {
        return playerName.equalsIgnoreCase(firstPlayer.getName()) || playerName.equalsIgnoreCase(secondPlayer.getName());
    }

    private boolean isPlayerExists(String playerName) {
        return null != playerName && !"".equals(playerName);
    }

    private String getTennisFormatScore(int points) {
        String tennisScore = "";

        if (points == GameConstants.ZERO_POINT) {
            tennisScore = GameConstants.SCORE_LOVE;
        } else if (points == GameConstants.ONE_POINT) {
            tennisScore = GameConstants.SCORE_FIFTEEN;
        } else if (points == GameConstants.TWO_POINT) {
            tennisScore = GameConstants.SCORE_THIRTY;
        } else if (points == GameConstants.THREE_POINT) {
            tennisScore = GameConstants.SCORE_FORTY;
        }

        return tennisScore;
    }
}
