package com.neves_eduardo.cash_machine;

import com.neves_eduardo.cash_machine.atm.CashMachine;
import com.neves_eduardo.cash_machine.atm.Note;
import com.neves_eduardo.cash_machine.atm.StoredNotes;
import com.neves_eduardo.cash_machine.exception.MachineEmptyException;
import com.neves_eduardo.cash_machine.exception.NoNotesForTransactionException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class CashMachineTest {

    private CashMachine cashMachine;
    @Before
    public void init() {
        StoredNotes.getStoredNotes().clear();
        StoredNotes.getStoredNotes().add(new Note(new BigDecimal("100"),100));
        StoredNotes.getStoredNotes().add(new Note(new BigDecimal("50"),100));
        StoredNotes.getStoredNotes().add(new Note(new BigDecimal("20"),100));
        StoredNotes.getStoredNotes().add(new Note(new BigDecimal("10"),100));
        cashMachine = new CashMachine(StoredNotes.getStoredNotes());
    }
    @Test
    public void cashMachineShouldUseTheSmallestPossibleAmountOfNotesTest() {
        Map<String, Integer> usedNotesReturn = cashMachine.withdraw(new BigDecimal(50));
        Map<String, Integer> usedNotesReturn2 = cashMachine.withdraw(new BigDecimal(80));
        Map<String, Integer> usedNotesReturn3 = cashMachine.withdraw(new BigDecimal(120));
        Map<String, Integer> usedNotesReturn4 = cashMachine.withdraw(new BigDecimal(280));
        Map<String, Integer> usedNotesReturn5 = cashMachine.withdraw(new BigDecimal(1000));

        assertEquals(usedNotesReturn.get("R$50"), Integer.valueOf(1));

        assertEquals(usedNotesReturn2.get("R$50"), Integer.valueOf(1));
        assertEquals(usedNotesReturn2.get("R$20"), Integer.valueOf(1));
        assertEquals(usedNotesReturn2.get("R$10"), Integer.valueOf(1));

        assertEquals(usedNotesReturn3.get("R$100"), Integer.valueOf(1));
        assertEquals(usedNotesReturn3.get("R$20"), Integer.valueOf(1));


        assertEquals(usedNotesReturn4.get("R$100"), Integer.valueOf(2));
        assertEquals(usedNotesReturn4.get("R$50"), Integer.valueOf(1));
        assertEquals(usedNotesReturn4.get("R$20"), Integer.valueOf(1));
        assertEquals(usedNotesReturn4.get("R$10"), Integer.valueOf(1));

        assertEquals(usedNotesReturn5.get("R$100"), Integer.valueOf(10));


    }

    @Test
    public void cashMachineShouldAlwaysGiveTheSmallestAmountOfNotesWhenSomeAreNotAvailableTest() {
        cashMachine.withdraw(new BigDecimal(10000));
        Map<String, Integer> usedNotesReturn = cashMachine.withdraw(new BigDecimal(100));
        assertEquals(usedNotesReturn.get("R$50"), Integer.valueOf(2));

    }


    @Test
    public void cashMachineShouldAlwaysGiveTheSmallestAmountOfNotesWhenSomeAreDepletedWhileWithdrawingTest() {
        Map<String, Integer> usedNotesReturn = cashMachine.withdraw(new BigDecimal(17500));
        assertEquals(usedNotesReturn.get("R$100"), Integer.valueOf(100));
        assertEquals(usedNotesReturn.get("R$50"), Integer.valueOf(100));
        assertEquals(usedNotesReturn.get("R$20"), Integer.valueOf(100));
        assertEquals(usedNotesReturn.get("R$10"), Integer.valueOf(50));

    }

    @Test(expected = NoNotesForTransactionException.class)
    public void cashMachineShouldWarnWhenThereIsNo10oughMoneyForTheWithdrawalTest() {
        cashMachine.withdraw(new BigDecimal(10000));
        cashMachine.withdraw(new BigDecimal(10000));
    }
    @Test(expected = NoNotesForTransactionException.class)
    public void cashMachineShouldWarnWhenThereIsNoMoneyForTheWithdrawalTest() {
        cashMachine.withdraw(new BigDecimal(20000));
    }

    @Test(expected = NoNotesForTransactionException.class)
    public void cashMachineShouldWarnWhenThereIsNoNotesForTheWithdrawalTest() {
        cashMachine.withdraw(new BigDecimal(1773));

    }


    @Test(expected = IllegalArgumentException.class)
    public void cashMachineShouldNotAcceptNegativeValuesTest() {
        cashMachine.withdraw(new BigDecimal(-50));

    }

    @Test(expected = MachineEmptyException.class)
    public void cashMachineShouldWarnWhenItIsEmpty() {
        cashMachine.withdraw(new BigDecimal(18000));
        cashMachine.withdraw(new BigDecimal(1));

    }

}