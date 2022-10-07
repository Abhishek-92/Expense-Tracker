package com.ex.service;

import java.util.List;

import com.ex.model.Expense;


public interface ExpenseService {
	
	public Expense addExpense(Expense expense, int id);
	
	public List<Expense> getExpense();
	
	public Expense getExpenseById(int userId, int expId);
	
	public Expense updateExpense(Expense expense, int expId, int userId);
	
	public boolean approveExpense(int expId, int approve, int userId);
	
	public boolean deleteExpense(int expId, int userId);
	
	public List<Expense> getApprovedExpense(int userId);
	
	public List<Expense> getNotApprovedExpense(int userId);
	
	public double getAmount(int userId);
}
