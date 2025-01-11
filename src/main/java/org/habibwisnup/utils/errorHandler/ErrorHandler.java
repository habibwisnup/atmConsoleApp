package org.habibwisnup.utils.errorHandler;

import org.habibwisnup.utils.errorHandler.exceptions.ApplicationException;

public class ErrorHandler {
    public static void handleException(Exception exception) {
        if (exception instanceof ApplicationException) {
            ApplicationException appException = (ApplicationException) exception;
            System.err.println("ErrCode: " + appException.getErrorCode());
            System.err.println("ErrMessage: " + appException.getMessage());
        } else {
            System.err.println("Unexpected Error: " + exception.getMessage());
        }
    }
}
