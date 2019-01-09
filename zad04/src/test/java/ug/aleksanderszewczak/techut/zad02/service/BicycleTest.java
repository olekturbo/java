package ug.aleksanderszewczak.techut.zad02.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ug.aleksanderszewczak.techut.zad02.domain.Bicycle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class BicycleTest {

    @Autowired
    BicycleManager bm;
    
    private static final double DELTA = 1e-15;
    

    @Test
    public void addBicycleCheck() {
    	 String sDate = "01-01-2015";
    		Date dDate = null;
			try {
				dDate = new SimpleDateFormat("dd-MM-yyyy").parse(sDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        Bicycle b = new Bicycle(999, dDate, true);

        bm.addBicycle(b);

        Bicycle retrieved = bm.findById(b.getId());

        assertEquals(b.getId(), retrieved.getId());
    }

    @Test
    public void getAllBicyclesCheck() {
    	String sDate = "01-01-2015";
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("dd-MM-yyyy").parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Bicycle> before = bm.getAllBicycles();
    	
        Bicycle b1 = new Bicycle(1000, dDate, true);
        Bicycle b2 = new Bicycle(2000, dDate, false);

        bm.addBicycle(b1);
        bm.addBicycle(b2);

        List<Bicycle> after = bm.getAllBicycles();

        assertEquals(before.size() + 2, after.size());
    }

    @Test
    public void findBicycleByIdCheck() {
    	String sDate = "01-01-2015";
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("dd-MM-yyyy").parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Bicycle b = new Bicycle(999, dDate, true);
        bm.addBicycle(b);

        long id = b.getId();

        Bicycle foundBicycle = bm.findById(id);

        assertEquals(b.getId(), foundBicycle.getId());
    }

    @Test
    public void deleteBicycleCheck() {
    	String sDate = "01-01-2015";
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("dd-MM-yyyy").parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Bicycle> before = bm.getAllBicycles();

    	Bicycle b = new Bicycle(999, dDate, true);
    	
        bm.addBicycle(b);
        bm.deleteBicycle(b);

        List<Bicycle> after = bm.getAllBicycles();

        assertEquals(before.size(), after.size());
    }

    @SuppressWarnings("deprecation")
	@Test
    public void updateBicycleCheck() {
    	String sDate = "01-01-2015";
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("dd-MM-yyyy").parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Bicycle b = new Bicycle(999, dDate, true);
        bm.addBicycle(b);

        double newPrice = 100;

        b.setPrice(newPrice);

        bm.updateBicycle(b);

        assertEquals(b.getPrice(), newPrice, DELTA);
    }
}