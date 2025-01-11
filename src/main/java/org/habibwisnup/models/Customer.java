package org.habibwisnup.models;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private final String name;
    private int balance;
    private int debt;

    private final Map<String, Integer> debtors = new HashMap<>();

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

    public Map<String, Integer> getDebtors(){
        return this.debtors;
    }

    public int getDebtToPay(String Name){
        return this.debtors.getOrDefault(Name,0);
    }

    public void addDebtFromCustomer(String debtorName, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Debt amount must be positive.");
        }
        debtors.put(debtorName, debtors.getOrDefault(debtorName, 0) + amount);
    }

    public void reduceDebtFromCustomer(String debtorName, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Debt amount must be positive.");
        }
        debtors.put(debtorName, debtors.getOrDefault(debtorName, 0) - amount);
    }

    public void displayDebts(String key) {
        if (!debtors.isEmpty()) {
            if (key != null || key != "") {
                System.out.println("Owed $" + debtors.get(key) + " to " + key);
            } else {
                for (Map.Entry<String, Integer> entry : debtors.entrySet()) {
                    System.out.println("Owed by " + entry.getKey() + ": $" + entry.getValue());
                }
            }


        }
    }
}
