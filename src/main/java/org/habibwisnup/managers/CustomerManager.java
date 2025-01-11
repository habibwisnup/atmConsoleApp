package org.habibwisnup.managers;

import org.habibwisnup.services.impl.CustomerService;
import org.habibwisnup.utils.MessageConstant;
import org.habibwisnup.utils.errorHandler.exceptions.GeneralException;

import java.util.HashMap;
import java.util.Map;

public class CustomerManager {
    private CustomerService currentCustomer;
    private final Map<String, CustomerService> customers = new HashMap<>();

    public CustomerService getCurrentCustomer() {
        if (currentCustomer == null) throw new GeneralException(MessageConstant.USER_NOT_LOGIN_MESSAGE);
        return currentCustomer;
    }

    public CustomerService getCustomer(String name) {
        return customers.get(name);
    }
}
