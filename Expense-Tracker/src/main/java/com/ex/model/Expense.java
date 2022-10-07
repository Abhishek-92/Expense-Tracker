package com.ex.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String type;
	
	private String remark;
	
	private double price;
	
	@ColumnDefault("0")
	private int approved;

	public Expense() {
		super();
		//TODO Auto-generated constructor stub
	}

	public Expense(String name, String type, String remark, double price) {
		super();
		this.name = name;
		this.type = type;
		this.remark = remark;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "Expenses [id=" + id + ", name=" + name + ", type=" + type + ", remark=" + remark + ", price=" + price
				+ ", approved=" + approved + "]";
	}
	
	
	
}
