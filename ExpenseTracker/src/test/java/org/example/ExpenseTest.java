package org.example;

import java.util.Date;

public class ExpenseTest {
    public static void main(String[] args){
        testExpenseCreation();
    }
    public static void testExpenseCreation(){
        //arrange/set conditions for the test
        Expense expense = new Expense(1, new Date(), 100, "Dummy");

        //act - what functionality are you trying to validate
        int possibleID = expense.getId();
        double possibleVal = expense.getValue();

        //assert - compare the expected to the actual
        if (possibleID != 1) IO.println("[FAIL] incorrect ID");
        else IO.println("[PASS] correct ID");

        if (possibleVal != 100) IO.println("[FAIL] incorrect value");
        else IO.println("[PASS] correct value");
    }
}
