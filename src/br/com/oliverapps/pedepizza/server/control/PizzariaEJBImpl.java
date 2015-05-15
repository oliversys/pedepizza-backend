 package br.com.oliverapps.pedepizza.server.control;
 
 import br.com.oliverapps.pedepizza.bean.entity.Pizzaria;
 import br.com.oliverapps.pedepizza.server.persistence.JPAGenericDAO;
 import br.com.oliverapps.pedepizza.server.persistence.JpaDao;
 import br.com.oliverapps.pedepizza.server.service.ejb.IPizzariaLocalEJB;
 import java.util.List;
 import javax.ejb.Stateless;
 import javax.persistence.EntityNotFoundException;
 
 @Stateless
 @javax.ejb.Local({IPizzariaLocalEJB.class})
 public class PizzariaEJBImpl implements IPizzariaLocalEJB
 {
   @javax.inject.Inject
   @JpaDao
   private JPAGenericDAO<Pizzaria> jpaDao;
   
   public PizzariaEJBImpl() {}
   
   public List<Pizzaria> consultarTodos()
   {
     return this.jpaDao.consultarTodos();
   }
   
   public List<Pizzaria> consultarPorNome(String nome) {
     return this.jpaDao.consultarPorCampo("nome", nome);
   }
   
   public List<Pizzaria> consultarPorCEP(String cep) {
     return this.jpaDao.consultarPorCampo("cep", cep);
   }
   
   public List<Pizzaria> consultarAssinantes() {
     List<Pizzaria> lista = this.jpaDao.consultarPorCampo("isAssinante", Boolean.valueOf(true));
     return lista;
   }
   
   public Pizzaria incluir(Pizzaria p) {
     Pizzaria managedObject = (Pizzaria)this.jpaDao.incluir(p);
     return managedObject;
   }
   
   public void remover(String id) throws EntityNotFoundException {
     Pizzaria pizzaria = loadPizzaria(id);
     this.jpaDao.remover(pizzaria);
   }
   
   private Pizzaria loadPizzaria(String id) {
     Pizzaria pizzaria = (Pizzaria)this.jpaDao.findById(new Long(id));
     if (pizzaria == null) {
       throw new EntityNotFoundException(
         "Nao eh possivel excluir pizzaria porque ela nao existe. ID PIZZARIA: " + id);
     }
     return pizzaria;
   }
   
 
   public void atualizar(Pizzaria p)
   {
     this.jpaDao.atualizar(p);
   }
 }
