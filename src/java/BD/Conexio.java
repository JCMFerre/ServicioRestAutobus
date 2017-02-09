package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            conexio = DriverManager.getConnection("jdbc:oracle:thin:@ieslaferreria.xtec.cat:8081:INSLAFERRERI", "JOANCM", "JOAN95");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
