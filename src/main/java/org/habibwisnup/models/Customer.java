package org.habibwisnup.models;

public class Customer {
    private final String name;
    private int balance;
    private int debt;

    public Customer(String name) {
        this.name = name;
        this.balance = 0;
        this.debt = 0;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }
}
