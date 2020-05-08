package com.neves_eduardo.cash_machine.exception;

public class NoNotesForTransactionException extends RuntimeException {

    public NoNotesForTransactionException(String errorMessage) {
        super(errorMessage);
    }

}
