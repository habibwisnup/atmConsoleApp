package org.habibwisnup.services.interfaces;

import org.habibwisnup.models.Customer;

public interface ICustomerService {
    void deposit(int amount);
    void withdraw(int amount);
    void addDebt(int amount);
    void reduceDebt(int amount);
    Customer getCustomer();
}
