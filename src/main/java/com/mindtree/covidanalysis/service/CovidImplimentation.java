package com.mindtree.covidanalysis.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import com.mindtree.covidanalysis.dao.CovidAnalysisDaoImpli;
import com.mindtree.covidanalysis.dao.CovidAnalysisDaoInterface;
import com.mindtree.covidanalysis.entity.CovidCaseCompare;
import com.mindtree.covidanalysis.entity.CovidDataRange;
import com.mindtree.covidanalysis.exceptions.InvalidDateException;
import com.mindtree.covidanalysis.exceptions.InvalidDateRangeException;
import com.mindtree.covidanalysis.exceptions.InvalidStateCodeException;
import com.mindtree.covidanalysis.exceptions.NoDataFoundException;

public class CovidImplimentation implements CovidInterface {
	
	
	CovidAnalysisDaoInterface dao = new CovidAnalysisDaoImpli();
	
	
	public List<String> getStatesNames() {
		// TODO Auto-generated method stub
		List<String> out= new ArrayList();;
		
			try {
				out = dao.getAllStates();
				for (int i = 0; i < out.size(); i++) {
					System.out.println(out.get(i));
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			return out;
		
	}

	public List<String> getDistrictForstate(String s) {
		// TODO Auto-generated method stub
		List<String> out = new ArrayList();
		try {
			out = dao.getAllDistrict(s);
			for (int i = 0; i < out.size(); i++) {
				System.out.println(out.get(i));
			}
		} catch (InvalidStateCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		return out;
	}

	public List<CovidDataRange> getdisplayonDate(String d1, String d2) {
		// TODO Auto-generated method stub\
		List<CovidDataRange> out = new ArrayList();
		SimpleDateFormat sd1=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1;
		java.util.Date date2;
		Formatter fmt2 = new Formatter();
		try {
			date1 = sd1.parse(d1);
			date2 = sd2.parse(d2);
			Date sd =new Date(date1.getTime());
			Date ed =new Date(date2.getTime());
			fmt2.format("%5s %25s %25s\n", "DATE ","STATE   "," CONFIRMED TOTAL");
			
		 	if(!dao.dateConfirm(sd)) {
			
				throw new InvalidDateException("Invalid start Date  please check the input");
			}
			if(!dao.dateConfirm(ed)) {
				throw new InvalidDateException("invalid end Date please check the input");
			}
			if(!date1.before(date2)) {
				throw new InvalidDateRangeException("Invalid Date Range , please check the input");
			}
			out = dao.getStateByDateRange(d1, d2);
			if(out.isEmpty()) {
				throw new NoDataFoundException("no Data present");
			}
			for (int i = 0; i < out.size(); i++) {
				fmt2.format("%5s %25s %25s\n",out.get(i).getDate(), out.get(i).getState(), out.get(i).getConfirmTotal() );
			}
			System.out.println(fmt2);
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDateRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return out;
		
	}

	public List<CovidCaseCompare> displayconfirmedCase(String d3, String d4, String s1, String s2) {
		// TODO Auto-generated method stub
		List<CovidCaseCompare> out = new ArrayList();
		Formatter fmt= new Formatter();
		SimpleDateFormat sd1=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sd2 = new SimpleDateFormat("yyy-MM-dd");
		java.util.Date date1;
		java.util.Date date2;
		try {
			date1 = sd1.parse(d3);
			date2 = sd2.parse(d4);
			Date sd =new Date(date1.getTime());
			Date ed =new Date(date2.getTime());
		 
			if(!dao.dateConfirm(sd)) {
				System.out.println(date1);
				System.out.println(date2);
				System.out.println(sd);
				System.out.println("end date"+ed);
				throw new InvalidDateException("Invalid start Date  please check the input");
			}
			if(!dao.dateConfirm(ed)) {
				throw new InvalidDateException("invalid end Date please check the input");
			}
			if(sd.after(ed)) {
				throw new InvalidDateRangeException("Invalid Date Range , please check the input");
			}
			out = dao.getConfirmCaseByTwoState(d3, d4, s1, s2);
			fmt.format("%5s %25s %25s %25s %25s\n", "DATE","FIRST STATE    ","FIRST STATE CONFIRMED TOTAL","SECOND STATE   ","SECOND STATE CONFIRMED TOTAL");
			for (int i = 0; i < out.size(); i++) {
				//System.out.println(out.get(i).getState()+" "+out.get(i).getDate()+" "+out.get(i).getCasesConfirm());
				fmt.format("%5s %15s %25s %30s %25s\n",out.get(i).getDate(),out.get(i).getState2(),out.get(i).getS1Case(),out.get(i).getState3(),out.get(i).getS2Case() );
			}
			System.out.println(fmt);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch ( InvalidDateRangeException e1   ) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (InvalidDateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return out;
		
	}

}
