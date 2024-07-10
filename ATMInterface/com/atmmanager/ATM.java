package com.atmmanager;

public class ATM implements ATMOperation {
    private static final int PIN = 332211;
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public ATM() {
    }

    @Override
    public boolean checkAuthentication(int pin) {
        if (pin == PIN) {
            System.out.println("Authentication successful.");
            return true;
        } else {
            System.out.println("Authentication failed. Wrong PIN entered");
            return false;
        }
    }

    @Override
    public void withdraw(double amount) {
        double availBalance = bankAccount.getAcBalance();

        if (availBalance >= amount) {
            availBalance -= amount;
            bankAccount.setAcBalance(availBalance);
            System.out.println("Withdrawal successful");
        } else if (availBalance < amount) {
            System.out.println("Insufficient balance: ₹" + availBalance);
        } else if (amount == 0) {
            System.out.println("Amount must be greater than 0");
        }
    }

    @Override
    public void deposit(double amount) {
        double currentBalance = bankAccount.getAcBalance();
        currentBalance += amount;
        bankAccount.setAcBalance(currentBalance);
        System.out.println("Deposit successful");
    }

    @Override
    public double checkBalance() {
        return bankAccount.getAcBalance();
    }
}
