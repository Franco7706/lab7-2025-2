package pe.com.transitsoft.transitsoftpersistance.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.com.transitsoft.transitsoftdbmanager.util.MotorDeBaseDeDatos;
import pe.com.transitsoft.transitsoftmodel.Camara;
import pe.com.transitsoft.transitsoftmodel.Captura;
import pe.com.transitsoft.transitsoftmodel.Propietario;
import pe.com.transitsoft.transitsoftmodel.Vehiculo;
import pe.com.transitsoft.transitsoftpersistance.dao.CapturaDAO;

public class CapturaDAOImpl extends DAOImplBase implements CapturaDAO{
    
    private Captura captura;
    private Camara camara;
    private Propietario propietario;
    private Vehiculo vehiculo;
    private List<Object> lista;
    
    
    public CapturaDAOImpl(){
        super.motorBD=MotorDeBaseDeDatos.MYSQL;
    }
    
    public List<Object> listarCapturas(){
        super.listarTodos();
        return lista;
    }
    
    public void ModificarEstadoCaptura(Captura captura){
        this.captura=captura;
        super.modificar();
    }

    @Override
    protected String generarSQLParaListarTodos() {
        return "{CALL ListarCapturas()}";
    }
    
    @Override
    protected void extraerResultSetParaListarTodos() throws SQLException {
        lista=new ArrayList<>();
        while(this.resultSet.next()){
            List<Object> datos = new ArrayList<>();
            captura=new Captura();
            camara=new Camara();
            propietario=new Propietario();
            vehiculo=new Vehiculo();
            captura.setCapturaId(this.resultSet.getInt(1));
            captura.setPlaca(this.resultSet.getString(2));
            captura.setVelocidad(this.resultSet.getDouble(3));
            captura.setFechaCaptura(this.resultSet.getDate(4));
            captura.setEstado(this.resultSet.getString(5));
            camara.setCamaraId(this.resultSet.getInt(6));
            camara.setModelo(this.resultSet.getString(7));
            camara.setCodigoSerie(this.resultSet.getString(8));
            camara.setLatitud(this.resultSet.getInt(9));
            camara.setLongitud(this.resultSet.getInt(10));
            propietario.setPropietarioId(this.resultSet.getInt(11));
            propietario.setDni(this.resultSet.getString(12));
            propietario.setNombres(this.resultSet.getString(13));
            propietario.setApellidos(this.resultSet.getString(14));
            propietario.setDireccion(this.resultSet.getString(15));
            vehiculo.setVehiculoId(this.resultSet.getInt(16));
            vehiculo.setPlaca(this.resultSet.getString(17));
            vehiculo.setMarca(this.resultSet.getString(18));
            vehiculo.setModelo(this.resultSet.getString(19));
            vehiculo.setAnho(this.resultSet.getInt(20));
            
            captura.setCamara(camara);  
            datos.add(captura);
            datos.add(camara);
            datos.add(propietario);
            datos.add(vehiculo);
            lista.add(datos);
        }
    }

    @Override
    protected String generarSQLParaModificacion() {
        return "{CALL modificarEstadoCaptura('PROCESADO',?)}";
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setInt(1,this.captura.getCapturaId());
    }
    
    
}
