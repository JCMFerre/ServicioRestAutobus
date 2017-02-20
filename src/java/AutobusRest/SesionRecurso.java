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

/**
 * Clase la cual gestiona el inició de sesión (GET) y el cierre de sesión
 * (POST).
 *
 * @author Joan Creus Martin.
 */
@Path("sesion")
public class SesionRecurso {

    /**
     * Clase la cual implementa la interfaz IAutobusesDao para gestionar las
     * acciones sobre la BD.
     */
    private final IAutobusesDAO autobusesDAO;

    /**
     * Constructor en el que inicializa el atributo con una instancia de la
     * clase AutobusesDao.
     */
    public SesionRecurso() {
        autobusesDAO = new AutobusesDAO();
    }

    /**
     * Valida si el usuario (Autobús) puede loguearse, devuelve true si puede
     * loguearse o false en caso contrario (Puede ser que la contraseña y el
     * usuario estén bien, pero ya hayan iniciado sesión desde otro
     * dispositivo).
     *
     * Si puede validarse además de devolver true, cambia el estado del autobús
     * en la BD a activo ('S').
     *
     * @param autobus Autobús del cual se comprobarán usuario (Matrícula) y
     * contraseña.
     * @return true o false según si se ha podido o no loguear en formato JSON.
     */
    @GET
    @Path("validarSesion/{autobus}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getValidacionInicioSesion(@PathParam("autobus") String autobus) {
        Gson gson = new Gson();
        return gson.toJson(autobusesDAO.getValidacionInicioSesion(gson.fromJson(autobus, Autobus.class)));
    }

    /**
     * Cierra la sesión del autobús cambiando el estado a no activo ('N') para
     * que otros usuarios puedan conectarse.
     *
     * @param autobusJson
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void cerrarSesion(String autobusJson) {
        autobusesDAO.cerrarSesion(new Gson().fromJson(autobusJson, Autobus.class).getMatricula());
    }
}
