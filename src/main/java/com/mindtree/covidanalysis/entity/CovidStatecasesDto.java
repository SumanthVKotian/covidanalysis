package com.mindtree.covidanalysis.entity;

import java.sql.Date;

public class CovidStatecasesDto {
	private String state;
	private Date date;
	private int casesConfirm;
	public CovidStatecasesDto(String state, Date date, int casesConfirm) {
		super();
		this.state = state;
		this.date = date;
		this.casesConfirm = casesConfirm;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCasesConfirm() {
		return casesConfirm;
	}
	public void setCasesConfirm(int casesConfirm) {
		this.casesConfirm = casesConfirm;
	}
	

}
