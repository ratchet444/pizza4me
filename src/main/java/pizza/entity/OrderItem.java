package pizza.entity;

import java.io.Serializable;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Vetoed
public class OrderItem implements Serializable{
    
    @Id
    @SequenceGenerator(name = "orderItemSeq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "orderItemSeq")
    private long id;

    private  int quantity = 1; 

    @OneToOne
    private Pizza pizza;

    public OrderItem(){

    }
    //==========GETTER===========
    public long getId() {
        return id;
    }
    public int getQuantity() {
        return quantity;
    }
    public Pizza getPizza() {
        return pizza;
    }

    //==========SETTER==========
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

}
