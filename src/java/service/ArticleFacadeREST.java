/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entities.Article;
import java.util.List;

/**
 *
 * @author JUDITH
 */

@Stateless // Indica que es un EJB sense estat.
@Path("/api/v1/article") // Defineix el cami base del recurs.
@Produces(MediaType.APPLICATION_JSON) // Totes les respostes seran en JSON.
@Consumes(MediaType.APPLICATION_JSON) // Les sol·licituds han de ser en JSON.
public class ArticleFacadeREST {
    @PersistenceContext(unitName = "Homework1PU") // Conexió amb l'unitat de persistencia.
    private EntityManager em;
    
    public ArticleFacadeREST() {}
     
    // Endpoint GET: Llistar artícles amb filtres opcionals
    @GET
    public Response getArticles(@QueryParam("topic") List<String> topics, @QueryParam("author") String author) {
        String jpql = "SELECT a FROM Article a WHERE 1=1";
        if (topics != null && !topics.isEmpty()) {
            jpql += " AND EXISTS (SELECT t FROM a.topics t WHERE t IN :topics)";
        }
        if(author != null && !author.isEmpty()){
            jpql += " AND a.author = :author";
        }
        
        TypedQuery<Article> query = em.createQuery(jpql, Article.class);
        if (topics != null && !topics.isEmpty()) {
            query.setParameter("topics", topics);
        }
        if(author != null && !author.isEmpty()){
            query.setParameter("author", author);
        }
        
        List<Article> articles = query.getResultList();
        return Response.ok(articles).build();   //Respon amb 200 OK i la llista.
    }
    
    //Endpoint GET: Obtenir un artícle per ID
    @GET
    @Path("/{id}")
    public Response gerArticlesById(@PathParam("id") Long id) {
        Article article = em.find(Article.class, id);
        if (article == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // 404 si no exixteix.
        }
        article.setViews(article.getViews() + 1); //Incrmenta les views
        em.merge(article); //Actualitza en la BD.
        return Response.ok(article).build(); // Respon amb 200 OK i l'artícle.
    }
    
    //Endpoint POST: Crear un nou artícle
    @POST
    @Secured
    public Response createArticle(Article article) {
        if (article == null || article.getTitle() == null || article.getContent() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Datos incompletos").build();
        }
        article.setPublishedDate(new java.util.Date());
        em.persist(article); //Guarda l'artícle.
        return Response.status(Response.Status.CREATED).entity(article.getId()).build(); // 400 BAD REQUEST.
    }
    
    // Endpoint DELETE: Esborrar un artícle per ID
    @DELETE
    @Path("/{id}")
    @Secured
    public Response deleteArticle(@PathParam("id") Long id) {
        Article article = em.find(Article.class, id);
        if (article == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // 404 si no existeix.
        }
        em.remove(article); // Elimina l'artícle.
        return Response.noContent().build(); // 204 No Content si s'elimina.
    }
}
