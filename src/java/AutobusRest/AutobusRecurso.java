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

/**
 * Clase la cual gestiona las acciones sobre los autobuses, insertar autobús
 * (POST) y obtener todos los autobuses (GET).
 *
 * @author Joan Creus Martin.
 */
@Path("autobus")
public class AutobusRecurso {

    /**
     * Clase la cual implementa la interfaz IAutobusesDao para gestionar las
     * acciones sobre la BD.
     */
    private final IAutobusesDAO autobusesDAO;

    /**
     * Constructor en el que inicializa el atributo con una instancia de la
     * clase AutobusesDao.
     */
    public AutobusRecurso() {
        autobusesDAO = new AutobusesDAO();
    }

    /**
     * Devuelve todos los autobuses que hay registrados en la BD.
     *
     * @return Lista con todos los autobuses convertida a JSON.
     */
    @GET
    @Path("obtenerAutobuses")
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerTodosLosAutobuses() {
        return new Gson().toJson(autobusesDAO.getTodosLosAutobuses());
    }

    /**
     * Inserta el autobús que le llega en formato JSON en la BD.
     *
     * @param autobus Autobús en formato JSON.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertarAutobus(String autobus) {
        autobusesDAO.insertarAutobus(new Gson().fromJson(autobus, Autobus.class));
    }

}
