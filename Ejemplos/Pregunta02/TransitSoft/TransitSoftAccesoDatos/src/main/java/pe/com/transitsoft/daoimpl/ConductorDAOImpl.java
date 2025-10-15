package pe.com.transitsoft.daoimpl;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.com.transitsoft.config.DBManager;
import pe.com.transitsoft.dao.IConductorDAO;
import pe.com.transitsoft.modelo.Gravedad;

/**
 *
 * @author eric
 */
public class ConductorDAOImpl implements IConductorDAO {
    private Connection conexion;
    private CallableStatement statement;
    private ResultSet resultSet;
    public ConductorDAOImpl(){
        
    }
    public void Obtenerconexion() throws SQLException, IOException, ClassNotFoundException{
        conexion=DBManager.getInstance().getConnection();
    }
    @Override
    public int obtenerPuntos(int idConductor, Gravedad gravedad) {
        int puntos=0;
        try {
            Obtenerconexion();
            statement = conexion.prepareCall(generarSQL());
            statement.setInt(1, idConductor);
            statement.setString(2,obtenerGravedad(gravedad));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                puntos=resultSet.getInt(1);
            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConductorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return puntos;
    }
    
    public String obtenerGravedad(Gravedad gravedad){
        switch(gravedad){
            case GRAVE -> {
                return "Grave";
            }
            case LEVE -> {
                return "Leve";
            }
            case MUY_GRAVE -> {
                return "Muy_grave";
            }
        }
        return "";
    }
    
    public String generarSQL(){
        return "SELECT SUM(I.puntos)\n" +
"		FROM EX1_CONDUCTORES AS C\n" +
"		INNER JOIN EX1_REGISTRO_INFRACCIONES AS R ON R.CONDUCTOR_ID = C.CONDUCTOR_ID\n" +
"		INNER JOIN EX1_INFRACCIONES AS I ON I.INFRACCION_ID = R.INFRACCION_ID\n" +
"		WHERE C.CONDUCTOR_ID = ? AND I.GRAVEDAD = ?";
    }
}