package ug.aleksanderszewczak.techut.zad02.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.aleksanderszewczak.techut.zad02.domain.Cyclist;

@Component
@Transactional
public class CyclistManagerHibernateImpl implements CyclistManager {
	
	@Autowired
	private SessionFactory hsf;

	@Override
	public void addCyclist(Cyclist cyclist) {
		hsf.getCurrentSession().save(cyclist);
		
	}
	

}
