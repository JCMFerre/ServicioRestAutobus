package DAO;

import BD.Conexio;
import Model.Autobus;
import Model.Ruta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutobusesDAO implements IAutobusesDAO {

    private final Connection conexion;

    public AutobusesDAO() {
        conexion = new Conexio().getConexioOracle();
    }

    @Override
    public List<Ruta> getUltimasPosicionesAutobuses() {
        String query = "SELECT * FROM " + TABLA_RUTAS + " WHERE (" + KEY_MATRICULA_RUTAS + ", " + KEY_FECHA_RUTAS
                + ") IN (SELECT " + KEY_MATRICULA_RUTAS + ", MAX(" + KEY_FECHA_RUTAS + ") FROM " + TABLA_RUTAS
                + " GROUP BY " + KEY_MATRICULA_RUTAS + ")";
        return ejecutarPreparedStatementRutas(query, false, null);
    }

    @Override
    public List<Ruta> getTodasLasRutasPorMatricula(String matricula) {
        String query = "SELECT * FROM " + TABLA_RUTAS + " WHERE " + KEY_MATRICULA_RUTAS + " = ? AND "
                + KEY_IDSESION_RUTAS + " IN (SELECT " + KEY_IDSESION_RUTAS + " FROM " + TABLA_RUTAS
                + " GROUP BY " + KEY_IDSESION_RUTAS + ") ORDER BY " + KEY_IDSESION_RUTAS + ", " + KEY_FECHA_RUTAS;
        return ejecutarPreparedStatementRutas(query, true, matricula);
    }

    @Override
    public void insertarLocalizacion(Ruta trozoRuta) {
        String query = "INSERT INTO " + TABLA_RUTAS + " VALUES(?, ?, ?, ?, ?)";
        ejecutarQuery(query, new String[]{trozoRuta.getIdSesion().toUpperCase(),
            trozoRuta.getMatricula().toUpperCase(), trozoRuta.getFecha()},
                new double[]{trozoRuta.getLatitud(), trozoRuta.getLongitud()});
    }

    @Override
    public void insertarAutobus(Autobus autobus) {
        String query = "INSERT INTO " + TABLA_USUARIOS + " VALUES (?,?,?)";
        ejecutarQuery(query, new String[]{autobus.getMatricula().toUpperCase(),
            autobus.getContrasena(), "N"}, null);
    }

    private void cambiarEstadoUsuario(String matricula, boolean activo) {
        String query = "UPDATE " + TABLA_USUARIOS + " SET " + KEY_ESTADO_USUARIOS + " = ? WHERE "
                + KEY_MATRICULA_USUARIOS + " = ?";
        ejecutarQuery(query, new String[]{(activo ? "S" : "N"), matricula.toUpperCase()}, null);
    }

    private Ruta obtenerRutaPorRs(ResultSet rs) throws SQLException {
        return new Ruta(rs.getString(KEY_IDSESION_RUTAS), rs.getString(KEY_MATRICULA_RUTAS), rs.getString(KEY_FECHA_RUTAS),
                rs.getDouble(KEY_LATITUD_RUTAS), rs.getDouble(KEY_LONGITUD_RUTAS));
    }

    @Override
    public void cerrarSesion(String matricula) {
        cambiarEstadoUsuario(matricula, false);
    }

    private void ejecutarQuery(String query, String[] campos, double[] camposDouble) {
        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            prepararPsCampos(campos, ps, camposDouble);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void prepararPsCampos(String[] campos, final PreparedStatement ps, double[] camposDouble) throws SQLException {
        int contador = 1;
        for (String campo : campos) {
            ps.setString(contador++, campo);
        }
        if (camposDouble != null) {
            for (double d : camposDouble) {
                ps.setDouble(contador++, d);
            }
        }
    }

    private List<Ruta> ejecutarPreparedStatementRutas(String query, boolean ponerString, String campo) {
        List<Ruta> rutas = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            if (ponerString) {
                ps.setString(1, campo);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rutas.add(obtenerRutaPorRs(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rutas;
    }

    @Override
    public boolean getValidacionInicioSesion(Autobus autobus) {
        int comprobacion = 0;
        String query = "SELECT * FROM " + TABLA_USUARIOS + " WHERE UPPER(" + KEY_MATRICULA_USUARIOS
                + ") = ? AND " + KEY_CONTRASENA_USUARIOS + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, autobus.getMatricula().toUpperCase());
            ps.setString(2, autobus.getContrasena());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comprobacion = rs.getString(KEY_ESTADO_USUARIOS).equals("N") ? 2 : 1;
            }
            if (comprobacion == 2) {
                cambiarEstadoUsuario(autobus.getMatricula(), true);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return comprobacion == 2;
    }

    @Override
    public List<Autobus> getTodosLosAutobuses() {
        List<Autobus> autobuses = new ArrayList<>();
        String query = "SELECT * FROM " + TABLA_USUARIOS;
        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                autobuses.add(obtenerAutobusPorRs(rs));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return autobuses;
    }

    private Autobus obtenerAutobusPorRs(ResultSet rs) throws SQLException {
        return new Autobus(rs.getString(KEY_MATRICULA_USUARIOS),
                rs.getString(KEY_CONTRASENA_USUARIOS), rs.getString(KEY_ESTADO_USUARIOS).equals("S"));
    }
}
