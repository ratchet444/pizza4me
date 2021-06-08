package pizza.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

   private String plz;
   private String place;
   private String street;
   private String houseNr; 

   public Address(){

    }

    //==========GETTER==========
    public String getHouseNr() {
        return houseNr;
    }
    public String getPlace() {
        return place;
    }
    public String getPlz() {
        return plz;
    }
    public String getStreet() {
        return street;
    }
    //==========SETTER==========
    public void setHouseNr(String houseNr) {
        this.houseNr = houseNr;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public void setPlz(String plz) {
        this.plz = plz;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    

}
