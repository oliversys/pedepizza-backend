 package br.com.oliverapps.pedepizza.server.control;
 
 import br.com.oliverapps.pedepizza.bean.entity.Pedido;
 import br.com.oliverapps.pedepizza.server.persistence.JPAGenericDAO;
 import br.com.oliverapps.pedepizza.server.persistence.JpaDao;
 import br.com.oliverapps.pedepizza.server.service.ejb.IPedidoLocalEJB;
 import java.util.List;
 import javax.inject.Inject;
 
 
 
 
 public class PedidoEJBImpl
   implements IPedidoLocalEJB
 {
   @Inject
   @JpaDao
   private JPAGenericDAO<Pedido> jpaDao;
   
   public PedidoEJBImpl() {}
   
   public List<Pedido> consultarPorSituacao(String situacao)
   {
     return null;
   }
   
 
   public List<Pedido> consultarPorDataHoraCriacao(String criadoEm)
   {
     return null;
   }
   
 
   public List<Pedido> consultarPorDataHoraEntregaPrevista(String criadoEm)
   {
     return null;
   }
   
 
   public List<Pedido> consultarPorPizzaria(Long pizzariaId)
   {
     return null;
   }
   
 
   public Pedido incluir(Pedido p)
   {
     return null;
   }
   
   public void atualizar(Pedido p) {}
   
   public void cancelar(Pedido p) {}
 }
