package org.habibwisnup.managers;

import org.habibwisnup.services.impl.CustomerService;
import org.habibwisnup.utils.errorHandler.exceptions.GeneralException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class CustomerManagerTest {
    private CustomerManager customerManager;
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        customerManager = new CustomerManager();
        customerService = mock(CustomerService.class);
    }

    @Test
    void testLoginSuccessfully() {
        String customerName = "Alice";
        customerManager.login(customerName);

        assertTrue(customerManager.isLoggedIn());
        assertEquals(customerName, customerManager.getCurrentCustomer().getCustomer().getName());
    }

    @Test
    void testLoginWhenAlreadyLoggedIn() {
        String customerName = "Alice";
        customerManager.login(customerName);

        customerManager.login(customerName);

        assertTrue(customerManager.isLoggedIn());
        verifyNoMoreInteractions(customerService);
    }

    @Test
    void testLogoutSuccessfully() {
        String customerName = "Alice";
        customerManager.login(customerName);

        customerManager.logout();
        assertFalse(customerManager.isLoggedIn());
    }

    @Test
    void testLogoutWhenNoUserLoggedIn() {
        customerManager.logout();
        assertFalse(customerManager.isLoggedIn());
    }

    @Test
    void testGetCurrentCustomerWhenNotLoggedIn() {
        assertThrows(GeneralException.class, () -> customerManager.getCurrentCustomer(), "User not logged in");
    }

    @Test
    void testIsLoggedIn() {
        assertFalse(customerManager.isLoggedIn());

        String customerName = "Alice";
        customerManager.login(customerName);

        assertTrue(customerManager.isLoggedIn());
    }

    @Test
    void testGetCustomer() {
        String customerName = "Alice";
        customerManager.login(customerName);

        CustomerService customer = customerManager.getCustomer(customerName);
        assertNotNull(customer);

        customerManager.logout();
        assertNull(customerManager.getCustomer(customerName));
    }
}