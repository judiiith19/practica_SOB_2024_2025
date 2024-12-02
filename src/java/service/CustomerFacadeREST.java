/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entities.Customer;
import java.util.List;

/**
 *
 * @author JUDITH
 */

@Stateless
@Path("/api/v1/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerFacadeREST {
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public CustomerFacadeREST() {}
    
    // Endpoint GET: Llistar tots els usuaris
    @GET
    public Response getAllCustomers() {
        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        
        customers.forEach(customer -> customer.setPassword(null)); // Ocultar les contrasenyes.
        
        return Response.ok(customers).build(); // Respon amb 200 OK i la llista.
    }
    
    // Endpoint GET: Obtenir info d'un usuari per ID
    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        Customer customer = em.find(Customer.class, id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // 404 si no existeix.
        }
        customer.setPassword(null); // Oculta la contrasenya.
        return Response.ok(customer).build(); // Responde con 200 OK y el usuario.
    }
    
    // Endpoint PUT: Modificar dades d'un usuari (opcional)
    @PUT
    @Path("/{id}")
    @Secured
    public Response updateCustomer(@PathParam("id") Long id, Customer updatedCustomer) {
        Customer customer = em.find(Customer.class, id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // 404 si no existeix.
        }

        // Actualitza els camps permesos
        customer.setUsername(updatedCustomer.getUsername());
        customer.setIsAuthor(updatedCustomer.getIsAuthor());
        customer.setArticles(updatedCustomer.getArticles());
        // No actualitzem la contrasenya sense validació.

        em.merge(customer); // Guarda els canvis.
        return Response.noContent().build(); // Respon amb 204 No Content.
    }
}
