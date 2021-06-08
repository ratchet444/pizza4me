package pizza.control;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import pizza.entity.OrderItem;
import pizza.entity.Orders;

@RequestScoped
public interface OrderService {
    public Collection<Orders> getAllOrders();
    public Orders getOrderByID(long orderID);
    public boolean orderPizzas(long oID, long cID);
    public OrderItem setCountOfPizza(long oID, long oiID, int count);
    public Orders addPizzaToOrder(long oID, long pID);
    public boolean removePizza(long oID, long oiID);
    
    public Orders createOrder();
}
