package com.atmmanager;

public class BankAccount {
    private double AcBalance;

    public BankAccount() {
        this.AcBalance = 100000.00;
    }

    public BankAccount(double Ac_Balance) {
        this.AcBalance = Ac_Balance;
    }

    public double getAcBalance() {
        return AcBalance;
    }

    public void setAcBalance(double Ac_Balance) {
        this.AcBalance = Ac_Balance;
    }
}
