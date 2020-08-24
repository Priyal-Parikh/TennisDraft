package com.bnppf.kata.interfaces;

public interface TennisGameInterface {
    String getScore();
    String getSecondPlayerName();
    String getFirstPlayerName();
    int getFirstPlayerScore();
    int getSecondPlayerScore();
    void increasePlayerScore(String pointWinnerPlayer);
}
