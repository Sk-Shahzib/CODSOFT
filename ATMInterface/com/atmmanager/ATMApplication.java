package com.atmmanager;

import java.util.Scanner;

public class ATMApplication {
    private ATM atm;
    private Scanner sc;
    private static final int MAX_ATTEMPTS = 3;

    public ATMApplication(ATM atm, Scanner sc) {
        this.atm = atm;
        this.sc = sc;
    }

    private double getValidAmount() {
        System.out.println("Enter amount:");

        double userAmount;
        while (true) {
            try {
                userAmount = Double.parseDouble(sc.nextLine());
                if (userAmount > 0) {
                    break;
                } else {
                    System.out.println("Entered amount is negative or zero. Please enter a positive amount.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid numeric value (Amount)");
            }
        }
        return userAmount;
    }

    private void handleWithdraw() {
        System.out.println("\nEnter amount to withdraw");
        double withAmount = getValidAmount();
        atm.withdraw(withAmount);
    }

    private void handleDeposit() {
        System.out.println("\nEnter amount to deposit");
        double withAmount = getValidAmount();
        atm.deposit(withAmount);
    }

    private int getMenuChoice() {
        System.out.println("\n========================");
        System.out.println(" Choose an option (1-3) ");
        System.out.println("========================");
        System.out.println("| 1 | Withdraw Money    |");
        System.out.println("| 2 | Deposit Money     |");
        System.out.println("| 3 | Check Balance     |");
        System.out.println("========================");

        int choice = -1;
        while (true) {
            try {
                choice = Integer.parseInt(sc.nextLine());

                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Please enter a valid option (1-3).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1-3).");
            }
        }
        return choice;
    }

    private void menu() {
        int choice = getMenuChoice();

        switch (choice) {
            case 1:
                handleWithdraw();
                break;
            case 2:
                handleDeposit();
                break;
            case 3:
                System.out.println("Available Balance: " + atm.checkBalance());
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
                choice = getMenuChoice();
        }
    }

    private int getPin() {
        System.out.println("Enter PIN:");
        int userPin = -1;

        while (true) {
            try {
                String input = sc.nextLine();
                userPin = Integer.parseInt(input);
                if (userPin > 0) {
                    break;
                } else {
                    System.out.println("PIN must be a positive number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid numeric value.");
            }
        }

        return userPin;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount bankAccount = new BankAccount();
        ATM atm = new ATM(bankAccount);
        ATMApplication atmApp = new ATMApplication(atm, sc);
        int pin = atmApp.getPin();

        boolean authentic = false;
        int attempts = 0;
        if (pin != -1) {
            while (attempts < MAX_ATTEMPTS) {
                attempts++;
                authentic = atm.checkAuthentication(pin);
                if (authentic) {
                    break;
                } else {
                    System.out.println("Incorrect PIN. Try again.");
                    if (attempts < MAX_ATTEMPTS) {
                        pin = atmApp.getPin();
                    }
                }
            }

            if (!authentic) {
                System.out.println("You have exceeded the maximum number of attempts. Please try again later.");
            } else {
                do {
                    atmApp.menu();
                    System.out.println("\nDo you want to perform another operation? Enter Y to continue or N to exit:");
                    char ch = sc.nextLine().toLowerCase().charAt(0);
                    if (ch == 'y') {

                    } else if (ch == 'n') {
                        System.out.println("\n=======================================");
                        System.out.println("          THANKS FOR VISITING          ");
                        System.out.println("=======================================\n");
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter Y or N.");
                    }
                } while (true);
            }
        }

        sc.close();

    }
}
