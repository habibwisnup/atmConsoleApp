package org.habibwisnup.services.impl;

import org.habibwisnup.models.Customer;
import org.habibwisnup.services.interfaces.ICustomerService;

public class CustomerService implements ICustomerService {
    private final Customer customer;

    public CustomerService(Customer customer){
        this.customer = customer;
    }
    @Override
    public void deposit(int amount) {
        customer.setBalance(customer.getBalance() + amount);
    }

    @Override
    public void withdraw(int amount) {
        if (amount > customer.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        customer.setBalance(customer.getBalance() - amount);
    }

    @Override
    public void addDebt(int amount) {
        customer.setDebt(customer.getDebt() + amount);
    }

    @Override
    public void reduceDebt(int amount) {
        customer.setDebt(customer.getDebt() - amount);
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }
}
