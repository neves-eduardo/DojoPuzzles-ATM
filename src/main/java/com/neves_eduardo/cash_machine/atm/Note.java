package com.neves_eduardo.cash_machine.atm;

import java.math.BigDecimal;

public class Note {

    private BigDecimal value;
    private Integer numberOfNotesStored;

    public Note(BigDecimal value, Integer numberOfNotesStored) {
        this.value = value;
        this.numberOfNotesStored = numberOfNotesStored;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getNumberOfNotesStored() {
        return numberOfNotesStored;
    }

    public void setNumberOfNotesStored(Integer numberOfNotesStored) {
        this.numberOfNotesStored = numberOfNotesStored;
    }

}
