 package br.com.oliverapps.pedepizza.server.control;
 
 import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.apache.log4j.Logger;

import br.com.oliverapps.pedepizza.bean.entity.Cardapio;
import br.com.oliverapps.pedepizza.bean.entity.Pizza;
import br.com.oliverapps.pedepizza.server.persistence.JPAGenericDAO;
import br.com.oliverapps.pedepizza.server.persistence.JpaDao;
import br.com.oliverapps.pedepizza.server.service.ejb.ICardapioLocalEJB;
 
 @Stateless
 @Local({ICardapioLocalEJB.class})
 public class CardapioEJBImpl implements ICardapioLocalEJB
 {
   private Logger LOGGER = Logger.getLogger(getClass().getSimpleName());
   @Inject
   @JpaDao
   private JPAGenericDAO<Cardapio> jpaDao;
   @Inject
   @JpaDao
   private JPAGenericDAO<Pizza> jpaDaoPizza;
   
   public CardapioEJBImpl() {}
   
   public Map<String, List<Pizza>> consultarPizzasDoCardapio(String id) { if (id == null) {
       this.LOGGER.error("consultarPizzasDoCardapio: id do cardapio NULO");
     }
     List<String> categoriasDesseCardapio = this.jpaDao.callNamedQuery("getCategoriasDoCardapio", "id", Byte.valueOf(id));
     List<Pizza> pizzas = new ArrayList();
     String jpqlEditada = null;
     String jpql = "SELECT p FROM Pizza p, Cardapio c WHERE p.cardapio.id = c.id AND c.id = " + id + " AND (";     
	for (String cat : categoriasDesseCardapio)
     {
       jpql = jpql + " p.categoriaCardapio = '" + cat + "' or ";
       jpqlEditada = jpql.substring(0, jpql.lastIndexOf("or "));
     }
     jpqlEditada = jpqlEditada + ")";
    pizzas = this.jpaDaoPizza.executarJPQL(jpqlEditada);
     return filtrarPizzasPorCategoria(categoriasDesseCardapio, pizzas);
   }
   
  private Map<String, List<Pizza>> filtrarPizzasPorCategoria(List<String> categoriasDesseCardapio, List<Pizza> pizzas)
   {
     Map<String, List<Pizza>> pizzasPorCategoria = new HashMap();
     List<Pizza> pizzasFiltradas = new ArrayList();
     for (String cat : categoriasDesseCardapio) {
       for (Pizza pizza : pizzas) {
         if (pizza.getCategoriaCardapio().equals(cat))
           pizzasFiltradas.add(pizza);
      }
       pizzasPorCategoria.put(cat, new ArrayList(pizzasFiltradas));
       pizzasFiltradas.clear();
    }
     return pizzasPorCategoria;
   }
   
  public Collection<Pizza> consultarPorCategoria(String c, String idCardapio)
   {
     Collection<Pizza> lista = ((Cardapio)this.jpaDao.findById(Byte.valueOf(idCardapio))).getPizzas();
     Collection<Pizza> out = new ArrayList();
     for (Pizza p : lista) {
       if (p.getCategoriaCardapio().equals(c))
         out.add(p);
     }
     return out;
  }
  
   public Cardapio incluir(Cardapio c)
   {
    return (Cardapio)this.jpaDao.incluir(c);
  }
   
   public void atualizar(Cardapio c)
   {
     this.jpaDao.atualizar(c);
  }
   
   public void remover(String id)
   {
   Cardapio c = loadCardapio(id);
     this.jpaDao.remover(c);
   }
   
   private Cardapio loadCardapio(String id) {
     Cardapio c = (Cardapio)this.jpaDao.findById(Byte.valueOf(id));
     if (c == null) {
       throw new EntityNotFoundException(
         "Cardapio inexistente. ID CARDAPIO: " + id);
     }
   return c;
   }
 }
