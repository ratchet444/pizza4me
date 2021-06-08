// ###################################################
// Autor: Heiko Simon
// Datum: 02.06.21
// ###################################################

package pizza.boundary;

import java.util.Collection;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/* import io.quarkus.runtime.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License; */
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.Operation;


import pizza.control.PizzaService;
import pizza.entity.Pizza;

/* @OpenAPIDefinition(
    info = @Info(
        title="Pizza4Me API",
        version = "0.1",
        contact = @Contact(
            name = "Heiko Simon",
            email = "heiko.simon@hs-osnabrueck.de"),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
) */


@Path("/pizzas")
@PermitAll
@Tag(name = "Pizza")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(value = Transactional.TxType.REQUIRES_NEW) // Default: REQUIRED
public class PizzaResource {

    @Inject
    PizzaService pizzaService;

    // Liste mit allen Pizzen ausgeben
    @GET
    @Operation(summary = "Get all Pizzas", description = "Gets a List of all Pizzas")
    public Response getAllPizzas() {
        Collection<Pizza> pizzas = pizzaService.getAllPizzas();
        return Response.status(Response.Status.OK).entity(pizzas).build();
    }

    // Pizza Details anfordern
    @GET
    @Path("/{PizzaID}")
    @Operation(summary = "Get Pizza with ID", description = "Gets a specific Pizza by ID")
    public Response getPizza(@PathParam("PizzaID") long id) {
        return Response.status(Response.Status.OK).entity(pizzaService.getPizza(id)).build();
    }
}