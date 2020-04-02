package com.neves_eduardo.cash_machine;

import com.neves_eduardo.cash_machine.atm.AvailableNotes;
import com.neves_eduardo.cash_machine.atm.CashMachine;
import com.neves_eduardo.cash_machine.exception.MachineEmptyException;
import com.neves_eduardo.cash_machine.exception.NoNotesForTransactionException;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class CashMachineTest {
    private CashMachine cashMachine;
    @Before
    public void init() {
        AvailableNotes.HUNDRED.setNumberOfNotesStored(100);
        AvailableNotes.FIFTY.setNumberOfNotesStored(100);
        AvailableNotes.TWENTY.setNumberOfNotesStored(100);
        AvailableNotes.TEN.setNumberOfNotesStored(100);
        cashMachine = new CashMachine();
    }
    @Test
    public void cashMachineShouldUseTheSmallestPossibleAmountOfNotesTest() {
        Map<String, Integer> usedNotesReturn = cashMachine.withdraw(50);
        Map<String, Integer> usedNotesReturn2 = cashMachine.withdraw(80);
        Map<String, Integer> usedNotesReturn3 = cashMachine.withdraw(120);
        Map<String, Integer> usedNotesReturn4 = cashMachine.withdraw(280);
        Map<String, Integer> usedNotesReturn5 = cashMachine.withdraw(1000);

        assertEquals(usedNotesReturn.get("FIFTY"), Integer.valueOf(1));

        assertEquals(usedNotesReturn2.get("FIFTY"), Integer.valueOf(1));
        assertEquals(usedNotesReturn2.get("TWENTY"), Integer.valueOf(1));
        assertEquals(usedNotesReturn2.get("TEN"), Integer.valueOf(1));

        assertEquals(usedNotesReturn3.get("HUNDRED"), Integer.valueOf(1));
        assertEquals(usedNotesReturn3.get("TWENTY"), Integer.valueOf(1));


        assertEquals(usedNotesReturn4.get("HUNDRED"), Integer.valueOf(2));
        assertEquals(usedNotesReturn4.get("FIFTY"), Integer.valueOf(1));
        assertEquals(usedNotesReturn4.get("TWENTY"), Integer.valueOf(1));
        assertEquals(usedNotesReturn4.get("TEN"), Integer.valueOf(1));

        assertEquals(usedNotesReturn5.get("HUNDRED"), Integer.valueOf(10));


    }

    @Test
    public void cashMachineShouldAlwaysGiveTheSmallestAmountOfNotesWhenSomeAreNotAvailableTest() {
        cashMachine.withdraw(10000);
        Map<String, Integer> usedNotesReturn = cashMachine.withdraw(100);
        assertEquals(usedNotesReturn.get("FIFTY"), Integer.valueOf(2));

    }


    @Test
    public void cashMachineShouldAlwaysGiveTheSmallestAmountOfNotesWhenSomeAreDepletedWhileWithdrawingTest() {
        Map<String, Integer> usedNotesReturn = cashMachine.withdraw(17500);
        assertEquals(usedNotesReturn.get("HUNDRED"), Integer.valueOf(100));
        assertEquals(usedNotesReturn.get("FIFTY"), Integer.valueOf(100));
        assertEquals(usedNotesReturn.get("TWENTY"), Integer.valueOf(100));
        assertEquals(usedNotesReturn.get("TEN"), Integer.valueOf(50));

    }

    @Test(expected = NoNotesForTransactionException.class)
    public void cashMachineShouldWarnWhenThereIsNotEnoughMoneyForTheWithdrawalTest() {
        cashMachine.withdraw(10000);
        cashMachine.withdraw(10000);
    }
    @Test(expected = NoNotesForTransactionException.class)
    public void cashMachineShouldWarnWhenThereIsNoMoneyForTheWithdrawalTest() {
        cashMachine.withdraw(20000);
    }

    @Test(expected = NoNotesForTransactionException.class)
    public void cashMachineShouldWarnWhenThereIsNoNotesForTheWithdrawalTest() {
        cashMachine.withdraw(1773);

    }


    @Test(expected = IllegalArgumentException.class)
    public void cashMachineShouldNotAcceptNegativeValuesTest() {
        cashMachine.withdraw(-50);

    }

    @Test(expected = MachineEmptyException.class)
    public void cashMachineShouldWarnWhenItIsEmpty() {
        cashMachine.withdraw(18000);
        cashMachine.withdraw(1);

    }

}