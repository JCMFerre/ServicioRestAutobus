package AutobusRest;

import DAO.AutobusesDAO;
import DAO.IAutobusesDAO;
import Model.Autobus;
import Model.Ruta;
import com.google.gson.Gson;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("autobus")
public class AutobusRecurso {

    private final IAutobusesDAO autobusesDAO;

    public AutobusRecurso() {
        autobusesDAO = new AutobusesDAO();
    }

    @GET
    @Path("getValidacionInicioSesion/{autobus}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getValidacionInicioSesion(@PathParam("autobus") String autobus) {
        Gson gson = new Gson();
        Autobus auto = gson.fromJson(autobus, Autobus.class);
        return gson.toJson(autobusesDAO.getValidacionInicioSesion(auto));
    }

    @GET
    @Path("getUltimasPosicionesAutobuses")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUltimasPosicionesAutobuses() {
        return new Gson().toJson(autobusesDAO.getUltimasPosicionesAutobuses());
    }

    @GET
    @Path("obtenerRutaCompletaPorIdSesion/{matricula}")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerRutaCompletaPorIdSesion(@PathParam("matricula") String matricula) {
        return new Gson().toJson(autobusesDAO.getRutaCompletaPorIdSesion(matricula));
    }

    @GET
    @Path("getTodasLasRutasPorMatricula/{matricula}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTodasLasRutasPorMatricula(@PathParam("matricula") String matricula) {
        return new Gson().toJson(autobusesDAO.getTodasLasRutasPorMatricula(matricula));
    }
//
//    @POST
//    @Path("cerrarSesion/{matricula}")
//    public void cerrarSesion(@PathParam("matricula") String matricula) {
//        autobusesDAO.cerrarSesion(matricula);
//    }
//
    @POST
    @Path("{trozoRuta}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertarLocalizacion(@PathParam("trozoRuta") String trozoRuta) {
        Ruta ruta = new Gson().fromJson(trozoRuta, Ruta.class);
        autobusesDAO.insertarLocalizacion(ruta);
    }
//
//    @POST
//    @Path("insertarAutobus/")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void insertarAutobus(String autobus) {
//        Autobus auto = new Gson().fromJson(autobus, Autobus.class);
//        autobusesDAO.insertarAutobus(auto);
//    }
}
