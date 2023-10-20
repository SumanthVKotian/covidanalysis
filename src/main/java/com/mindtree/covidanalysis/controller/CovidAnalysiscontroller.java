package com.mindtree.covidanalysis.controller;

import java.util.Scanner;

import com.mindtree.covidanalysis.service.CovidImplimentation;
import com.mindtree.covidanalysis.service.CovidInterface;
//hello
public class CovidAnalysiscontroller {
	
	public static void main(String[] args) {
		
		boolean flag =true;
		
		CovidInterface service=new CovidImplimentation();
		
		while(flag) {
			
			System.out.println("*******************************************************************");
			System.out.println("1. Get State names");
			System.out.println("2.GetDistrict for given state");
			System.out.println("3.Display data By State");
			System.out.println("4.Display Confirmed cases by comparing two states for a given date range.");
			System.out.println("5.Exit");
			Scanner sc=new Scanner(System.in);
			System.out.println("Please enter the option:");
			int n=sc.nextInt();
			switch(n) {
			  case 1:
			    service.getStatesNames();
			    break;
			  case 2:
				
				  System.out.println("Please enter state code:");
				  String s=sc.next();
				  service.getDistrictForstate(s);
			    break;
			  case 3:
				  System.out.println("1");
				  System.out.println("Plase enter the start date;");
				  String d1=sc.next();
				  System.out.println();
				  System.out.println("Please enter the end date;");
				  String d2=sc.next();
				  service.getdisplayonDate(d1,d2);
				break;
			  case 4:
				  System.out.println("1");
				  System.out.println("Plase enter the start date;");
				  String d3=sc.next();
				  System.out.println();
				  System.out.println("Please enter the end date;");
				  String d4=sc.next();
				  System.out.println("Plase enter the first state;");
				  String s1=sc.next();
				  System.out.println();
				  System.out.println("Please enter the second state;");
				  String s2=sc.next();
				  service.displayconfirmedCase(d3,d4,s1,s2);
				break;
			  case 5:
				  System.out.println("1");
				  flag=false;
				break;
			  default:
			    System.out.println("invalid option");
			}
			
		}
	}

}
