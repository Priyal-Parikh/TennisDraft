package com.bnppf.kata;

import com.bnppf.kata.entity.TennisPlayer;
import com.bnppf.kata.exception.TennisException;
import com.bnppf.kata.interfaces.TennisGameInterface;

import java.util.Scanner;

public class TennisSimulator {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TennisGameInterface tennisGame = startGameWithTwoPlayers();
        playGame(tennisGame);
        scanner.close();
    }

    private static void playGame(TennisGameInterface tennisGame) {
        System.out.println("Game starts!!!");
    try {
        while (!tennisGame.getScore().contains("Winner")) {
            System.out.print("Enter point winner player:");
            String pointScoredByPlayer = scanner.nextLine();
            try {
                tennisGame.increasePlayerScore(pointScoredByPlayer);
            } catch (TennisException e) {
                System.out.println("Exception occurred: " + e.getMessage());
            }
            printScoreBoard(tennisGame);
        }
    }catch (TennisException e){
        System.out.println("1st Player:"+tennisGame.getFirstPlayer().getScoredPoint() +"   2nd PLayer:"+tennisGame.getSecondPlayer().getScoredPoint());
        System.out.println("Exception occurred: " + e.getMessage());
    }

        System.out.println("Game over!");
    }

    private static void printScoreBoard(TennisGameInterface tennisGame) {
        System.out.println("/////////////////////////////////////////////////////");
        System.out.println(" " + tennisGame.getScore());
        System.out.println("/////////////////////////////////////////////////////");
    }

    private static TennisGameInterface startGameWithTwoPlayers() {
        String firstPlayer;
        String secondPlayer;

        do {
            System.out.println("Kindly enter two valid player names to start the game.");

            System.out.print("Player 1 name : ");
            firstPlayer = scanner.nextLine().trim();

            System.out.print("Player 2 name : ");
            secondPlayer = scanner.nextLine().trim();

        } while ("".equals(firstPlayer) || "".equals(secondPlayer) || firstPlayer.equalsIgnoreCase(secondPlayer));

        return new TennisGame(new TennisPlayer(firstPlayer) , new TennisPlayer(secondPlayer));
    }
}
