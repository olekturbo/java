package ug.aleksanderszewczak.techut.zad02.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "producer.all", query = "Select p from Producer p"),
})
public class Producer {

    private long id;
    private String name;
    private List<Bicycle> bicycles = new ArrayList<Bicycle>();

    public Producer(String name) {
        this.name = name;
    }

    public Producer() {
    	
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Bicycle> getBicycles() {
        return bicycles;
    }
    public void setBicycles(List<Bicycle> bicycles) {
        this.bicycles = bicycles;
}
}