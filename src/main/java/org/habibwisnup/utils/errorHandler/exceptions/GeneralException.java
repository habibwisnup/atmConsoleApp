package org.habibwisnup.utils.errorHandler.exceptions;

public class GeneralException extends ApplicationException{
    public GeneralException(String message){
        super(message, "ERR_000_GEN");
    }
}
