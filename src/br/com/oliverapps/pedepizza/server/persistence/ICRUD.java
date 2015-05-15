package br.com.oliverapps.pedepizza.server.persistence;

import br.com.oliverapps.pedepizza.bean.entity.IPedePizzaEntity;
import java.util.List;
import javax.validation.constraints.NotNull;

public abstract interface ICRUD<T extends IPedePizzaEntity>
{
  public abstract List<T> consultarTodos();
  
  public abstract T findById(Object paramObject);
  
  public abstract List<T> consultarPorCampo(@NotNull String paramString, @NotNull Object paramObject);
  
  public abstract T incluir(T paramT);
  
  public abstract void atualizar(T paramT);
  
  public abstract void remover(T paramT);
}