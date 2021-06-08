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


@Entity
@Vetoed
public class Orders {
   
    @Id
    @SequenceGenerator(name = "orderSeq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "orderSeq")
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItem = new ArrayList<>();
    
    public void addOrderItem(OrderItem newItem){
        orderItem.add(newItem);
    }
    public void removeOrderItem(OrderItem newItem){
        orderItem.remove(newItem);
    }

    //==========GETTER==========
    public long getId() {
        return id;
    }

     public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    //==========SETTER==========
    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }
    public void setId(long id) {
        this.id = id;
    }
}
