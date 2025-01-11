package org.habibwisnup.services.impl;

import org.habibwisnup.utils.MessageConstant;
import org.habibwisnup.models.Customer;
import org.habibwisnup.services.interfaces.ICustomerService;
import org.habibwisnup.utils.errorHandler.ErrorHandler;
import org.habibwisnup.utils.errorHandler.exceptions.InsufficientBalanceException;
import org.habibwisnup.utils.errorHandler.exceptions.InvalidAmountException;

public class CustomerService implements ICustomerService {
    private final Customer customer;

    public CustomerService(Customer customer){
        this.customer = customer;
    }
    @Override
    public void deposit(int amount) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException(MessageConstant.INVALID_AMOUNT_MESSAGE);
            }
            customer.setBalance(customer.getBalance() + amount);
        } catch (Exception e) {
            ErrorHandler.handleException(e);
        }
    }

    @Override
    public void withdraw(int amount) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException(MessageConstant.INVALID_AMOUNT_MESSAGE);
            }
            if (amount > customer.getBalance()) {
                throw new InsufficientBalanceException(MessageConstant.INSUFFICIENT_BALANCE_MESSAGE);
            }
            customer.setBalance(customer.getBalance() - amount);
        } catch (Exception e) {
            ErrorHandler.handleException(e);
        }
    }

    @Override
    public void addDebt(int amount) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException(MessageConstant.INVALID_AMOUNT_MESSAGE);
            }
            customer.setDebt(customer.getDebt() + amount);
        } catch (Exception e) {
            ErrorHandler.handleException(e);
        }
    }

    @Override
    public void reduceDebt(int amount) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException(MessageConstant.INVALID_AMOUNT_MESSAGE);
            }
            customer.setDebt(customer.getDebt() - amount);
        } catch (Exception e) {
            ErrorHandler.handleException(e);
        }
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }
}
