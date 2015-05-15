package br.com.oliverapps.pedepizza.server.service.rest;

import br.com.oliverapps.pedepizza.bean.entity.Pedido;
import javax.ws.rs.core.Response;

public abstract interface IPedidoRESTService
{
  public abstract Response consultarPorSituacao(String paramString);
  
  public abstract Response consultarPorDataHoraCriacao(String paramString);
  
  public abstract Response consultarPorDataHoraEntregaPrevista(String paramString);
  
  public abstract Response consultarPorPizzaria(Long paramLong);
  
  public abstract Response incluir(Pedido paramPedido);
  
  public abstract Response atualizar(Pedido paramPedido);
  
  public abstract Response cancelar(String paramString);
}