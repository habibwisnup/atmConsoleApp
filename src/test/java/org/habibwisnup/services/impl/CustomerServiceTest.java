package org.habibwisnup.services.impl;

import org.habibwisnup.models.Customer;
import org.habibwisnup.utils.errorHandler.exceptions.InsufficientBalanceException;
import org.habibwisnup.utils.errorHandler.exceptions.InvalidAmountException;
import org.habibwisnup.utils.MessageConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {
    @Mock
    private Customer mockCustomer;

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(mockCustomer);
    }

    @Test
    public void testDeposit() {
        when(mockCustomer.getBalance()).thenReturn(100);

        customerService.deposit(50);

        verify(mockCustomer).setBalance(150);
    }

    @Test
    public void testDeposit_InvalidAmount() {
        InvalidAmountException exception = assertThrows(InvalidAmountException.class, () -> {
            customerService.deposit(-50);
        });
        assertEquals(MessageConstant.INVALID_AMOUNT_MESSAGE, exception.getMessage());
    }

    @Test
    public void testWithdraw() {
        when(mockCustomer.getBalance()).thenReturn(100);

        customerService.withdraw(50);

        verify(mockCustomer).setBalance(50);
    }

    @Test
    public void testWithdraw_InsufficientBalance() {
        when(mockCustomer.getBalance()).thenReturn(100);

        InsufficientBalanceException exception = assertThrows(InsufficientBalanceException.class, () -> {
            customerService.withdraw(150);
        });
        assertEquals(MessageConstant.INSUFFICIENT_BALANCE_MESSAGE, exception.getMessage());
    }

    @Test
    public void testWithdraw_InvalidAmount() {
        InvalidAmountException exception = assertThrows(InvalidAmountException.class, () -> {
            customerService.withdraw(-50);
        });
        assertEquals(MessageConstant.INVALID_AMOUNT_MESSAGE, exception.getMessage());
    }

    @Test
    public void testAddDebt() {
        when(mockCustomer.getDebt()).thenReturn(0);

        customerService.addDebt(50);

        verify(mockCustomer).setDebt(50);
    }

    @Test
    public void testReduceDebt() {
        when(mockCustomer.getDebt()).thenReturn(100);

        customerService.reduceDebt(50);

        verify(mockCustomer).setDebt(50);
    }

    @Test
    public void testReduceDebt_InvalidAmount() {
        InvalidAmountException exception = assertThrows(InvalidAmountException.class, () -> {
            customerService.reduceDebt(-50);
        });
        assertEquals(MessageConstant.INVALID_AMOUNT_MESSAGE, exception.getMessage());
    }

    @Test
    public void testGetCustomer() {
        Customer result = customerService.getCustomer();

        assertEquals(mockCustomer, result);
    }
}