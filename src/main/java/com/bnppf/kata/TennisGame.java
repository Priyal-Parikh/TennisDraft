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

        if (checkForDeuce()) {
            currentGameScore = GameConstants.SCORE_DEUCE;
        } else if (checkForAdvantage()) {
            return GameConstants.SCORE_ADVANTAGE + GameConstants.TXT_COLON + getHighScorer();
        } else if ((secondPlayer.getScoredPoint() > GameConstants.THREE_POINT || firstPlayer.getScoredPoint() > GameConstants.THREE_POINT) && pointDifference() > GameConstants.ONE_POINT) {
            currentGameScore = GameConstants.SCORE_WINNER + GameConstants.TXT_COLON + getHighScorer();
        } else {
            currentGameScore = formatScore();
        }

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

    private String getHighScorer() {
        return firstPlayer.getScoredPoint() > secondPlayer.getScoredPoint() ? firstPlayer.getName() : secondPlayer.getName();
    }

    private boolean checkForAdvantage() {
        return isBothPlayersBeyondForty() && pointDifference() == GameConstants.ONE_POINT;
    }

    private int pointDifference() {
        return Math.abs(secondPlayer.getScoredPoint() - firstPlayer.getScoredPoint());
    }

    private boolean isBothPlayersBeyondForty() {
        return secondPlayer.getScoredPoint() > GameConstants.THREE_POINT && firstPlayer.getScoredPoint() > GameConstants.THREE_POINT;
    }

    private String formatScore() {
        TennisScoreEnum firstPlayerTennisScore = getTennisFormatScore(firstPlayer.getScoredPoint());
        TennisScoreEnum secondPlayerTennisScore = getTennisFormatScore(secondPlayer.getScoredPoint());

        return isSameScore() ?
                firstPlayerTennisScore.getScore() + GameConstants.TXT_SPACE + GameConstants.TXT_ALL :
                firstPlayerTennisScore.getScore() + GameConstants.TXT_COLON + secondPlayerTennisScore.getScore();
    }

    private boolean checkForDeuce() {
        return isScoreBeyondThirty() && isSameScore();
    }

    private boolean isSameScore() {
        return firstPlayer.getScoredPoint() == secondPlayer.getScoredPoint();
    }

    private boolean isScoreBeyondThirty() {
        return firstPlayer.getScoredPoint() >= GameConstants.THREE_POINT;
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
