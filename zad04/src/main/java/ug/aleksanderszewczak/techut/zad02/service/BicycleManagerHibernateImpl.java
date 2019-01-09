package ug.aleksanderszewczak.techut.zad02.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.aleksanderszewczak.techut.zad02.domain.Bicycle;

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

}
