package DAO;

import Model.Autobus;
import Model.Ruta;
import java.util.List;

public interface IAutobusesDAO {

    // Cadenas con el nombre de la tabla usuarios y el nombre de sus columnas.
    String TABLA_USUARIOS = "USUARIOS_AUTOBUSES";
    String KEY_MATRICULA_USUARIOS = "MATRICULA";
    String KEY_CONTRASENA_USUARIOS = "CONTRASENA";
    String KEY_ESTADO_USUARIOS = "ESTADO";

    // Cadenas con el nombre de la tabla rutas y el nombre de sus columnas.
    String TABLA_RUTAS = "RUTAS_AUTOBUSES";
    String KEY_IDSESION_RUTAS = "ID_SESION";
    String KEY_MATRICULA_RUTAS = "MATRICULA";
    String KEY_FECHA_RUTAS = "FECHA";
    String KEY_LATITUD_RUTAS = "LATITUD";
    String KEY_LONGITUD_RUTAS = "LONGITUD";

    /**
     * Inserta el autobús que le llega por parámetro en la BD.
     *
     * @param autobus Autobús a insertar.
     */
    void insertarAutobus(Autobus autobus);

    /**
     * Obtiene todos los autobuses de la BD y devuelve una lista de autobuses.
     *
     * @return List de tipo Autobus.
     */
    List<Autobus> getTodosLosAutobuses();

    /**
     * Inserta la ruta que le llega por parámetro en la BD.
     *
     * @param trozoRuta Ruta a insertar.
     */
    void insertarLocalizacion(Ruta trozoRuta);

    /**
     * Obtiene las últimas posiciones de todos los autobuses que por lo menos
     * tengan una ruta relacionada.
     *
     * @return List de tipo Ruta.
     */
    List<Ruta> getUltimasPosicionesAutobuses();

    /**
     * Obtiene todas las rutas a partir de la matrícula que le llega por
     * parámetro.
     *
     * @param matricula Matrícula por la cual se filtrarán las rutas.
     * @return List de tipo Ruta.
     */
    List<Ruta> getTodasLasRutasPorMatricula(String matricula);

    /**
     * Comprueba si puede validarse, si puede iniciar sesión cambia el estado a
     * activo 'S' y devuelve true, si no devuelve false.
     *
     * @param autobus Autobús a comprobar.
     * @return boolean si es correcto el inició de sesión o no.
     */
    boolean getValidacionInicioSesion(Autobus autobus);

    /**
     * Cierra la sesión de la matrícula (Cambia el estado del autobús a no
     * activo ('N')).
     *
     * @param matricula Matrícula del autobús a cambiar el estado.
     */
    void cerrarSesion(String matricula);
}
