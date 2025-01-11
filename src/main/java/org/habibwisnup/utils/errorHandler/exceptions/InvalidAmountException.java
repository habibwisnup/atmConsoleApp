package org.habibwisnup.utils.errorHandler.exceptions;

public class InvalidAmountException extends ApplicationException{
    public InvalidAmountException(String message){
        super(message, "ERR_002_IA");
    }
}
