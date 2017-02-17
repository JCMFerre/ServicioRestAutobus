package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexio {

    private Connection conexio;

    public Conexio() {
    }

    public Connection getConexioOracle() {
        if (conexio == null) {
            obtenerInstancia();
        }
        return conexio;
    }

    private void obtenerInstancia() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
                // Aquí ira desde el cole
                conexio = DriverManager.getConnection("jdbc:oracle:thin:@ieslaferreria.xtec.cat:8081:INSLAFERRERI", "JOANCM", "JOAN95");
            } catch (SQLException sql) {
                try {
                    conexio = DriverManager.getConnection("jdbc:oracle:thin:@ieslaferreria.xtec.cat:8081:INSLAFERRERI", "JOANCM", "JOAN95");
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
