/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutobusRest;

import Model.Autobus;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author nrpc
 */
@Path("autobus")
public class AutobusRecurso {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AutobusRecurso
     */
    public AutobusRecurso() {
    }

    /**
     * Retrieves representation of an instance of AutobusRest.AutobusRecurso
     *
     * @param autobus
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEstadoInicioSesion(String autobus) {
        boolean inicioCorrecto = false;
        Gson gson = new Gson();
        Autobus prueba = gson.fromJson(autobus, Autobus.class);
        
        return String.valueOf(inicioCorrecto);
    }

    /**
     * PUT method for updating or creating an instance of AutobusRecurso
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void anadir(String autobus) {
        // FALTA HACER TODOS LOS METODOS;
    }
}
