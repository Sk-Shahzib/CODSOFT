package com.atmmanager;

public interface ATMOperation {
    void withdraw(double amount);

    void deposit(double amount);

    boolean checkAuthentication(int pin);

    double checkBalance();
}
