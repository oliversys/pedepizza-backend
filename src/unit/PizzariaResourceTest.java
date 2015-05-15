 package unit;
 
 import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.oliverapps.pedepizza.server.service.rest.impl.PizzariasResource;
 
 public class PizzariaResourceTest
 {
   private PizzariasResource resource = new PizzariasResource(); 
 
   public PizzariaResourceTest() {}
   
   @Before
   public void setUp() {}
   
   @Test
   public void consultarTodosTest()
   {
     Response r = this.resource.consultarTodos();
     Assert.assertFalse(((List)r.getEntity()).isEmpty());
   }
   
   @After
   public void tearDown() {}
 }