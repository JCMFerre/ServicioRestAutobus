package DAO;

import Model.Autobus;
import Model.Ruta;
import java.util.List;

public interface IAutobusesDAO {

    String TABLA_USUARIOS = "USUARIOS_AUTOBUSES";
    String KEY_MATRICULA_USUARIOS = "MATRICULA";
    String KEY_CONTRASENA_USUARIOS = "CONTRASENA";
    String KEY_ESTADO_USUARIOS = "ESTADO";

    String TABLA_RUTAS = "RUTAS_AUTOBUSES";
    String KEY_IDSESION_RUTAS = "ID_SESION";
    String KEY_MATRICULA_RUTAS = "MATRICULA";
    String KEY_FECHA_RUTAS = "FECHA";
    String KEY_LATITUD_RUTAS = "LATITUD";
    String KEY_LONGITUD_RUTAS = "LONGITUD";

    List<Ruta> getUltimasPosicionesAutobuses();

    List<Ruta> getTodasLasRutasPorMatricula(String matricula);

    List<Ruta> getRutaCompletaPorIdSesion(String idSesion);

    boolean getValidacionInicioSesion(Autobus autobus);

    void cerrarSesion(String matricula);

    void insertarLocalizacion(Ruta trozoRuta);

    void insertarAutobus(Autobus autobus);

}
