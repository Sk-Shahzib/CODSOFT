package com.game;

import java.util.Random;
import java.util.Scanner;

public class GuessNumGame {
    private Random ran = new Random();
    private Scanner sc = new Scanner(System.in);
    private int score = 0;

    public void startGame() {
        boolean playAgain = true;
        while (playAgain) {
            playRound();
            System.out.print("Do you want to play again? (yes/no): ");
            String playChoice = sc.next().toLowerCase();
            if (!playChoice.equals("yes")) {
                playAgain = false;
            }
        }
        System.out.println("Thanks for playing! Your final score is: " + score);
        sc.close(); // Close scanner to release resources
    }

    private void playRound() {
        int systemGenNumber = chooseRange();
        int attempts = 0;
        int maxAttempts = 5;

        while (attempts < maxAttempts) {
            System.out.print("Guess the number (attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
            int userGuessNumber = userGuessNum();

            attempts++;

            if (systemGenNumber == userGuessNumber) {
                System.out.println("Well Done! You guessed the correct number");
                System.out.println("Generated Num: " + systemGenNumber + "\n" + "Guessed Num: " + userGuessNumber);
                System.out.println("Number of attempts: " + attempts);
                score += maxAttempts - attempts + 1; // Increase score based on attempts left
                break;
            } else if (systemGenNumber > userGuessNumber) {
                System.out.println("My number is greater than: " + userGuessNumber);
            } else {
                System.out.println("My number is less than: " + userGuessNumber);
            }

            if (attempts == maxAttempts) {
                System.out.println("You've run out of attempts. The correct number was: " + systemGenNumber);
            }
        }
    }

    private int userGuessNum() {
        int num = 0;
        while (true) {
            try {
                num = sc.nextInt();
                break; // Exit loop if input is valid
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // Clear scanner buffer
            }
        }
        return num;
    }

    private int randomNumGen(int range) {
        return ran.nextInt(range);
    }

    private int chooseRange() {
        System.out.println("Select Target (range) which you want:");
        System.out.println("Enter - A for (0-10)");
        System.out.println("Enter - B for (0-100)");
        System.out.println("Enter - C for (0-1000)");
        char choose = sc.next().charAt(0);
        sc.nextLine(); // Consume newline

        System.out.println("Your chosen Option is: " + choose);
        int generatedNum = -1;
        switch (choose) {
            case 'A', 'a':
                generatedNum = randomNumGen(11); // Range 0-10 (inclusive)
                break;
            case 'B', 'b':
                generatedNum = randomNumGen(101); // Range 0-100
                break;
            case 'C', 'c':
                generatedNum = randomNumGen(1001); // Range 0-1000
                break;
            default:
                System.out.println("Invalid option. Please choose A, B, or C.");
                generatedNum = chooseRange(); // Recursive call in case of invalid input
                break;
        }
        return generatedNum;
    }

    public static void main(String[] args) {
        GuessNumGame game = new GuessNumGame();
        game.startGame();
    }
}
