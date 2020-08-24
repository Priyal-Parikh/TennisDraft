package com.bnppf.kata;

import com.bnppf.kata.constants.GameConstants;
import com.bnppf.kata.entity.TennisPlayer;
import com.bnppf.kata.enums.TennisScoreEnum;
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

        if (firstPlayer.getScoredPoint() >= GameConstants.THREE_POINT && firstPlayer.getScoredPoint() == secondPlayer.getScoredPoint())
            return GameConstants.SCORE_DEUCE;

        TennisScoreEnum firstPlayerTennisScore = getTennisFormatScore(firstPlayer.getScoredPoint());
        TennisScoreEnum secondPlayerTennisScore = getTennisFormatScore(secondPlayer.getScoredPoint());

        if (firstPlayerTennisScore.getScore().equalsIgnoreCase(secondPlayerTennisScore.getScore()))
            currentGameScore = firstPlayerTennisScore.getScore() + GameConstants.TXT_SPACE + GameConstants.TXT_ALL;
        else
            currentGameScore = firstPlayerTennisScore.getScore() + GameConstants.TXT_COLON + secondPlayerTennisScore.getScore();

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

    private TennisScoreEnum getTennisFormatScore(int points) {
        return TennisScoreEnum.fromScore(points);
    }
}
