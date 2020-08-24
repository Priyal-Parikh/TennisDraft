package com.bnppf.kata;

import com.bnppf.kata.entity.TennisPlayer;
import com.bnppf.kata.interfaces.TennisGameInterface;

import java.util.Scanner;

public class TennisSimulator {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TennisGameInterface tennisGame = startGameWithTwoPlayers();
        System.out.println("Game starts with score as :" + tennisGame.getScore());
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
