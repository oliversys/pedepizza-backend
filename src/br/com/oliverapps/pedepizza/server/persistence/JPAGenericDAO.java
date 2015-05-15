 package br.com.oliverapps.pedepizza.server.persistence;
 
 import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import br.com.oliverapps.pedepizza.bean.domain.EnumStatusPedido;
import br.com.oliverapps.pedepizza.bean.entity.IPedePizzaEntity;

 public class JPAGenericDAO<T extends IPedePizzaEntity> implements ICRUD<T>
 {
   private EntityManager entityManager;
   private CriteriaBuilder builder;
   private Class<T> entityClass;
   
   public JPAGenericDAO(EntityManager em, CriteriaBuilder cb, Class<T> c)
   {
     this.entityManager = em;
     this.builder = cb;
     this.entityClass = c;
   }
   
   public T findById(Object id) {
     return this.entityManager.find(this.entityClass, id);
   }
 
   public List<T> consultarTodos()
   {
     CriteriaQuery<T> query = this.builder.createQuery(this.entityClass);
     Root<T> r = query.from(this.entityClass);
     query.select(r);
     TypedQuery<T> q = this.entityManager.createQuery(query);
     return q.getResultList();
   }
   
   public List<T> consultarPorCampo(@NotNull String nomeCampo, @NotNull Object valorCampo) {
     if (!IsStatusPedidoValido(nomeCampo, valorCampo)) { return null;
     }
     CriteriaQuery<T> query = this.entityManager.getCriteriaBuilder().createQuery(this.entityClass);
     Root<T> r = query.from(this.entityClass);
     query.select(r);
     query.where(this.builder.equal(r.get(nomeCampo), valorCampo));
     
     return this.entityManager.createQuery(query).getResultList();
   }
   
   public List<T> executarJPQL(@NotNull String jpql) {
     return this.entityManager.createQuery(jpql).getResultList();
   }

   public List callNamedQuery(String name, String paramName, Object param)
   {
     Query q = this.entityManager.createNamedQuery(name);
     q.setParameter("id", param);
     return q.getResultList();
   }
   
   public T incluir(T obj)
   {
     this.entityManager.persist(obj);
     return obj;
   }
   
   public void atualizar(T obj)
   {
     this.entityManager.merge(obj);
   }
   
   public void remover(T obj)
   {
     this.entityManager.remove(obj);
   }
   
   private boolean IsStatusPedidoValido(String campo, Object valor)
   {
     if (!campo.equalsIgnoreCase("status")) { return true;
     }
     if (EnumStatusPedido.valueOf(valor.toString().toUpperCase()) != null) {
       return true;
     }
     return false;
   }
 }
