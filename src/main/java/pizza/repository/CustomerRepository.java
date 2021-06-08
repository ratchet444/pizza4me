package pizza.repository;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import pizza.control.CustomerService;
import pizza.entity.Address;
import pizza.entity.Customer;
import pizza.entity.Orders;

@Transactional() // Default: value = Transactional.TxType.REQUIRED
@RequestScoped
public class CustomerRepository implements CustomerService {
    @Inject
    EntityManager em;
    @Inject
    OrderRepository oRepository;

    public Customer addCustomer(Customer customer){
       
        if(customer != null){
            em.persist(customer);
        }

        return customer; 
    }

    public Collection<Customer> getAllCustomer(){
        
        Query q = em.createQuery("SELECT e FROM Customer e");
        
        return q.getResultList();
    }

    public Customer getCustomer(Long cNumber){
        return em.find(Customer.class, cNumber); 
    }


    @Override
    public Collection<Orders> getOrdersFromCustomer(long cID) {
        return getCustomer(cID).getOrders();
    }

    public boolean deleteCustomer(Long cNumber){

        Customer customer = em.getReference(Customer.class, cNumber);
        if(customer != null){
            em.remove(customer);
            return true; 
        }
        return false; 
    }

    public Customer addAdress(Long cNumber, Address addr){
        
        if(getCustomer(cNumber) != null){
            Customer customer = getCustomer(cNumber);
            customer.setAddr(addr);
            em.merge(customer);;
            return customer;
        }
        return null; 
    }

    public Customer updateAdress(Long cNumber, Address newAddr){
        
        if(getCustomer(cNumber) != null){
            Customer customer = getCustomer(cNumber);
            customer.setAddr(newAddr);
            em.merge(customer);;
            return customer;
        }
        return null;
    }

    public Address getAdress(Long cNumber){
        
        if(getCustomer(cNumber) != null){
             return getCustomer(cNumber).getAddr();
        }
        return null;
    }

    public boolean deleteAdress(Long cNumber){

        if(getCustomer(cNumber) != null){
            getCustomer(cNumber).setAddr(null);; 
            return true; 
        }
        return false;
    }

    
}
