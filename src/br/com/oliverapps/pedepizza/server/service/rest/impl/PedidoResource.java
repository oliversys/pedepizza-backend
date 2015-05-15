package br.com.oliverapps.pedepizza.server.service.rest.impl;
 
 import java.util.Hashtable;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Response;

import br.com.oliverapps.pedepizza.bean.entity.Pedido;
import br.com.oliverapps.pedepizza.server.control.CardapioEJBImpl;
import br.com.oliverapps.pedepizza.server.service.ejb.ICardapioLocalEJB;
import br.com.oliverapps.pedepizza.server.service.ejb.IPedidoLocalEJB;
import br.com.oliverapps.pedepizza.server.service.rest.IPedidoRESTService;
 
 public class PedidoResource implements IPedidoRESTService
 {
   @EJB	 
   private IPedidoLocalEJB ejb;
   
   public PedidoResource()
   {
     doSessionEJBlookup();
   }   
 
   public Response consultarPorSituacao(String situacao)
   {
     return null;
   }
 
   public Response consultarPorDataHoraCriacao(String criadoEm)
   {
     return null;
   }   
 
   public Response consultarPorDataHoraEntregaPrevista(String criadoEm)
   {
     return null;
   }
 
   public Response consultarPorPizzaria(Long pizzariaId)
   {
     return null;
   }
 
   public Response incluir(Pedido p)
   {
     return null;
   }
 
   public Response atualizar(Pedido p)
   {
     return null;
   }
 
   public Response cancelar(String id)
   {
     return null;
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
       this.ejb = ((IPedidoLocalEJB)context.lookup(
         "java:app/pedepizza-backend/" + beanName + "!" + viewClassName));
     } catch (NamingException e) {
       e.printStackTrace();
     }
   }
 }