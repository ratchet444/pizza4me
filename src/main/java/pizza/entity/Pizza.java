package pizza.entity;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Vetoed
public class Pizza {
    
    @Id
    private long id;
    private String name;
    private int price;
    private String description; 

    public Pizza(){

    }
    //===========GETTER===========

    public String getDescription() {
        return description;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }

    //==========SETTER==========
    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }

}
