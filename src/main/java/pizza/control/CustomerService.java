package pizza.control;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import pizza.entity.Address;
import pizza.entity.Customer;
import pizza.entity.Orders;



@RequestScoped
//@ApplicationScoped
public interface CustomerService {

    public Customer addCustomer(Customer customer);

    public Collection<Customer> getAllCustomer();

    public Customer getCustomer(Long cNumber);

    public boolean deleteCustomer(Long cNumber);

    public Customer addAdress(Long cNumber, Address addr);

    public Customer updateAdress(Long cNumber, Address newAddr);

    public Address getAdress(Long cNumber);

    public boolean deleteAdress(Long cNumber);

    public Collection<Orders> getOrdersFromCustomer(long cID);
}
