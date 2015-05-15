package br.com.oliverapps.pedepizza.server.service.ejb;

import br.com.oliverapps.pedepizza.bean.entity.Pedido;
import java.util.List;

public abstract interface IPedidoLocalEJB
{
  public abstract List<Pedido> consultarPorSituacao(String paramString);
  
  public abstract List<Pedido> consultarPorDataHoraCriacao(String paramString);
  
  public abstract List<Pedido> consultarPorDataHoraEntregaPrevista(String paramString);
  
  public abstract List<Pedido> consultarPorPizzaria(Long paramLong);
  
  public abstract Pedido incluir(Pedido paramPedido);
  
  public abstract void atualizar(Pedido paramPedido);
  
  public abstract void cancelar(Pedido paramPedido);
}