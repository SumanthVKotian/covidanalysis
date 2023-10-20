package com.mindtree.covidanalysis.entity;
//hello
import java.sql.Date;

public class CovidCaseCompare {
	
	private Date date;
	private String state2;
	private int s1Case;
	private String state3;
	private int s2Case;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getState2() {
		return state2;
	}
	public void setState2(String state2) {
		this.state2 = state2;
	}
	public int getS1Case() {
		return s1Case;
	}
	public void setS1Case(int s1Case) {
		this.s1Case = s1Case;
	}
	public String getState3() {
		return state3;
	}
	public void setState3(String state3) {
		this.state3 = state3;
	}
	public int getS2Case() {
		return s2Case;
	}
	public void setS2Case(int s2Case) {
		this.s2Case = s2Case;
	}
	public CovidCaseCompare(Date date, String state2, int s1Case, String state3, int s2Case) {
		super();
		this.date = date;
		this.state2 = state2;
		this.s1Case = s1Case;
		this.state3 = state3;
		this.s2Case = s2Case;
	}
	
	

}
