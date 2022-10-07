package com.ex.service;

import java.util.ArrayList;
import java.util.List;

import com.ex.dao.ExpenseRepository;
import com.ex.dao.UserRepository;
import com.ex.model.Expense;
import com.ex.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public Expense addExpense(Expense expense, int id) {
		
		User user = this.userRepo.findById(id).get();
		if(user.getId() == 1) {
			return this.expRepo.save(expense);
		}

		return null;
	}

	
	@Override
	public List<Expense> getExpense() {
		System.out.println(expRepo.findAll());
		return this.expRepo.findAll();
	}

	
	@Override
	public Expense getExpenseById(int userId, int expId) {
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 1) {
			return this.expRepo.findById(expId).get();
		}
		return null;
	}

	
	@Override
	public Expense updateExpense(Expense expense, int expId, int userId) {
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 1) {
			Expense updateExp = this.expRepo.findById(expId).get();
			updateExp.setName(expense.getName());
			updateExp.setRemark(expense.getRemark());
			updateExp.setType(expense.getType());
			updateExp.setPrice(expense.getPrice());
			return this.expRepo.save(updateExp);
		}
		return null;
		
	}
	
	
	@Override
	public boolean approveExpense(int expId, int approve, int userId) {
		
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 2) {
			Expense approveExp = this.expRepo.findById(expId).get();
			if(approve >= 0 && approve <= 1)
				approveExp.setApproved(approve);
			this.expRepo.save(approveExp);
			return true;
		}
		return false;
	}

	
	@Override
	public boolean deleteExpense(int expId, int userId) {
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 1) {
			this.expRepo.deleteById(expId);
			return true;
		}
		return false;
	}


	
	
	@Override
	public List<Expense> getApprovedExpense(int userId) {
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 2) {
			List<Expense> allExp = this.expRepo.findAll();
			List<Expense> approvedExp = new ArrayList<>();
			for(Expense exp : allExp) {
				if(exp.getApproved() == 1) {
					approvedExp.add(exp);
				}
			}
			return approvedExp;
		}
		
		return null;
	}


	@Override
	public List<Expense> getNotApprovedExpense(int userId) {
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 2) {
			List<Expense> allExp = this.expRepo.findAll();
			List<Expense> notApprovedExp = new ArrayList<>();
			for(Expense exp : allExp) {
				if(exp.getApproved() == 0) {
					notApprovedExp.add(exp);
				}
			}
			return notApprovedExp;
		}
		
		return null;
	}


	@Override
	public double getAmount(int userId) {
		User user = this.userRepo.findById(userId).get();
		if(user.getId() == 3) {
			double sum = 0;
			List<Expense> allExp = this.expRepo.findAll();
			for(Expense exp : allExp) {
				if(exp.getApproved() == 1) {
					sum += exp.getPrice();
				}
			}
			return sum;
		}
		return -1;
	}

	

	

}
