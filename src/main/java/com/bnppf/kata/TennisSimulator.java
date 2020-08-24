package com.bnppf.kata;

import com.bnppf.kata.entity.TennisPlayer;
import com.bnppf.kata.exception.TennisException;
import com.bnppf.kata.interfaces.TennisGameInterface;

import java.util.Scanner;

public class TennisSimulator {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TennisGameInterface tennisGame = startGameWithTwoPlayers();
        System.out.println("Game starts!!!");

        while (!tennisGame.getScore().contains("Winner")) {
            System.out.print("Point scored by :");
            String pointScoredByPlayer = scanner.nextLine();
            try {
                tennisGame.increasePlayerScore(pointScoredByPlayer);
            } catch (TennisException e) {
                System.out.println("Exception occurred: " + e.getMessage());
            }
            System.out.println("/////////////////////////////////////////////////////");
            System.out.println(" "+tennisGame.getScore());
            System.out.println("/////////////////////////////////////////////////////");
        }

        System.out.println("Game over!");
        scanner.close();
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
