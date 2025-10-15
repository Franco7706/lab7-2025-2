package pe.com.transitsoft.transitsoftpersistance.daoimpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import pe.com.transitsoft.transitsoftdbmanager.util.MotorDeBaseDeDatos;
import pe.com.transitsoft.transitsoftmodel.Infraccion;
import pe.com.transitsoft.transitsoftpersistance.dao.InfraccionDAO;

public class InfraccionDAOImpl extends DAOImplBase implements InfraccionDAO {

    private Infraccion infraccion;

    public InfraccionDAOImpl() {
        super.motorBD=MotorDeBaseDeDatos.MSSQL;
    }

    public int insertarInfraccion(Infraccion infraccion) {
        this.infraccion = infraccion;
        return super.insertar();
    }

    @Override
    protected int recuperaParametrosDeSalidaParaInsercion() throws SQLException {
        return this.statement.getInt(19);
    }
    
    @Override
    protected String generarSQLParaInsercion() {
        return "{call insertarInfraccion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.infraccion.getPlaca());
        this.statement.setDouble(2, this.infraccion.getVelocidad());
        this.statement.setDouble(3, this.infraccion.getLimite());
        this.statement.setDouble(4, this.infraccion.getExceso());
        this.statement.setString(5, this.infraccion.getVehiculoMarca());
        this.statement.setString(6, this.infraccion.getVehiculoModelo());
        this.statement.setInt(7, this.infraccion.getVehiculoAnho());
        this.statement.setString(8, this.infraccion.getPropietarioDni());
        this.statement.setString(9, this.infraccion.getPropietarioNombres());
        this.statement.setString(10, this.infraccion.getPropietarioApellidos());
        this.statement.setString(11, this.infraccion.getPropietarioDireccion());
        this.statement.setString(12, this.infraccion.getCamaraModelo());
        this.statement.setString(13, this.infraccion.getCamaraCodigoSerie());
        this.statement.setInt(14, this.infraccion.getCamaraLatitud());
        this.statement.setInt(15, this.infraccion.getCamaraLongitud());
        this.statement.setDouble(16, this.infraccion.getMonto());
        this.statement.setTimestamp(17,new Timestamp(this.infraccion.getFechaCaptura().getTime()));
        this.statement.setTimestamp(18,new Timestamp(this.infraccion.getFechaCaptura().getTime()));
        this.statement.registerOutParameter(19, Types.INTEGER);
    }

}
