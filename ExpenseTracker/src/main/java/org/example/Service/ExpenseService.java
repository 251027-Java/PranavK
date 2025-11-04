package org.example.Service;

import org.example.Repository.IRepository;
import org.example.Expense;
import java.util.List;
import java.util.Date;

public class ExpenseService {
    private IRepository repo;
    public ExpenseService(IRepository repository){
        repo = repository;
    }

    public Expense createExpense(int id, double val, String merchant){
        if (repo.readExpense(id) != null) return null;

        Expense expense = new Expense(id, new Date(), val, merchant);
        repo.createExpense(expense);
        return expense;
    }
    public Expense getExpense(int id){
        return repo.readExpense(id);
    }
    public Expense updateExpense(int id, double val, String merchant){
        if (repo.readExpense(id) != null) return null;

        Expense expense = new Expense(id, new Date(), val, merchant);
        repo.updateExpense(expense);
        return expense;
    }
    public boolean deleteExpense(int id){
        if (repo.readExpense(id) == null) return false;

        repo.deleteExpense(id);
        return true;
    }
    public List<Expense> loadExpenses(){
        return repo.loadExpenses();
    }
    public void saveExpenses(List<Expense> expenses){
        repo.saveExpenses(expenses);
    }
}
