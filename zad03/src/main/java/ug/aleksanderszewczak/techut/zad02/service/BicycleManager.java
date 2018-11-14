package ug.aleksanderszewczak.techut.zad02.service;

import java.util.List;

import ug.aleksanderszewczak.techut.zad02.domain.Bicycle;

public interface BicycleManager {
	public void addBicycle(Bicycle bicycle);
	public List<Bicycle> getAllBicycles();
	public Bicycle getBicycleById(int id);
	public List<Bicycle> getBicyclesCheaperThan(double price);
	public void deleteAllBicycles();
	public void deleteBicycleById(int id);
	public boolean addBicycles(List<Bicycle> bicycles);
}
