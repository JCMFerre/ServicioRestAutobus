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

/**
 * Clase la cual gestiona las rutas, insertar ruta (POST), obtener ultimas
 * posiciones de los autobuses (GET) y obtener todas las rutas de una matrícula
 * (De un autobús) (GET).
 *
 * @author Joan Creus Martin.
 */
@Path("rutas")
public class RutasRecurso {

    /**
     * Clase la cual implementa la interfaz IAutobusesDao para gestionar las
     * acciones sobre la BD.
     */
    private final IAutobusesDAO autobusesDAO;

    /**
     * Constructor en el que inicializa el atributo con una instancia de la
     * clase AutobusesDao.
     */
    public RutasRecurso() {
        autobusesDAO = new AutobusesDAO();
    }

    /**
     * Devuelve una lista de las últimas posiciones de todos los autobuses (Que
     * han tenido por lo menos alguna ruta).
     *
     * @return Lista en formato GSON con todas las últimas posiciones.
     */
    @GET
    @Path("ultimasPosiciones")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUltimasPosicionesAutobuses() {
        return new Gson().toJson(autobusesDAO.getUltimasPosicionesAutobuses());
    }

    /**
     * Devuelve una lista con todas las rutas del autobús que le llega por
     * parámetro.
     *
     * @param autobus Autobús del cual se quieren obtener todas las rutas, en
     * formato JSON.
     * @return Lista con todas las rutas en formato JSON.
     */
    @GET
    @Path("todasLasRutas/{autobus}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getTodasLasRutasPorMatricula(@PathParam("autobus") String autobus) {
        Gson gson = new Gson();
        return gson.toJson(autobusesDAO
                .getTodasLasRutasPorMatricula(gson.fromJson(autobus, Autobus.class).getMatricula()));
    }

    /**
     * Inserta la ruta que le llega por parámetro en formato JSON en la BD.
     *
     * @param trozoRuta Ruta a añadir en formato JSON.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertarLocalizacion(String trozoRuta) {
        autobusesDAO.insertarLocalizacion(new Gson().fromJson(trozoRuta, Ruta.class));
    }

}
