package org.example.Repository;

import org.example.Expense;

import java.sql.*;
import java.util.List;

public class PostgreSQLRepository implements IRepository{
    private static final String Postgre_URL = "jdbc:postgresql://localhost:5432/expensesdb";
    private static final String Postgre_User = "postgres";
    private static final String Postgre_PW = "mysecretpassword";
    private Connection connection;

    public PostgreSQLRepository(){
        try {
            connection = DriverManager.getConnection(Postgre_URL, Postgre_User, Postgre_PW);

            try (Statement stmt = connection.createStatement()) {
                String sql = "CREATE SCHEMA IF NOT EXISTS ExpenseReport;" +
                        "CREATE TABLE IF NOT EXISTS ExpenseReport.Expenses (" +
                        "id INT PRIMARY KEY," +
                        "date TIMESTAMP NOT NULL," +
                        "price FLOAT CHECK (price > 0)," +
                        "merchant VARCHAR(50) NOT NULL" +
                        ");";

                stmt.execute(sql);
                IO.println("Successful creation of Postgre database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createExpense(Expense expense) {
        String sql = "INSERT INTO ExpenseReport.Expenses (id, date, price, merchant) Values (?,?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, expense.getId());
            stmt.setDate(2,  new java.sql.Date((expense.getDate().getTime())));
            stmt.setDouble(3, expense.getValue());
            stmt.setString(4, expense.getMerchant());
            stmt.executeUpdate();
            System.out.println("Expense created successfully!");
        } catch (SQLException e){
            IO.println(e);
        }
    }

    @Override
    public Expense readExpense(int id) {
        String sql = "select * from ExpenseReport.Expenses where id=?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                Expense exp = new Expense(rs.getInt("id"),
                        rs.getDate("date"),
                        rs.getFloat("price"),
                        rs.getString("merchant"));
                return exp;
            }
        } catch (SQLException e){
            IO.println(e);
        }

        return null;
    }

    @Override
    public void updateExpense(Expense expense) {

    }

    @Override
    public void deleteExpense(int id) {

    }

    @Override
    public List<Expense> loadExpenses() {
        return List.of();
    }

    @Override
    public void saveExpenses(List<Expense> expenses) {

    }
}
