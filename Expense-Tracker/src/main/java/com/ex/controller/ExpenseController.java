package com.ex.controller;

import java.util.List;

import com.ex.model.Expense;
import com.ex.service.ExpenseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

	@Autowired
	private ExpenseServiceImpl service;
	
	
	@PostMapping("{userId}")
	public ResponseEntity<Object> insertExpenses(@RequestBody Expense expense, @PathVariable int userId){
		Expense res = service.addExpense(expense, userId);
		System.out.println("here");
		if(res == null) {
			return new ResponseEntity<Object>("Valid Create Role Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(res, HttpStatus.CREATED);
	}
	
	
	@GetMapping("")
	public ResponseEntity<List<Expense>> getAllExpense(){
		List<Expense> expRec = service.getExpense();
		return new ResponseEntity<List<Expense>>(expRec, HttpStatus.OK);
	}
	
	
	@GetMapping("{userId}/{expId}")
	public ResponseEntity<Object> getSingleExpense(@PathVariable int userId, @PathVariable int expId){
		Expense res = this.service.getExpenseById(userId, expId);
		if(res == null) {
			return new ResponseEntity<Object>("Invalid role", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
	
	
	@PutMapping("{userId}/{expId}")
	public ResponseEntity<Object> updateExpenseById(@RequestBody Expense expense, @PathVariable int expId, @PathVariable int userId) {
		Expense updatedExp = service.updateExpense(expense, expId, userId);
		if(updatedExp == null) {
			return new ResponseEntity<Object>("Valid Update Role Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(updatedExp, HttpStatus.OK);
	}
	
	
	@PostMapping("{userId}/approveExpense/{expId}")
	public ResponseEntity<String> approveExp(@PathVariable int userId, @PathVariable int expId, @RequestBody Expense exp){
		
		boolean res = this.service.approveExpense(expId, exp.getApproved(), userId);
		if(!res) {
			return new ResponseEntity<String>("Valid Approve Role Not Found", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>("Expenses Approved Sucessfully", HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@DeleteMapping("{userId}/{expId}")
	public ResponseEntity<String> removeExpense(@PathVariable int userId, @PathVariable int expId){
		boolean res = this.service.deleteExpense(expId, userId);
		if(!res) {
			return new ResponseEntity<String>("Valid Delete Role Not Found", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>("Expenses Deleted Sucessfully", HttpStatus.OK);
	}
	
	
	@GetMapping("{userId}/approvedExpenses")
	public ResponseEntity<Object> allApprovedExpense(@PathVariable int userId){
		List<Expense> approvedExp = this.service.getApprovedExpense(userId);
		if(approvedExp == null) {
			return new ResponseEntity<Object>("Valid Role Not Found", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(approvedExp, HttpStatus.OK);
	}


	@GetMapping("{userId}/notApprovedExpenses")
	public ResponseEntity<Object> notApprovedExpense(@PathVariable int userId){
		List<Expense> notApprovedExp = this.service.getNotApprovedExpense(userId);
		if(notApprovedExp == null) {
			return new ResponseEntity<Object>("Valid Role Not Found", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(notApprovedExp, HttpStatus.OK);
	}
	

	@GetMapping("{userId}/getTotal")
	public ResponseEntity<Object> getTotal(@PathVariable int userId){
		double res = this.service.getAmount(userId);
		if(res == -1) {
			return new ResponseEntity<Object>("Valid Role Not Found", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}
}
