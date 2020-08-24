package com.bnppf.kata;

import com.bnppf.kata.constants.TestConstants;
import com.bnppf.kata.interfaces.TennisGameInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TennisGameTest {
    TennisGameInterface tennisGame;

    @Before
    public void initialSetup() {
        tennisGame = new TennisGame(TestConstants.FIRST_PLAYER , TestConstants.SECOND_PLAYER);
    }

    @Test
    public void initializeNewTennisGame() {
        Assert.assertNotNull(tennisGame);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Assert.assertEquals(TestConstants.FIRST_PLAYER , tennisGame.getFirstPlayerName());
        Assert.assertEquals(TestConstants.SECOND_PLAYER , tennisGame.getSecondPlayerName());
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        Assert.assertEquals(TestConstants.LOVE + TestConstants.SPACE + TestConstants.ALL , tennisGame.getScore());
    }

    @Test
    public void firstPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennisGame.increaseAPointForFirstPlayer();

        Assert.assertEquals(1 , tennisGame.getFirstPlayerScore());
    }
}
