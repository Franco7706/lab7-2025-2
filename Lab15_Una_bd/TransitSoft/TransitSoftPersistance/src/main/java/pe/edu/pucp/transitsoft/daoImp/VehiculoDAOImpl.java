package pe.edu.pucp.transitsoft.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.VehiculoDAO;
import pe.edu.pucp.transitsoft.daoImp.util.Columna;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;

public class VehiculoDAOImpl extends DAOImplBase implements VehiculoDAO {

    private VehiculoDTO vehiculoDTO;

    public VehiculoDAOImpl() {
        super("vehiculo");
        this.vehiculoDTO = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID", true, true));
        this.listaColumnas.add(new Columna("PLACA", false, false));
        this.listaColumnas.add(new Columna("MARCA", false, false));
        this.listaColumnas.add(new Columna("MODELO", false, false));
        this.listaColumnas.add(new Columna("ANHO", false, false));
    }
    
    @Override
    protected String generarSQLParaListarTodos(){
        return """
               SELECT v.ID, v.PLACA, v.MARCA, v.MODELO, v.ANHO, p.ID, p.DNI, p.NOMBRES, p.APELLIDOS, p.DIRECCION
               FROM vehiculo v JOIN vehiculo_propietario vp ON v.id = vp.id_vehiculo
               JOIN propietario p ON vp.id_propietario = p.id;
               """;
    }
    
    @Override
    protected String generarSQLParaObtenerPorId(){
        return """
               SELECT v.ID, v.PLACA, v.MARCA, v.MODELO, v.ANHO, p.ID, p.DNI, p.NOMBRES, p.APELLIDOS, p.DIRECCION
               FROM vehiculo v JOIN vehiculo_propietario vp ON v.id = vp.id_vehiculo
               JOIN propietario p ON vp.id_propietario = p.id WHERE v.ID = ?;
               """;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(vehiculoDTO);
    }
    
    @Override
    public ArrayList<VehiculoDTO> listarTodos() {
        return (ArrayList<VehiculoDTO>) super.listarTodos(generarSQLParaListarTodos(),null,null);
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, vehiculoDTO.getId());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(this.resultSet.getInt(1));
        vehiculoDTO.setPlaca(this.resultSet.getString(2));
        vehiculoDTO.setMarca(this.resultSet.getString(3));
        vehiculoDTO.setModelo(this.resultSet.getString(4));
        vehiculoDTO.setAnho(this.resultSet.getInt(5));
        PropietarioDTO propietario = new PropietarioDTO();
        propietario.setId(this.resultSet.getInt(6));
        propietario.setDni(this.resultSet.getString(7));
        propietario.setNombres(this.resultSet.getString(8));
        propietario.setApellidos(this.resultSet.getString(9));
        propietario.setDireccion(this.resultSet.getString(10));
        vehiculoDTO.setPropietario(propietario);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        vehiculoDTO = new VehiculoDTO();
    }
    @Override
    public VehiculoDTO obtenerPorId(int vehiculoId) {
        vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(vehiculoId);
        super.obtenerPorId();
        return vehiculoDTO;
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1,vehiculoDTO.getPlaca());
        this.statement.setString(2,vehiculoDTO.getMarca());
        this.statement.setString(3,vehiculoDTO.getModelo());
        this.statement.setInt(4,vehiculoDTO.getAnho());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1,vehiculoDTO.getPlaca());
        this.statement.setString(2,vehiculoDTO.getMarca());
        this.statement.setString(3,vehiculoDTO.getModelo());
        this.statement.setInt(4,vehiculoDTO.getAnho());
        this.statement.setInt(5,vehiculoDTO.getId());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,vehiculoDTO.getId());
    }
    
    @Override
    public int eliminar(int vehiculoId) {
        this.vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(vehiculoId);
        return super.eliminar();
    }

    @Override
    public int modificar(VehiculoDTO vehiculo) {
        this.vehiculoDTO = vehiculo;
        return super.modificar();
    }

    @Override
    public int insertar(VehiculoDTO vehiculo) {
        this.vehiculoDTO = vehiculo;
        return super.insertar();
    }
    
    @Override
    protected void insertarEnCascada(Integer resultado) throws SQLException {
        vehiculoDTO.setId(resultado);
        String sql = this.generarSQLParaInsertarEnTablaIntermedia();
        this.colocarSQLEnStatement(sql);
        this.incluirParametrosParaTablaIntermedia();
        this.ejecutarDMLEnBD();
    }

    private String generarSQLParaInsertarEnTablaIntermedia() {
        return """
               INSERT INTO vehiculo_propietario (id_vehiculo, id_propietario) VALUES (?,?);
               """;
    }

    private void incluirParametrosParaTablaIntermedia() throws SQLException {
        this.statement.setInt(1,this.vehiculoDTO.getId());
        this.statement.setInt(2,this.vehiculoDTO.getPropietario().getId());
    }
    
    
}
