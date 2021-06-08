package pizza.boundary;


import java.util.Collection;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import pizza.control.OrderService;
import pizza.entity.Orders;

@Path("/orders")
@Tag(name = "Order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(value = Transactional.TxType.REQUIRES_NEW) // Default: REQUIRED
public class OrderResource {
    @Inject
    OrderService oService;

    @GET
    @RolesAllowed("admin")
    @Operation(summary = "Get all Orders", description = "Get all Orders, only to Test")
    public Response getAllOrders() {
        Collection<Orders> orders = oService.getAllOrders();
        return Response.status(Response.Status.OK).entity(orders).build();
    }

    @GET
    @RolesAllowed("user")
    @Path("/{oID}")
    @Operation(summary = "Get Order by ID")
    public Response getOrderbyID(@PathParam("oID")long oID) {
        return Response.status(Response.Status.OK).entity(oService.getOrderByID(oID)).build();
    }

    // Bestellung aufgeben
    @PUT
    @RolesAllowed("user")
    @Path("/{oID}/order")
    @Operation(summary = "Order Your Pizzas", description = "Orders all Pizzas to a Customer")
    public Response orderPizzas(@PathParam("oID") long oID, @QueryParam("cID")long cID) {
        return Response.status(Response.Status.OK).entity(oService.orderPizzas(oID, cID)).build();
    }

    // Anzahl im Bestellposten ändern
    @PUT
    @RolesAllowed("user")
    @Path("/{OrderID}")
    @Operation(summary = "Set Number of Pizzas", description = "Change the number the Pizza in the OrderItem")
    public Response setCountOfPizza(@PathParam("OrderID") long oID, @QueryParam("OrderItemID") long oiID, @QueryParam("Anzahl") int count) {
        return Response.status(Response.Status.OK).entity(oService.setCountOfPizza(oID, oiID, count)).build();
    }

    // Pizza zur Bestellung hinzufügen ändern
    @POST
    @RolesAllowed("user")
    @Path("/{OrderID}")
    @Operation(summary = "Add Pizza", description = "Add new OrderItem with Pizza")
    public Response addPizza(@PathParam("OrderID") long oID, @QueryParam("PizzaID")long pID){
        return Response.status(Response.Status.OK).entity(oService.addPizzaToOrder(oID, pID)).build();

    }

    // Pizza von Bestellung löschen
    @DELETE
    @RolesAllowed("user")
    @Path("/{OrderID}")
    @Operation(summary = "Deletes Pizza from Order", description = "Deletes a Pizza from the current Order")
    public Response deletePizzaFromOrder(@PathParam("OrderID") long oID, @QueryParam("OrderItemID") long oiID) {
        return Response.status(Response.Status.OK).entity(oService.removePizza(oID, oiID)).build();
    }

    // Test Create Order
    @POST
    @RolesAllowed("user")
    @Operation(summary = "Create Order")
    public Response createOrder() {
        return Response.status(Response.Status.OK).entity(oService.createOrder()).build();
    }
}
