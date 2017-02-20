package AutobusRest;

import DAO.AutobusesDAO;
import DAO.IAutobusesDAO;
import Model.Autobus;
import Model.Ruta;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("rutas")
public class RutasRecurso {

    private final IAutobusesDAO autobusesDAO;

    public RutasRecurso() {
        autobusesDAO = new AutobusesDAO();
    }

    @GET
    @Path("ultimasPosiciones")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUltimasPosicionesAutobuses() {
        return new Gson().toJson(autobusesDAO.getUltimasPosicionesAutobuses());
    }

    @GET
    @Path("todasLasRutas/{autobus}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getTodasLasRutasPorMatricula(@PathParam("autobus") String autobus) {
        Gson gson = new Gson();
        return gson.toJson(autobusesDAO
                .getTodasLasRutasPorMatricula(gson.fromJson(autobus, Autobus.class).getMatricula()));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertarLocalizacion(String trozoRuta) {
        autobusesDAO.insertarLocalizacion(new Gson().fromJson(trozoRuta, Ruta.class));
    }

}
