package ug.aleksanderszewczak.techut.zad02.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ug.aleksanderszewczak.techut.zad02.domain.Bicycle;
import ug.aleksanderszewczak.techut.zad02.domain.Cyclist;
import ug.aleksanderszewczak.techut.zad02.domain.License;
import ug.aleksanderszewczak.techut.zad02.domain.Producer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class BicycleTest {

    @Autowired
    BicycleManager bm;
    
    @Autowired
    CyclistManager cm;
    
    @Autowired
    LicenseManager lm;
    
    @Autowired
    ProducerManager pm;
    
    private static final double DELTA = 1e-15;
    
    private static String sDate = "01-01-2015";
	private static Date dDate;
	static {
		try {
			dDate = new SimpleDateFormat("dd-MM-yyyy").parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    

    @Test
    public void addBicycleCheck() {
        Bicycle b = new Bicycle(999, dDate, true);

        bm.addBicycle(b);

        Bicycle retrieved = bm.findById(b.getId());

        assertEquals(b.getId(), retrieved.getId());
    }

    @Test
    public void getAllBicyclesCheck() {
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
    	Bicycle b = new Bicycle(999, dDate, true);
        bm.addBicycle(b);

        long id = b.getId();

        Bicycle foundBicycle = bm.findById(id);

        assertEquals(b.getId(), foundBicycle.getId());
    }

    @Test
    public void deleteBicycleCheck() {
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
    	Bicycle b = new Bicycle(999, dDate, true);
        bm.addBicycle(b);

        double newPrice = 100;

        b.setPrice(newPrice);

        bm.updateBicycle(b);

        assertEquals(b.getPrice(), newPrice, DELTA);
    }
    
    @Test
    public void bicycleAndCyclistCheck() {
        Cyclist c1 = new Cyclist("Tadeusz", "Norek", 1997);
        Cyclist c2 = new Cyclist("Karol", "Krawczyk", 2000);

        cm.addCyclist(c1);
        cm.addCyclist(c2);
        
    	Bicycle b1 = new Bicycle(999, dDate, true);
    	Bicycle b2 = new Bicycle(555, dDate, false);


        bm.addBicycle(b1);
        bm.addBicycle(b2);

        List<Cyclist> cyclistsOneBefore = bm.getBicycleCyclists(b1);
        List<Cyclist> cyclistsTwoBefore = bm.getBicycleCyclists(b2);
        int beforeOne = cyclistsOneBefore.size();
        int beforeTwo = cyclistsTwoBefore.size();

        bm.relateBicycleAndCyclist(b1.getId(), c1.getId());
        bm.relateBicycleAndCyclist(b1.getId(), c2.getId());
        bm.relateBicycleAndCyclist(b2.getId(), c1.getId());
        bm.relateBicycleAndCyclist(b2.getId(), c2.getId());
        

        List<Cyclist> cyclistsOneAfter = bm.getBicycleCyclists(b1);
        List<Cyclist> cyclistsTwoAfter = bm.getBicycleCyclists(b2);
        int afterOne = cyclistsOneAfter.size();
        int afterTwo = cyclistsTwoAfter.size();

        assertEquals(beforeOne + 2, afterOne);
        assertEquals(beforeTwo + 2, afterTwo);
        assertEquals(b1.getCyclists().get(afterOne-2).getFirstName(), c1.getFirstName());
        assertEquals(b1.getCyclists().get(afterOne-1).getFirstName(), c2.getFirstName());
        assertEquals(b2.getCyclists().get(afterTwo-2).getFirstName(), c1.getFirstName());
        assertEquals(b2.getCyclists().get(afterTwo-1).getFirstName(), c2.getFirstName());
    }
    
    @Test
    public void giveLicenseTest() {
 
    	Bicycle bicycle = new Bicycle(999, dDate, true);
        bm.addBicycle(bicycle);

        License license = new License((int) Math.random());
        lm.addLicense(license);

        bm.giveLicense(bicycle.getId(), license.getId());

        assertEquals(bicycle.getLicense().getId(), license.getId());
    }
    
    @Test
    public void bicycleAndProducersTest() {
 
    	Bicycle bicycle = new Bicycle(999, dDate, true);
        bm.addBicycle(bicycle);

        Producer producer = new Producer("KROSS");
        pm.addProducer(producer);

        pm.assignBicycle(bicycle.getId(), producer.getId());

        assertTrue(producer.getBicycles().contains(bicycle));
    }
}