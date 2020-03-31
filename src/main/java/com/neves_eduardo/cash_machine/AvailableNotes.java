package com.neves_eduardo.cash_machine;

public enum AvailableNotes {
    HUNDRED(100), FIFTY(50),TWENTY(20), TEN(10);
    private int value;

    public int getValue()
    {
        return this.value;
    }

    private AvailableNotes(int value)
    {
        this.value = value;
    }
}
