package pe.edu.pucp.transitsoft.daoImp;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.InfraccionDAO;
import pe.edu.pucp.transitsoft.daoImp.util.Columna;
import pe.edu.pucp.transitsoft.model.InfraccionDTO;

public class InfraccionDAOImpl extends DAOImplBase implements InfraccionDAO {

    private InfraccionDTO infraccionDTO;

    public InfraccionDAOImpl() {
        super("infraccion");
        this.infraccionDTO = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID", true, true));
        this.listaColumnas.add(new Columna("PLACA", false, false));
        this.listaColumnas.add(new Columna("VELOCIDAD", false, false));
        this.listaColumnas.add(new Columna("LIMITE", false, false));
        this.listaColumnas.add(new Columna("EXCESO", false, false));
        this.listaColumnas.add(new Columna("VEHICULO_MARCA", false, false));
        this.listaColumnas.add(new Columna("VEHICULO_MODELO", false, false));
        this.listaColumnas.add(new Columna("VEHICULO_ANHO", false, false));
        this.listaColumnas.add(new Columna("PROPIETARIO_DNI", false, false));
        this.listaColumnas.add(new Columna("PROPIETARIO_NOMBRES", false, false));
        this.listaColumnas.add(new Columna("PROPIETARIO_APELLIDOS", false, false));
        this.listaColumnas.add(new Columna("PROPIETARIO_DIRECCION", false, false));
        this.listaColumnas.add(new Columna("ID_CAMARA", false, false));
        this.listaColumnas.add(new Columna("CAMARA_MODELO", false, false));
        this.listaColumnas.add(new Columna("CAMARA_CODIGO_SERIE", false, false));
        this.listaColumnas.add(new Columna("CAMARA_LATITUD", false, false));
        this.listaColumnas.add(new Columna("CAMARA_LONGITUD", false, false));
        this.listaColumnas.add(new Columna("MONTO", false, false));
        this.listaColumnas.add(new Columna("FECHA_CAPTURA", false, false));
        this.listaColumnas.add(new Columna("FECHA_REGISTRO", false, false));
    }
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        instanciarObjetoDelResultSet();
        lista.add(infraccionDTO);
    }
    
    @Override
    public ArrayList<InfraccionDTO> listarTodos() {
        return (ArrayList<InfraccionDTO>) super.listarTodos(super.generarSQLParaListarTodos(),null,null);
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, infraccionDTO.getId());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        infraccionDTO = new InfraccionDTO();
        infraccionDTO.setId(this.resultSet.getInt(1));
        infraccionDTO.setPlaca(this.resultSet.getString(2));
        infraccionDTO.setVelocidad(this.resultSet.getDouble(3));
        infraccionDTO.setLimite(this.resultSet.getDouble(4));
        infraccionDTO.setExceso(this.resultSet.getDouble(5));
        infraccionDTO.setMarcaVehiculo(this.resultSet.getString(6));
        infraccionDTO.setModeloVehiculo(this.resultSet.getString(7));
        infraccionDTO.setAnhoVehiculo(this.resultSet.getInt(8));
        infraccionDTO.setDniPropietario(this.resultSet.getString(9));
        infraccionDTO.setNombresPropietario(this.resultSet.getString(10));
        infraccionDTO.setApellidosPropietario(this.resultSet.getString(11));
        infraccionDTO.setDireccionPropietario(this.resultSet.getString(12));
        infraccionDTO.setIdCamara(this.resultSet.getInt(13));
        infraccionDTO.setModeloCamara(this.resultSet.getString(14));
        infraccionDTO.setCodigoSerieCamara(this.resultSet.getString(15));
        infraccionDTO.setLatitud(this.resultSet.getLong(16));
        infraccionDTO.setLongitud(this.resultSet.getLong(17));
        infraccionDTO.setMonto(this.resultSet.getDouble(18));
        infraccionDTO.setFechaCaptura(this.resultSet.getTimestamp(19));
        infraccionDTO.setFechaRegistro(this.resultSet.getTimestamp(20));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        infraccionDTO = new InfraccionDTO();
    }
    @Override
    public InfraccionDTO obtenerPorId(int infraccionId) {
        infraccionDTO = new InfraccionDTO();
        infraccionDTO.setId(infraccionId);
        super.obtenerPorId();
        return infraccionDTO;
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1,infraccionDTO.getPlaca());
        this.statement.setDouble(2,infraccionDTO.getVelocidad());
        this.statement.setDouble(3,infraccionDTO.getLimite());
        this.statement.setDouble(4,infraccionDTO.getExceso());
        this.statement.setString(5,infraccionDTO.getMarcaVehiculo());
        this.statement.setString(6,infraccionDTO.getModeloVehiculo());
        this.statement.setInt(7, infraccionDTO.getAnhoVehiculo());
        this.statement.setString(8,infraccionDTO.getDniPropietario());
        this.statement.setString(9,infraccionDTO.getNombresPropietario());
        this.statement.setString(10,infraccionDTO.getApellidosPropietario());
        this.statement.setString(11,infraccionDTO.getDireccionPropietario());
        this.statement.setInt(12, infraccionDTO.getIdCamara());
        this.statement.setString(13,infraccionDTO.getModeloCamara());
        this.statement.setString(14,infraccionDTO.getCodigoSerieCamara());
        this.statement.setLong(15, infraccionDTO.getLatitud());
        this.statement.setLong(16, infraccionDTO.getLongitud());
        this.statement.setDouble(17,infraccionDTO.getMonto());
        this.statement.setTimestamp(18,new Timestamp(infraccionDTO.getFechaCaptura().getTime()));
        this.statement.setTimestamp(19,new Timestamp(infraccionDTO.getFechaRegistro().getTime()));
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1,infraccionDTO.getPlaca());
        this.statement.setDouble(2,infraccionDTO.getVelocidad());
        this.statement.setDouble(3,infraccionDTO.getLimite());
        this.statement.setDouble(4,infraccionDTO.getExceso());
        this.statement.setString(5,infraccionDTO.getMarcaVehiculo());
        this.statement.setString(6,infraccionDTO.getModeloVehiculo());
        this.statement.setInt(7, infraccionDTO.getAnhoVehiculo());
        this.statement.setString(8,infraccionDTO.getDniPropietario());
        this.statement.setString(9,infraccionDTO.getNombresPropietario());
        this.statement.setString(10,infraccionDTO.getApellidosPropietario());
        this.statement.setString(11,infraccionDTO.getDireccionPropietario());
        this.statement.setInt(12, infraccionDTO.getIdCamara());
        this.statement.setString(13,infraccionDTO.getModeloCamara());
        this.statement.setString(14,infraccionDTO.getCodigoSerieCamara());
        this.statement.setLong(15, infraccionDTO.getLatitud());
        this.statement.setLong(16, infraccionDTO.getLongitud());
        this.statement.setDouble(17,infraccionDTO.getMonto());
        this.statement.setTimestamp(18,new Timestamp(infraccionDTO.getFechaCaptura().getTime()));
        this.statement.setTimestamp(19,new Timestamp(infraccionDTO.getFechaRegistro().getTime()));
        this.statement.setInt(20,infraccionDTO.getId());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,infraccionDTO.getId());
    }
    @Override
    public int eliminar(int infraccionId) {
        this.infraccionDTO = new InfraccionDTO();
        infraccionDTO.setId(infraccionId);
        return super.eliminar();
    }

    @Override
    public int modificar(InfraccionDTO infraccion) {
        this.infraccionDTO = infraccion;
        return super.modificar();
    }

    @Override
    public int insertar(InfraccionDTO infraccion) {
        this.infraccionDTO = infraccion;
        return super.insertar();
    }

    
}
