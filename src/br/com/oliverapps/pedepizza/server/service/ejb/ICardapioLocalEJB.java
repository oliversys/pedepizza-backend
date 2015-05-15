package br.com.oliverapps.pedepizza.server.service.ejb;

import br.com.oliverapps.pedepizza.bean.entity.Cardapio;
import br.com.oliverapps.pedepizza.bean.entity.Pizza;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract interface ICardapioLocalEJB
{
  public abstract Map<String, List<Pizza>> consultarPizzasDoCardapio(String paramString);
  
  public abstract Collection<Pizza> consultarPorCategoria(String paramString1, String paramString2);
  
  public abstract Cardapio incluir(Cardapio paramCardapio);
  
  public abstract void atualizar(Cardapio paramCardapio);
  
  public abstract void remover(String paramString);
}
