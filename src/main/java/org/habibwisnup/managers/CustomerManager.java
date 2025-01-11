package org.habibwisnup.managers;

import org.habibwisnup.models.Customer;
import org.habibwisnup.services.impl.CustomerService;
import org.habibwisnup.utils.MessageConstant;
import org.habibwisnup.utils.errorHandler.exceptions.GeneralException;

import java.util.HashMap;
import java.util.Map;

public class CustomerManager {
    private CustomerService currentCustomer;
    private final Map<String, CustomerService> customers = new HashMap<>();

    public void login(String name) {
        if (currentCustomer != null) {
            System.out.println(MessageConstant.USER_ALREADY_LOGIN);
            return;
        }
        CustomerService customerService = customers.computeIfAbsent(name, k -> new CustomerService(new Customer(name)));
        currentCustomer = customerService;

        System.out.println("Hello, " + currentCustomer.getCustomer().getName() + "!");
        System.out.println("Your balance is $" + currentCustomer.getCustomer().getBalance());
    }

    public void logout() {
        if (currentCustomer == null) {
            System.out.println(MessageConstant.USER_NOT_LOGIN_MESSAGE);
            return;
        }
        System.out.println("Goodbye, " + currentCustomer.getCustomer().getName() + "!");
        currentCustomer = null;
    }

    public CustomerService getCurrentCustomer() {
        if (currentCustomer == null) throw new GeneralException(MessageConstant.USER_NOT_LOGIN_MESSAGE);
        return currentCustomer;
    }

    public CustomerService getCustomer(String name) {
        return customers.get(name);
    }
}
