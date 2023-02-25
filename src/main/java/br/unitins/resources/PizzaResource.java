package br.unitins.resources;
import br.unitins.model.Pizza;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Produces;

@Path("/pizza")
public class PizzaResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Pizza insert(Pizza random){
    
        if (random.isBorda()==false) {
            random.setSaborBorda(null);
        }

        random.persist();
        return random;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List <Pizza> getPizzas(){

        return Pizza.findAll().list();
    }

    @GET
    @Path("/{id}")
    public  Pizza getPizzaId (@PathParam("id") Long id){

        return Pizza.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public boolean delete(@PathParam("id") Long id){

        return Pizza.deleteById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Pizza update (@PathParam("id") Long id, Pizza random){

        Pizza upPizza = Pizza.findById(id);

        upPizza.setSabor(random.getSabor());
        upPizza.setTamanho(random.getTamanho());
        upPizza.setValor(random.getValor());
        upPizza.setBorda(random.isBorda());
        upPizza.setSaborBorda(random.getSaborBorda());

        return upPizza;

    }
}