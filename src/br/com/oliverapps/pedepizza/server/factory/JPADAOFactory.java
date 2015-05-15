 package br.com.oliverapps.pedepizza.server.factory;
 
 import br.com.oliverapps.pedepizza.server.persistence.JPAGenericDAO;
 import br.com.oliverapps.pedepizza.server.persistence.JpaDao;
 import java.lang.reflect.ParameterizedType;
 import java.lang.reflect.Type;
 import javax.enterprise.inject.Produces;
 import javax.enterprise.inject.spi.Annotated;
 import javax.enterprise.inject.spi.BeanManager;
 import javax.enterprise.inject.spi.InjectionPoint;
 import javax.persistence.EntityManager;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.criteria.CriteriaBuilder;
 
 public class JPADAOFactory<T>
 {
   @javax.persistence.PersistenceContext(unitName="pedepizza_prod")
   private EntityManager entityManager;
   
   public JPADAOFactory() {}
   
   @Produces
   @JpaDao
   public <T extends br.com.oliverapps.pedepizza.bean.entity.IPedePizzaEntity> JPAGenericDAO<T> create(InjectionPoint p, BeanManager bm)
   {
     if (p.getAnnotated().isAnnotationPresent(JpaDao.class)) {
       ParameterizedType type = (ParameterizedType)p.getType();
       Type[] typeArgs = type.getActualTypeArguments();
       Class<T> entityClass = (Class)typeArgs[0];
       CriteriaBuilder cb = this.entityManager.getEntityManagerFactory().getCriteriaBuilder();
       return new JPAGenericDAO(this.entityManager, cb, entityClass);
     }
     throw new IllegalArgumentException("Anotacao @JpaDao eh obrigatoria na injecao do JPAGenericDAO");
   }
 }
