package com.bnppf.kata;

import com.bnppf.kata.constants.TestConstants;
import com.bnppf.kata.entity.TennisPlayer;
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
        tennisGame = new TennisGame(new TennisPlayer(TestConstants.FIRST_PLAYER) , new TennisPlayer(TestConstants.SECOND_PLAYER));
    }

    @Test
    public void initializeNewTennisGame() {
        Assert.assertNotNull(tennisGame);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Assert.assertEquals(TestConstants.FIRST_PLAYER , tennisGame.getFirstPlayer().getName());
        Assert.assertEquals(TestConstants.SECOND_PLAYER , tennisGame.getSecondPlayer().getName());
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        Assert.assertEquals(TestConstants.LOVE + TestConstants.SPACE + TestConstants.ALL , tennisGame.getScore());
    }

    @Test
    public void firstPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennisGame.increasePlayerScore(TestConstants.FIRST_PLAYER);

        Assert.assertEquals(TestConstants.ONE_POINT , tennisGame.getFirstPlayer().getScoredPoint());
    }

    @Test
    public void secondPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennisGame.increasePlayerScore(TestConstants.SECOND_PLAYER);

        Assert.assertEquals(TestConstants.ONE_POINT , tennisGame.getSecondPlayer().getScoredPoint());
    }

    @Test
    public void shouldThrowAnExceptionIfNameIsNotCorrect() {
        exceptionRule.expect(TennisException.class);
        exceptionRule.expectMessage(TestConstants.INVALID_PLAYER);

        tennisGame.increasePlayerScore(TestConstants.RANDOM_PLAYER);
    }

    @Test
    public void scoreShouldBeLoveFifteenIfSecondPlayerScoresPoint() {
        tennisGame.increasePlayerScore(TestConstants.SECOND_PLAYER);
        tennisGame.getScore();

        Assert.assertEquals(TestConstants.LOVE + TestConstants.COLON + TestConstants.FIFTEEN , tennisGame.getScore());
    }

    @Test
    public void scoreShouldReturnFifteenAllIfBothPlayerWinsFirstPoint() {
        tennisGame.increasePlayerScore(TestConstants.FIRST_PLAYER);
        tennisGame.increasePlayerScore(TestConstants.SECOND_PLAYER);

        Assert.assertEquals(TestConstants.FIFTEEN + TestConstants.SPACE + TestConstants.ALL , tennisGame.getScore());
    }
}
