package org.habibwisnup.utils.errorHandler.exceptions;

public class InsufficientBalanceException extends ApplicationException{
    public InsufficientBalanceException(String message){
        super(message, "ERR_001_IB");
    }
}
