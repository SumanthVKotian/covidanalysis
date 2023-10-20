package com.mindtree.covidanalysis.entity;

import java.sql.Date;

public class CovidDataRange {
	
	private Date date;
	public CovidDataRange(Date date, String state, int confirmTotal) {
		super();
		this.date = date;
		this.state = state;
		this.confirmTotal = confirmTotal;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	private String state;
	private int confirmTotal;

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getConfirmTotal() {
		return confirmTotal;
	}
	public void setConfirmTotal(int confirmTotal) {
		this.confirmTotal = confirmTotal;
	}
	
}
