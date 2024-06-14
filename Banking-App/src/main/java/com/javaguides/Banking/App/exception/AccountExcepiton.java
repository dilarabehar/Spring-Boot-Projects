package com.javaguides.Banking.App.exception;

public class AccountExcepiton extends RuntimeException{

    /*

     Exceptions are also classes in Java.
     They are used to signal errors that occur during program execution.
     When you create a custom exception class,
     it's typically a subclass of the built-in Exception class or a more specific exception class like RuntimeException.
     */
    public AccountExcepiton (String message)
    {
        super(message);
    }
}
