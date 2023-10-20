package com.mindtree.covidanalysis.service;

import java.util.List;

import com.mindtree.covidanalysis.entity.CovidCaseCompare;
import com.mindtree.covidanalysis.entity.CovidDataRange;

public interface CovidInterface {

	public List<String> getStatesNames();

	public List<String> getDistrictForstate(String s);

	public List<CovidDataRange> getdisplayonDate(String d1, String d2);

	public List<CovidCaseCompare> displayconfirmedCase(String d3, String d4, String s1, String s2);
	
	

}
