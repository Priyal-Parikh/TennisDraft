package com.bnppf.kata.interfaces;

import com.bnppf.kata.entity.TennisPlayer;

public interface TennisGameInterface {
    String getScore();
    void increasePlayerScore(String pointWinnerPlayer);
    TennisPlayer getFirstPlayer();
    TennisPlayer getSecondPlayer();
}
