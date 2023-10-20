package com.mindtree.covidanalysis.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.mindtree.covidanalysis.entity.CovidCaseCompare;
import com.mindtree.covidanalysis.entity.CovidDataRange;

class CovidImplimentationTest {
	
	private CovidImplimentation covidServe = new CovidImplimentation();

	@Test
	void testGetStatesNames() {
		List<String> expOut = covidServe.getStatesNames();
		List<String> actout = new ArrayList<String>(Arrays.asList("AN","AP","AR","AS"));
		assertTrue(expOut.contains("AN"));
		
	}

	@Test
	void testGetDistrictForstate() {
		List<String> expOut=covidServe.getDistrictForstate("KA");
		assertTrue(expOut.contains("Bidar"));
	}

	@Test
	void testGetdisplayonDate() throws ParseException {
		List<CovidDataRange> expOut = covidServe.getdisplayonDate("2020-07-25", "2020-07-29");
		SimpleDateFormat sd1=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1;
			date1 = sd1.parse("2020-07-26");
			Date sd =new Date(date1.getTime());
		assertTrue(expOut.get(0).getConfirmTotal()==12);
	}

	@Test
	void testDisplayconfirmedCase() {
		
		List<CovidCaseCompare> expOut = covidServe.displayconfirmedCase("2020-06-25","2020-07-29", "TR","JH" );
		assertTrue(expOut.get(0).getState2().equalsIgnoreCase("TR") && expOut.get(0).getState3().equalsIgnoreCase("JH")
	);
	}

}
