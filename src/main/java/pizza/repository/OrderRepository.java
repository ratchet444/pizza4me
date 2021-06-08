package pizza.repository;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import pizza.control.OrderService;
import pizza.entity.Customer;
import pizza.entity.OrderItem;
import pizza.entity.Orders;

@Transactional() // Default: value = Transactional.TxType.REQUIRED
@RequestScoped
public class OrderRepository implements OrderService {
    @Inject
    EntityManager em;

    @Inject
    PizzaRepository pRepository;

    @Inject
    CustomerRepository cRepository;

    @Override
    public Collection<Orders> getAllOrders() {
        Query query = em.createQuery("SElECT e FROM Orders e");
        return query.getResultList();
    }

    @Override
    public Orders getOrderByID(long orderID) {
        Orders o = em.find(Orders.class, orderID);
        return o;
    }

    public boolean orderPizzas(long oID, long cID) {
        Orders order = em.find(Orders.class, oID);
        Customer customer = em.find(Customer.class, cID);
        customer.addOrder(order);
        em.merge(customer);
        return true;
    }

    public OrderItem setCountOfPizza(long oID, long ioID, int count){
        Orders order = em.find(Orders.class, oID);
        OrderItem item = em.find(OrderItem.class, ioID);
        if(item != null) item.setQuantity(count);
        em.merge(item);
        em.merge(order);
        return item;
    }

    public Orders addPizzaToOrder(long oID, long pID) {
        Orders order = em.find(Orders.class, oID);
        boolean isIn = false;
        for ( OrderItem i : order.getOrderItem()) {
            if(i.getPizza().getId() == pID){
                isIn = true;
                i.setQuantity(i.getQuantity() + 1);
            }
        }
        if(!isIn) {
            OrderItem item = createOrderItem(pID);
            order.addOrderItem(item);

        }
        em.merge(order);
        return order;
    }

    @Override
    public boolean removePizza(long oID, long oiID) {
        Orders order = em.find(Orders.class, oID);
        OrderItem item = em.find(OrderItem.class, oiID);
        order.removeOrderItem(item);
        em.remove(item);
        em.merge(order);
        return true;
    }

    public Orders createOrder() {
        Orders order = new Orders();
        em.persist(order);
        return order;
    }

    private OrderItem createOrderItem(long pizzaID) {
        OrderItem oItem = new OrderItem();
        oItem.setPizza(pRepository.getPizza(pizzaID));
        em.persist(oItem);
        return oItem;
    }
}
