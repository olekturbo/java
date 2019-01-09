package ug.aleksanderszewczak.techut.zad02.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.aleksanderszewczak.techut.zad02.domain.Bicycle;
import ug.aleksanderszewczak.techut.zad02.domain.Producer;

@Component
@Transactional
public class ProducerManagerHibernateImpl implements ProducerManager {

	@Autowired
    SessionFactory hsf;

    @Override
    public void addProducer(Producer producer) {
        hsf.getCurrentSession().save(producer);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Producer> getAllProducers() {
        return hsf.getCurrentSession().getNamedQuery("producer.all").list();
    }

    @Override
    public Producer findById(long id) {
        return (Producer) hsf.getCurrentSession().get(Producer.class, id);

    }

    @Override
    public void updateProducer(Producer producer) {
        hsf.getCurrentSession().update(producer);
    }

    @Override
    public void deleteProducer(Producer producer) {
        hsf.getCurrentSession().delete(producer);
    }

    @Override
    public void assignBicycle(Long bicycleId, Long producerId) {
        Bicycle bicycle = (Bicycle) hsf.getCurrentSession().get(Bicycle.class, bicycleId);
        Producer producer = (Producer) hsf.getCurrentSession().get(Producer.class, producerId);
        producer.getBicycles().add(bicycle);
    }

}
