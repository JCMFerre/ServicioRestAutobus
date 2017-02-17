package AutobusRest;

import DAO.AutobusesDAO;
import DAO.IAutobusesDAO;
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
    @Path("rutaCompleta/{idSesion}")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerRutaCompletaPorIdSesion(@PathParam("idSesion") String idSesion) {
        return new Gson().toJson(autobusesDAO.getRutaCompletaPorIdSesion(idSesion));
    }

    @GET
    @Path("todasLasRutas/{matricula}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTodasLasRutasPorMatricula(@PathParam("matricula") String matricula) {
        return new Gson().toJson(autobusesDAO.getTodasLasRutasPorMatricula(matricula));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertarLocalizacion(String trozoRuta) {
        autobusesDAO.insertarLocalizacion(new Gson().fromJson(trozoRuta, Ruta.class));
    }

}
