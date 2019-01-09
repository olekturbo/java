package ug.aleksanderszewczak.techut.zad02.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.aleksanderszewczak.techut.zad02.domain.Bicycle;
import ug.aleksanderszewczak.techut.zad02.domain.Cyclist;
import ug.aleksanderszewczak.techut.zad02.domain.License;

@Component
@Transactional
public class BicycleManagerHibernateImpl implements BicycleManager {

	@Autowired
	SessionFactory hsf;
	
	@Override
	public void addBicycle(Bicycle bicycle) {
		hsf.getCurrentSession().save(bicycle);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bicycle> getAllBicycles() {
		return hsf.getCurrentSession().getNamedQuery("bicycles.all").list();
	}

	@Override
	public Bicycle findById(long id) {
		return (Bicycle) hsf.getCurrentSession().get(Bicycle.class, id);
	}

	@Override
	public void updateBicycle(Bicycle bicycle) {
		hsf.getCurrentSession().update(bicycle);
	}

	@Override
	public void deleteBicycle(Bicycle bicycle) {
		 hsf.getCurrentSession().delete(bicycle);
	}

	@Override
	public List<Cyclist> getBicycleCyclists(Bicycle bicycle) {
        bicycle = (Bicycle) hsf.getCurrentSession().get(Bicycle.class, bicycle.getId());

        List<Cyclist> cyclists = new ArrayList<Cyclist>(bicycle.getCyclists());

        return cyclists;
	}

	@Override
	public void relateBicycleAndCyclist(long bicycleId, long cyclistId) {
        Bicycle bicycle = findById(bicycleId);
        Cyclist cyclist = (Cyclist) hsf.getCurrentSession().get(Cyclist.class, cyclistId);

        bicycle.getCyclists().add(cyclist);
		
	}

	@Override
	public void giveLicense(long bicycleId, long licenseId) {
		Bicycle bicycle = (Bicycle) hsf.getCurrentSession().get(Bicycle.class, bicycleId);
		License license = (License) hsf.getCurrentSession().get(License.class, licenseId);

        bicycle.setLicense(license);
		
	}
}
