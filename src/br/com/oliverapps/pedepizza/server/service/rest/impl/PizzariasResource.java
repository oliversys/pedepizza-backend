 package br.com.oliverapps.pedepizza.server.service.rest.impl;
 
 import java.util.Hashtable;
import java.util.List;

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

import br.com.oliverapps.pedepizza.bean.entity.Pizzaria;
import br.com.oliverapps.pedepizza.server.control.PizzariaEJBImpl;
import br.com.oliverapps.pedepizza.server.service.ejb.IPizzariaLocalEJB;
import br.com.oliverapps.pedepizza.server.service.rest.IPizzariaRESTService;
 
 @TransactionManagement(TransactionManagementType.CONTAINER)
 @Path("/pizzarias")
 public class PizzariasResource implements IPizzariaRESTService
 {
   @Inject
   private IPizzariaLocalEJB ejb;
   
   public PizzariasResource()
   {
     doSessionEJBlookup();
   }
   
   @Path("/todas")
   @GET
   @Produces({"application/json"})
   public Response consultarTodos() {
     List<Pizzaria> lista = this.ejb.consultarTodos();
     return Response.ok().entity(lista).build();
   }
   
   @Path("/nome/{nome}")
   @GET
   @Produces({"application/json"})
   public Response consultarPorNome(@PathParam("nome") String nome)
   {
     List<Pizzaria> lista = this.ejb.consultarPorNome(nome);
     if (lista.isEmpty()) {
       return Response.status(Response.Status.NOT_FOUND).build();
     }
     return Response.ok(lista).build();
   }
   
   @Path("/cep/{cep}")
   @GET
   @Produces({"application/json"})
   public Response consultarPorCEP(@PathParam("cep") String cep)
   {
     List<Pizzaria> lista = this.ejb.consultarPorCEP(cep);
     return Response.ok().entity(lista).build();
   }
   
   @Path("/assinantes")
   @GET
   @Produces({"application/json"})
   public Response consultarAssinantes()
   {
     List<Pizzaria> lista = this.ejb.consultarAssinantes();
     return Response.ok().entity(lista).build();
   }
   
   @Path("/incluir")
   @PUT
   @Consumes({"application/json"})
   public Response incluir(Pizzaria p) {
     Pizzaria managed_p = this.ejb.incluir(p);
     if (managed_p != null)
       return Response.ok().entity(managed_p).build();
     return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
   }
   
   @PUT
   @Consumes({"application/json"})
   public void atualizar(Pizzaria p)
   {
     this.ejb.atualizar(p);
   }
   
   @Path("/remover/{id}")
   @DELETE
   public Response remover(@PathParam("id") String id) {
     this.ejb.remover(id);
     return Response.ok().build();
   }
   
   private void doSessionEJBlookup() {
     Hashtable<String, String> jndiProperties = new Hashtable();
     jndiProperties.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
     Context context = null;
     String appName = "pedepizza-backend";
     String beanName = PizzariaEJBImpl.class.getSimpleName();
     String viewClassName = IPizzariaLocalEJB.class.getName();
     try
     {
       context = new InitialContext(jndiProperties);
       this.ejb = ((IPizzariaLocalEJB)context.lookup("java:app/pedepizza-backend/" + beanName + "!" + viewClassName));
     } catch (NamingException e) {
       e.printStackTrace();
     }
   }
 }
