package com.neves_eduardo.cash_machine.atm;

public enum AvailableNotes {

    HUNDRED(100,100), FIFTY(50,100), TWENTY(20,100), TEN(10,100);
    private int value;
    private int numberOfNotesStored;

    public int getValue() {
        return this.value;
    }

    public int getNumberOfNotesStored() {
        return this.numberOfNotesStored;
    }

    public void setNumberOfNotesStored(int numberOfNotesStored) {
        this.numberOfNotesStored = numberOfNotesStored;
    }

    private AvailableNotes(int value, int numberOfNotesStored) {
        this.value = value;
        this.numberOfNotesStored = numberOfNotesStored;
    }

}
