package com.bnppf.kata;

import com.bnppf.kata.constants.TestConstants;
import com.bnppf.kata.exception.TennisException;
import com.bnppf.kata.interfaces.TennisGameInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TennisGameTest {
    TennisGameInterface tennisGame;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

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
        tennisGame.increasePlayerScore(TestConstants.FIRST_PLAYER);

        Assert.assertEquals(TestConstants.ONE_POINT , tennisGame.getFirstPlayerScore());
    }

    @Test
    public void secondPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennisGame.increasePlayerScore(TestConstants.SECOND_PLAYER);

        Assert.assertEquals(TestConstants.ONE_POINT , tennisGame.getSecondPlayerScore());
    }

    @Test
    public void shouldThrowAnExceptionIfNameIsNotCorrect() {
        exceptionRule.expect(TennisException.class);
        exceptionRule.expectMessage("Invalid Player Name");

        tennisGame.increasePlayerScore("Random Player");
    }
}
