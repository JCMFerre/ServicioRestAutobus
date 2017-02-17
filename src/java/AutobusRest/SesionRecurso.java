package AutobusRest;

import DAO.AutobusesDAO;
import DAO.IAutobusesDAO;
import Model.Autobus;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("sesion")
public class SesionRecurso {

    private final IAutobusesDAO autobusesDAO;

    public SesionRecurso() {
        autobusesDAO = new AutobusesDAO();
    }

    @GET
    @Path("validarSesion/{autobus}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getValidacionInicioSesion(@PathParam("autobus") String autobus) {
        Gson gson = new Gson();
        return gson.toJson(autobusesDAO.getValidacionInicioSesion(gson.fromJson(autobus, Autobus.class)));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void cerrarSesion(String matricula) {
        autobusesDAO.cerrarSesion(matricula);
    }
}
