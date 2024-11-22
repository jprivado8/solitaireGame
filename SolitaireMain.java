package com.svi.training.main;

import com.svi.training.game.Game;

import java.util.Scanner;

/**
 * Main class to start the self-playing Solitaire game.
 */
public class SolitaireMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean rerun = true;

        while (rerun) {
            // Start a new self-playing game
            Game game = new Game();
            game.play();

            // Ask if the user wants to re-run the program
            System.out.print("Do you want to re-run the program? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            // Determine whether to continue or exit
            if (!response.equals("yes")) {
                rerun = false;
            }
        }

        System.out.println("Program has ended. Thank you!");
        scanner.close();
    }
}
