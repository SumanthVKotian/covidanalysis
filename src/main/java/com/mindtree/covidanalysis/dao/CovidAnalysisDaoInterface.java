package com.mindtree.covidanalysis.dao;


import java.sql.Date;
import java.sql.SQLException;

import java.util.List;

import com.mindtree.covidanalysis.entity.CovidCaseCompare;
import com.mindtree.covidanalysis.entity.CovidDataRange;
import com.mindtree.covidanalysis.exceptions.InvalidDateException;
import com.mindtree.covidanalysis.exceptions.InvalidDateRangeException;
import com.mindtree.covidanalysis.exceptions.InvalidStateCodeException;
import com.mindtree.covidanalysis.exceptions.NoDataFoundException;

public interface CovidAnalysisDaoInterface {
	
	
	public boolean dateConfirm(Date d) throws ClassNotFoundException, SQLException;
	List<String> getAllStates() throws ClassNotFoundException, SQLException;
	List<String> getAllDistrict(String s1) throws InvalidStateCodeException, ClassNotFoundException, SQLException;
	List<CovidDataRange> getStateByDateRange(String d1,String d2) throws InvalidDateException,InvalidDateRangeException,NoDataFoundException, ClassNotFoundException, SQLException;
	List<CovidCaseCompare> getConfirmCaseByTwoState(String d1,String d2,String s1,String s2) throws ClassNotFoundException, SQLException;

}
