package br.com.oliverapps.pedepizza.server.service.ejb;

import br.com.oliverapps.pedepizza.bean.entity.Pizzaria;
import java.util.List;

public abstract interface IPizzariaLocalEJB
{
  public abstract List<Pizzaria> consultarTodos();
  
  public abstract List<Pizzaria> consultarPorNome(String paramString);
  
  public abstract List<Pizzaria> consultarPorCEP(String paramString);
  
  public abstract List<Pizzaria> consultarAssinantes();
  
  public abstract Pizzaria incluir(Pizzaria paramPizzaria);
  
  public abstract void atualizar(Pizzaria paramPizzaria);
  
  public abstract void remover(String paramString);
}