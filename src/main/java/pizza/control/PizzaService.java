package pizza.control;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import pizza.entity.Pizza;

@RequestScoped
public interface PizzaService {

    public Collection<Pizza> getAllPizzas();
    public Pizza getPizza(Long pID);
}
