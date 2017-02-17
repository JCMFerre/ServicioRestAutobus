package AutobusRest;

import DAO.AutobusesDAO;
import DAO.IAutobusesDAO;
import Model.Autobus;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("autobus")
public class AutobusRecurso {

    private final IAutobusesDAO autobusesDAO;

    public AutobusRecurso() {
        autobusesDAO = new AutobusesDAO();
    }

    @GET
    @Path("obtenerAutobuses")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerTodosLosAutobuses() {
        return new Gson().toJson(autobusesDAO.getTodosLosAutobuses());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertarAutobus(String autobus) {
        Autobus auto = new Gson().fromJson(autobus, Autobus.class);
        autobusesDAO.insertarAutobus(auto);
    }

}
