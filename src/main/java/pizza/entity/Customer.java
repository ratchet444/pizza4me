package pizza.entity;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Vetoed;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Vetoed
@Entity
public class Customer {
    
    @Id
    @SequenceGenerator(name = "customerSeq", initialValue = 1)
    @GeneratedValue(generator = "customerSeq")
    private long id; 
    private String surname; 
    private String firstName;
    private Address addr; 

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Orders> orders = new ArrayList<>();

    public Customer(){

    }

    //==========GETTER==========
    public Address getAddr() {
        return addr;
    }
    public String getFirstName() {
        return firstName;
    }
    public long getId() {
        return id;
    }
    public String getSurname() {
        return surname;
    }
    public List<Orders> getOrders() {
        return orders;
    }

    //==========SETTER==========
    public void setAddr(Address addr) {
        this.addr = addr;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void addOrder(Orders order) {
        this.orders.add(order);
    }
    
}
