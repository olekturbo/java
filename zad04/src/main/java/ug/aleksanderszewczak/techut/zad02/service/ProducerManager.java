package ug.aleksanderszewczak.techut.zad02.service;

import java.util.List;

import ug.aleksanderszewczak.techut.zad02.domain.Producer;

public interface ProducerManager {
	void addProducer(Producer producer);

    List<Producer> getAllProducers();

    Producer findById(long id);

    void updateProducer(Producer producer);

    void deleteProducer(Producer producer);

    void assignBicycle(Long bicycleId, Long producerId);
}
