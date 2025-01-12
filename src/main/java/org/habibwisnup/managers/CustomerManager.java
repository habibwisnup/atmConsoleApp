package org.habibwisnup.managers;

import org.habibwisnup.models.Customer;
import org.habibwisnup.services.impl.CustomerService;
import org.habibwisnup.utils.MessageConstant;
import org.habibwisnup.utils.errorHandler.exceptions.GeneralException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CustomerManager {
    private CustomerService currentCustomer;
    private final Map<String, CustomerService> customers = new HashMap<>();
    private final Map<String, Map<String, Integer>> debtorsData = new HashMap<>();

    public void login(String name) {
        if (currentCustomer != null) {
            System.out.println(MessageConstant.USER_ALREADY_LOGIN);
            return;
        }
        CustomerService customerService = customers.computeIfAbsent(name, k -> new CustomerService(new Customer(name)));
        currentCustomer = customerService;

        if(!currentCustomer.getCustomer().getDebtors().isEmpty()) {
            updateDebt(currentCustomer.getCustomer().getName(), currentCustomer.getCustomer().getDebtors());
        }

        System.out.println("Hello, " + currentCustomer.getCustomer().getName() + "!");
        System.out.println("Your balance is $" + currentCustomer.getCustomer().getBalance());
        findWhoOwesTo(currentCustomer.getCustomer().getName());
    }

    public void logout() {
        if (currentCustomer == null) {
            System.out.println(MessageConstant.USER_NOT_LOGIN_MESSAGE);
            return;
        }

        if(!currentCustomer.getCustomer().getDebtors().isEmpty()) {
            updateDebt(currentCustomer.getCustomer().getName(), currentCustomer.getCustomer().getDebtors());
        }
        System.out.println("Goodbye, " + currentCustomer.getCustomer().getName() + "!");
        currentCustomer = null;
    }

    public CustomerService getCurrentCustomer() {
        if (currentCustomer == null) throw new GeneralException(MessageConstant.USER_NOT_LOGIN_MESSAGE);
        return currentCustomer;
    }

    public boolean isLoggedIn() {
        return currentCustomer != null;
    }

    public CustomerService getCustomer(String name) {
        if (currentCustomer == null) {
            return null;
        }

        return customers.get(name);
    }

    private void updateDebt(String creditor, Map<String, Integer> debtors){
        debtorsData.put(creditor, debtors);
    }

    private void findWhoOwesTo(String debtor) {
        for (Map.Entry<String, Map<String, Integer>> entry : debtorsData.entrySet()) {
            String creditor = entry.getKey();
            Map<String, Integer> debtors = entry.getValue();

            if (debtors.containsKey(debtor)) {
                int amountOwed = debtors.get(debtor);
                if (amountOwed > 0)
                    System.out.println("Owed " + "$" + amountOwed + " from " + creditor);
            }
        }

        Map<String, Integer> debtors = debtorsData.get(debtor);
        if (debtors != null) {
            for (Map.Entry<String, Integer> entry : debtors.entrySet()) {
                String creditor = entry.getKey();
                int amountOwed = entry.getValue();
                if (amountOwed > 0)
                    System.out.println("Owed $" + amountOwed + " to " + creditor);
            }
        }
    }
}
