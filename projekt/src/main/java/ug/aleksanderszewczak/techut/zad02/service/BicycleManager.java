package ug.aleksanderszewczak.techut.zad02.service;

import java.util.List;

import ug.aleksanderszewczak.techut.zad02.domain.Bicycle;
import ug.aleksanderszewczak.techut.zad02.domain.Cyclist;

public interface BicycleManager {

	// Bicycle
	void addBicycle(Bicycle bicycle);
	List<Bicycle> getAllBicycles();
	Bicycle findById(long id);
	void updateBicycle(Bicycle bicycle);
	void deleteBicycle(Bicycle bicycle);
	
	// Relations
	List<Cyclist> getBicycleCyclists(Bicycle bicycle);
	void relateBicycleAndCyclist(long bicycleId, long cyclistId);
	void giveLicense(long bicycleId, long licenseId);
}
