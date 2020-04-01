package com.neves_eduardo.cash_machine.exception;

public class MachineEmptyException extends RuntimeException {
    public MachineEmptyException(String errorMessage) {
        super(errorMessage);
    }
}
