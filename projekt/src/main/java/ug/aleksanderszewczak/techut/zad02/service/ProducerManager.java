package ug.aleksanderszewczak.techut.zad02.service;

import java.util.List;

import ug.aleksanderszewczak.techut.zad02.domain.Address;
import ug.aleksanderszewczak.techut.zad02.domain.Producer;

public interface ProducerManager {
	
	// Producer
	void addProducer(Producer producer);
    List<Producer> getAllProducers();
    Producer findById(long id);
    void updateProducer(Producer producer);
    void deleteProducer(Producer producer);
    
    // Relations
    void assignBicycle(Long bicycleId, Long producerId);
    void assignAddress(Long addressId, Long producerId);
    void deleteAddress(Long addressId, Long producerId);
    Producer findByName(String name);
    List<Address> getProducerAddresses(Producer producer);
}
