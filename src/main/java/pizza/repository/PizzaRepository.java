package pizza.repository;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import pizza.control.PizzaService;
import pizza.entity.Pizza;

@Transactional() // Default: value = Transactional.TxType.REQUIRED
@RequestScoped
public class PizzaRepository implements PizzaService{
    @Inject
    EntityManager em; 

    
    public Collection<Pizza> getAllPizzas(){
        
        Query q = em.createQuery("SELECT e FROM Pizza e");
        
        return q.getResultList();
    }

    public Pizza getPizza(Long pID){
        Pizza pizza = em.find(Pizza.class, pID); 
        return pizza;
    }
    
}
