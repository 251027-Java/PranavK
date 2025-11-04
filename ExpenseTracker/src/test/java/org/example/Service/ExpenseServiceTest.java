package org.example.Service;

import org.example.Expense;
import org.example.Repository.CSVRepository;
import org.example.Repository.JSONRepository;
import org.example.Repository.TextRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseServiceTest {
    private static ExpenseService expServ;

    @BeforeAll
    static void setupAll() {
        int serv = 0;
        switch (serv){
            case 0:
                expServ = new ExpenseService(new TextRepository());
                break;
            case 1:
                expServ = new ExpenseService(new CSVRepository());
                break;
            case 2:
                expServ = new ExpenseService(new JSONRepository());
                break;
        }
    }

    @Test
    void createExpenseRepeatID() {
        assertNull(expServ.createExpense(2, 2143, "bob"), "creation should fail bc repeat ID");
    }
    @Test
    void createExpenseNegativeValue() {
        assertNull(expServ.createExpense(5, -2143, "bob"), "creation should fail bc negative value");
    }
    @Test
    void createExpenseValue0() {
        Expense exp = new Expense(5, new Date(), 0,"bob");
        Expense serv = expServ.createExpense(5, 0, "bob");

        assertNotNull(serv, "creation val=0 shouldn't be null");
        assertEquals(exp, serv, "creation expense objects should be equal");

        expServ.deleteExpense(5);
    }
    @Test
    void createExpenseValueGreaterThan0() {
        Expense exp = new Expense(6, new Date(), 10,"bob");
        Expense serv = expServ.createExpense(6, 10, "bob");

        assertNotNull(serv, "creation val>0 shouldn't be null");
        assertEquals(exp, serv, "creation expense objects should be equal");

        expServ.deleteExpense(6);
    }

    @Test
    void getExpenseDNE() {
        assertNull(expServ.getExpense(7), "get expense shouldn't exist");
    }
    @Test
    void getExpenseExists() {
        Expense exp = expServ.createExpense(9, 23, "bobmom");

        assertEquals(expServ.getExpense(9), exp, "get expense objects should be equal");

        expServ.deleteExpense(9);
    }

    @Test
    void updateExpense() {
    }

    @Test
    void deleteExpense() {
    }

    @Test
    void loadExpenses() {
    }

    @Test
    void saveExpenses() {
    }

    @Test
    void sumExpenses() {
    }
}