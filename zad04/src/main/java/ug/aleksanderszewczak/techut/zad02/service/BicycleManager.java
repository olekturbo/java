package ug.aleksanderszewczak.techut.zad02.service;

import java.util.List;

import ug.aleksanderszewczak.techut.zad02.domain.Bicycle;

public interface BicycleManager {

	void addBicycle(Bicycle bicycle);
	List<Bicycle> getAllBicycles();
	Bicycle findById(long id);
	void updateBicycle(Bicycle bicycle);
	void deleteBicycle(Bicycle bicycle);
}
