package br.com.oliverapps.pedepizza.server.service.rest;

import br.com.oliverapps.pedepizza.bean.entity.Pizzaria;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

@Remote
public abstract interface IPizzariaRESTService
{
  public abstract Response consultarTodos();
  
  public abstract Response consultarPorNome(String paramString);
  
  public abstract Response consultarPorCEP(String paramString);
  
  public abstract Response consultarAssinantes();
  
  public abstract Response incluir(Pizzaria paramPizzaria);
  
  public abstract Response remover(String paramString);
  
  public abstract void atualizar(Pizzaria paramPizzaria);
}