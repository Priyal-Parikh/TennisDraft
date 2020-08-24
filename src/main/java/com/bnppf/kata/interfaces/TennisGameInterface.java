package com.bnppf.kata.interfaces;

public interface TennisGameInterface {
    String getScore();
    String getSecondPlayerName();
    String getFirstPlayerName();
    void increaseAPointForFirstPlayer();
    int getFirstPlayerScore();
    void increaseAPointForSecondPlayer();
    int getSecondPlayerScore();
}
