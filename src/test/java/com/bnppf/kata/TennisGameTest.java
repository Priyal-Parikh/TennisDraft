package com.bnppf.kata;

import com.bnppf.kata.constants.TestConstants;
import com.bnppf.kata.entity.TennisPlayer;
import com.bnppf.kata.exception.TennisException;
import com.bnppf.kata.interfaces.TennisGameInterface;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
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
        prepareScore(TestConstants.ZERO_POINT , TestConstants.ONE_POINT);
        tennisGame.getScore();

        Assert.assertEquals(TestConstants.LOVE + TestConstants.COLON + TestConstants.FIFTEEN , tennisGame.getScore());
    }

    @Test
    public void scoreShouldReturnFifteenAllIfBothPlayerWinsFirstPoint() {
        prepareScore(TestConstants.ONE_POINT , TestConstants.ONE_POINT);

        Assert.assertEquals(TestConstants.FIFTEEN + TestConstants.SPACE + TestConstants.ALL , tennisGame.getScore());
    }

    @Test
    @Parameters({
            "0, 0, Love All" ,
            "0, 1, Love:Fifteen" ,
            "0, 2, Love:Thirty" ,
            "0, 3, Love:Forty" ,
            "1, 0, Fifteen:Love" ,
            "1, 1, Fifteen All" ,
            "1, 2, Fifteen:Thirty" ,
            "1, 3, Fifteen:Forty" ,
            "2, 0, Thirty:Love" ,
            "2, 1, Thirty:Fifteen" ,
            "2, 2, Thirty All" ,
            "3, 0, Forty:Love" ,
            "3, 1, Forty:Fifteen" ,
            "3, 2, Forty:Thirty"
    })
    public void scoreShouldBeAsPerParameters(int firstPlayerPoints , int secondPlayerPoints , String score) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(score , tennisGame.getScore());
    }

    @Test
    @Parameters({
            "4, 4" ,
            "5, 5" ,
            "15, 15" ,
            "26, 26"
    })
    public void checkForDeuceSituationInGame(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(TestConstants.SCORE_DEUCE , tennisGame.getScore());
    }

    @Test
    @Parameters({
            "3, 4",
            "4, 5" ,
            "5, 6" ,
            "6, 7" ,
            "14, 15" ,
            "27, 28"
    })
    public void checkForAdvantageSituationForPlayerTwo(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(TestConstants.ADVANTAGE + TestConstants.COLON + TestConstants.SECOND_PLAYER , tennisGame.getScore());
    }

    @Test
    @Parameters({
            "5, 4" ,
            "6, 5" ,
            "7, 6" ,
            "15, 14" ,
            "22, 21"
    })
    public void checkForAdvantageSituationForFirstPlayer(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(TestConstants.ADVANTAGE + TestConstants.COLON + TestConstants.FIRST_PLAYER , tennisGame.getScore());
    }

    @Test
    @Parameters({
            "5, 3" ,
            "8, 6" ,
            "15, 13" ,
            "22, 20"
    })
    public void shouldReturnFirstPlayerAsWinner(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(TestConstants.WINNER + TestConstants.COLON + TestConstants.FIRST_PLAYER , tennisGame.getScore());
    }

    @Test
    @Parameters({
            "3, 5" ,
            "0, 4" ,
            "4, 6" ,
            "20, 22"
    })
    public void shouldReturnSecondPlayerAsWinner(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(TestConstants.WINNER + TestConstants.COLON + TestConstants.SECOND_PLAYER , tennisGame.getScore());
    }

    private void prepareScore(int firstPlayerPoints , int secondPlayerPoints) {
        for (int counter = 0; counter < firstPlayerPoints; counter++)
            tennisGame.increasePlayerScore(TestConstants.FIRST_PLAYER);
        for (int counter = 0; counter < secondPlayerPoints; counter++)
            tennisGame.increasePlayerScore(TestConstants.SECOND_PLAYER);
    }
}
