package pizza.boundary;

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

import pizza.control.CustomerService;
import pizza.entity.Address;
import pizza.entity.Customer;

import javax.annotation.security.RolesAllowed;
import javax.annotation.security.PermitAll;

@Path("/kunden")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(value = Transactional.TxType.REQUIRES_NEW) // Default: .REQUIRED
public class CustomerResource {
    @Inject
    CustomerService cService;

    @GET
    @RolesAllowed("admin")
    @Tag(name = "Kunde")
    public Response getAllCustomers() {
        return Response.status(Response.Status.OK).entity(cService.getAllCustomer()).build();
    }

    @GET
    @RolesAllowed("user")
    @Tag(name = "Kunde")
    @Path("/{id}")
    public Response getCustomer(@PathParam("id")Long id) {
        return Response.status(Response.Status.OK).entity(cService.getCustomer(id)).build();
    }

    @POST
    @PermitAll
    @Tag(name = "Kunde")
    public Response createCustomer(@QueryParam("lastName")String surname,@QueryParam("firstName") String firstName) {
        Customer custom = new Customer();
        custom.setSurname(surname); 
        custom.setFirstName(firstName);
        return Response.status(Response.Status.OK).entity(cService.addCustomer(custom)).build();
    }

    @DELETE
    @RolesAllowed("admin")
    @Tag(name = "Kunde")
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id")Long id) {
        return Response.status(Response.Status.OK).entity(cService.deleteCustomer(id)).build();
    }

    @GET
    @RolesAllowed("user")
    @Tag(name = "Adresse")
    @Path("/{id}/address")
    public Response getAddress(@PathParam("id")Long id) {
        return Response.status(Response.Status.OK).entity(cService.getAdress(id)).build();
    }

    @POST
    @RolesAllowed("user")
    @Tag(name = "Adresse")
    @Path("/{id}/address")
    public Response createAddress(@PathParam("id")Long id, Address addr) {
        return Response.status(Response.Status.OK).entity(cService.addAdress(id, addr)).build();
    }

    @PUT
    @RolesAllowed("user")
    @Tag(name = "Adresse")
    @Path("/{id}/address")
    public Response changeAddress(@PathParam("id")Long id, Address addr) {
        return Response.status(Response.Status.OK).entity(cService.updateAdress(id, addr)).build();
    }

    @DELETE
    @RolesAllowed("admin")
    @Tag(name = "Adresse")
    @Path("/{id}/address")
    public Response deleteAddress(@PathParam("id")Long id) {
        return Response.status(Response.Status.OK).entity(cService.deleteAdress(id)).build();
    }    

    @GET
    @RolesAllowed("user")
    @Tag(name = "Kunde")
    @Path("/{cID}/orders")
    @Operation(summary = "Get Orders from Customer", description = "Get all Orders from Customer")
    public Response getOrdersFromCustomer(@PathParam("cID") long cID) {
        return Response.status(Response.Status.OK).entity(cService.getOrdersFromCustomer(cID)).build();
    }
}
