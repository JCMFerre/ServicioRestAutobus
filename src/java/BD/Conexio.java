package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexión con la BD de oracle.
 *
 * @author Joan Creus Martin.
 */
public class Conexio {

    private Connection conexio;

    public Conexio() {
    }

    /**
     * Devuelve la conexión a Oracle.
     *
     * @return Connection lista para ser utilizada.
     */
    public Connection getConexioOracle() {
        if (conexio == null) {
            obtenerInstancia();
            try {
                conexio.setAutoCommit(true);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return conexio;
    }

    /**
     * Intenta conectarse como si estuviera en el instituto, si salta al catch,
     * se conecta desde fuera, y si no ya apaga y vámonos... :)
     */
    private void obtenerInstancia() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
                conexio = DriverManager.getConnection("jdbc:oracle:thin:@192.168.180.10:1521:INSLAFERRERI", "JOANCM", "JOAN95");
            } catch (SQLException sql) {
                try {
                    conexio = DriverManager.getConnection("jdbc:oracle:thin:@ieslaferreria.xtec.cat:8081:INSLAFERRERI", "JOANCM", "JOAN95");
                } catch (SQLException e) {
                }
            }
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
