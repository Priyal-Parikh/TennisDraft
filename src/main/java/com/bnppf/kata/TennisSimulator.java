package com.bnppf.kata;

import java.util.Scanner;

public class TennisSimulator {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String firstPlayer;
        String secondPlayer;

        do {
            System.out.println("Kindly enter two valid player names to start the game.");

            System.out.print("Player 1 name : ");
            firstPlayer = scanner.nextLine().trim();

            System.out.print("Player 2 name : ");
            secondPlayer = scanner.nextLine().trim();

        } while ("".equals(firstPlayer) || "".equals(secondPlayer) || firstPlayer.equalsIgnoreCase(secondPlayer));

        scanner.close();
    }
}
