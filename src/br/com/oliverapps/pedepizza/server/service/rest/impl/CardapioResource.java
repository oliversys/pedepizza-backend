 package br.com.oliverapps.pedepizza.server.service.rest.impl;
 
 import br.com.oliverapps.pedepizza.bean.domain.EnumCategoriaCardapio;
import br.com.oliverapps.pedepizza.bean.entity.Cardapio;
import br.com.oliverapps.pedepizza.bean.entity.Pizza;
import br.com.oliverapps.pedepizza.server.control.CardapioEJBImpl;
import br.com.oliverapps.pedepizza.server.service.ejb.ICardapioLocalEJB;
import br.com.oliverapps.pedepizza.server.service.rest.ICardapioRESTService;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
 
 @TransactionManagement(TransactionManagementType.CONTAINER)
 @Path("/cardapio")
 public class CardapioResource implements ICardapioRESTService
 {
   @Inject
   private ICardapioLocalEJB ejb;
   
   public CardapioResource()
   {
     doSessionEJBlookup();
   }
   
   @Path("/{id}")
   @GET
   @Produces({"application/json"})
   public Response consultarPizzasDoCardapio(@PathParam("id") String id)
   {
     Map<String, List<Pizza>> mapa = this.ejb.consultarPizzasDoCardapio(id);
     return Response.ok().entity(mapa).build();
   } 
 
   @Path("/{idCardapio}/{categoria}")
   @GET
   @Produces({"application/json"})
   public Response consultarPorCategoria(@PathParam("categoria") String categoria, @PathParam("idCardapio") String cardapio)
   {
     if (!EnumCategoriaCardapio.contains(categoria)) {
       return 
         Response.status(Response.Status.BAD_REQUEST).entity("Categoria informada inexistente: " + categoria).build();
     }
     
     Collection<Pizza> lista = this.ejb.consultarPorCategoria(categoria, cardapio);
     return Response.ok().entity(lista).build();
   }
   
   @Path("/incluir")
   @PUT
   @Consumes({"application/json"})
   public Response incluir(Cardapio c)
   {
     Cardapio managed_obj = this.ejb.incluir(c);
     if (managed_obj != null)
       return Response.ok().entity(managed_obj).build();
     return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
   }
   
   @Path("/remover/{id}")
   @DELETE
   public Response remover(@PathParam("id") String id)
   {
     this.ejb.remover(id);
     return Response.ok().build();
   }
    
   @PUT
   @Consumes({"application/json"})
   public void atualizar(Cardapio c)
   {
     this.ejb.atualizar(c);
   }
   
   private void doSessionEJBlookup() {
     Hashtable<String, String> jndiProperties = new Hashtable();
     jndiProperties.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
     Context context = null;
     String appName = "pedepizza-backend";
     String beanName = CardapioEJBImpl.class.getSimpleName();
     String viewClassName = ICardapioLocalEJB.class.getName();
     try
     {
       context = new InitialContext(jndiProperties);
       this.ejb = ((ICardapioLocalEJB)context.lookup(
         "java:app/pedepizza-backend/" + beanName + "!" + viewClassName));
     } catch (NamingException e) {
       e.printStackTrace();
     }
   }
 }
