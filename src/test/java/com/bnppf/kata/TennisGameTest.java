package com.bnppf.kata;

import com.bnppf.kata.constants.TestConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TennisGameTest {
    TennisGame tennisGame;

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
}
