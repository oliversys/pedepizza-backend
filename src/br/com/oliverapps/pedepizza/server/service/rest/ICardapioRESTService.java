package br.com.oliverapps.pedepizza.server.service.rest;

import br.com.oliverapps.pedepizza.bean.entity.Cardapio;
import javax.ws.rs.core.Response;

public abstract interface ICardapioRESTService
{
  public abstract Response consultarPizzasDoCardapio(String paramString);
  
  public abstract Response consultarPorCategoria(String paramString1, String paramString2);
  
  public abstract Response incluir(Cardapio paramCardapio);
  
  public abstract Response remover(String paramString);
  
  public abstract void atualizar(Cardapio paramCardapio);
}