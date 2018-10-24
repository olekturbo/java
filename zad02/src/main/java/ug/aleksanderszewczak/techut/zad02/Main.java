package ug.aleksanderszewczak.techut.zad02;

import java.sql.*;
import java.text.ParseException;

import ug.aleksanderszewczak.techut.zad02.domain.Bicycle;
import ug.aleksanderszewczak.techut.zad02.service.BicycleService;

public class Main {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException, ParseException {
	
	    	
	
			Bicycle b1 = new Bicycle("Kross", Double.parseDouble("999.99"), new Date(118, 01, 11), false);
	    	Bicycle b2 = new Bicycle("GRAND", Double.parseDouble("1499.90"), new Date(115, 12, 15), true);
	    	
	        BicycleService bs = new BicycleService();
	
	        bs.addBicycle(b1);
	        bs.addBicycle(b2);
	        
	        Bicycle bicycle = bs.getBicycleById(4);
	        //System.out.println(bicycle);
	        //bs.deleteBicycleById(5);
	        //bs.deleteAllBicycles();
	        //System.out.println(bs.getAllBicycles());
	}
	

}
