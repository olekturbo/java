package ug.aleksanderszewczak.techut.zad02.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "bicycles.all", query = "Select b from Bicycle b"),
})
public class Bicycle {
	
	private long id;
	private double price;
	private Date productionDate;
	private boolean isReserved;
	private License license;
	private Producer producer;
	private List<Cyclist> cyclists = new ArrayList<Cyclist>();
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	public boolean isReserved() {
		return isReserved;
	}
	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
	
	@Override
	public String toString() {
		return "Bicycle [producer=" + producer + ", price=" + price + ", productionDate=" + productionDate
				+ ", isReserved=" + isReserved + "]";
	}
	
	public Bicycle(double price, Date productionDate, boolean isReserved, Producer producer, License license, List<Cyclist> cyclists) {
		super();
		this.price = price;
		this.productionDate = productionDate;
		this.isReserved = isReserved;
		this.producer = producer;
		this.license = license;
		this.cyclists = cyclists;
	}
	
	public Bicycle(double price, Date productionDate, boolean isReserved) {
		super();
		this.price = price;
		this.productionDate = productionDate;
		this.isReserved = isReserved;
	}
	public Bicycle() {
		super();
	}
	
	// Bicycle has unique license, License has unique bicycle
	@OneToOne
	public License getLicense() {
		return license;
	}
	public void setLicense(License license) {
		this.license = license;
	}
	
	// Producer has many bicycles
    @ManyToOne
    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
    
    // Bicycle has many cyclists, Cyclist has many bicycles
    @ManyToMany
    public List<Cyclist> getCyclists() {
        return cyclists;
    }

    public void setCyclists(List<Cyclist> cyclists) {
        this.cyclists = cyclists;
    }
}
