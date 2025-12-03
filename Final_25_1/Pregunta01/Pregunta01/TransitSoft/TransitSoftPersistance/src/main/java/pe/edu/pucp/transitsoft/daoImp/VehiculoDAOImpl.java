package pe.edu.pucp.transitsoft.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.VehiculoDAO;
import pe.edu.pucp.transitsoft.daoImp.util.Columna;
import pe.edu.pucp.transitsoft.model.VehiculosDTO;

public class VehiculoDAOImpl extends DAOImplBase implements VehiculoDAO {
    
    private VehiculosDTO vehiculo;
    
    public VehiculoDAOImpl() {
        super("EX2_VEHICULOS");
        this.vehiculo = null;
        this.retornarLlavePrimaria = true;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("VEHICULO_ID", true, true));
        this.listaColumnas.add(new Columna("PLACA", false, false));
        this.listaColumnas.add(new Columna("MARCA", false, false));
        this.listaColumnas.add(new Columna("MODELO", false, false));
        this.listaColumnas.add(new Columna("ANHO", false, false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, vehiculo.getPlaca());
        this.statement.setString(2, vehiculo.getMarca());
        this.statement.setString(3, vehiculo.getModelo());
        this.statement.setInt(4, vehiculo.getAnio());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {   
        this.statement.setString(1, vehiculo.getPlaca());
        this.statement.setString(2, vehiculo.getMarca());
        this.statement.setString(3, vehiculo.getModelo());
        this.statement.setInt(4, vehiculo.getAnio());     
        this.statement.setInt(5,vehiculo.getIdVehiculo());
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,vehiculo.getIdVehiculo());
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1,vehiculo.getIdVehiculo());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        vehiculo = new VehiculosDTO();
        this.vehiculo.setIdVehiculo(this.resultSet.getInt(1));
        this.vehiculo.setPlaca(this.resultSet.getString(2));
        this.vehiculo.setMarca(this.resultSet.getString(3));
        this.vehiculo.setModelo(this.resultSet.getString(4));
        this.vehiculo.setAnio(this.resultSet.getInt(5));
        this.vehiculo.setIdConductor(this.resultSet.getInt(6));
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.vehiculo = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.vehiculo);
    }
    
    @Override
    public Integer insertar(VehiculosDTO vehiculo) {        
        this.vehiculo = vehiculo;
        return super.insertar();
    }
    
    @Override
    public VehiculosDTO obtenerPorId(Integer vehiculoId) {
        this.vehiculo = new VehiculosDTO();
        this.vehiculo.setIdVehiculo(vehiculoId);
        super.obtenerPorId();
        return this.vehiculo;
    }
    
    private String generarSQLParaInsertarEnTablaIntermedia() {
        return """
               INSERT INTO EX2_VEHICULOS_POR_CONDUCTOR (VEHICULO_ID, CONDUCTOR_ID, FECHA_ADQUISICION) 
               VALUES (?, ?, ?)
                """;
    }

    private void incluirParametrosParaTablaIntermedia() throws SQLException {
        this.statement.setInt(1,vehiculo.getIdVehiculo());
        this.statement.setInt(2,vehiculo.getIdConductor());
        this.statement.setString(3,vehiculo.getFechaAdquisicion());
    }

    
    @Override
    protected void insertarEnCascada(Integer resultado) throws SQLException {
        vehiculo.setIdVehiculo(resultado);
        String sql = this.generarSQLParaInsertarEnTablaIntermedia();
        this.colocarSQLEnStatement(sql);
        this.incluirParametrosParaTablaIntermedia();
        this.ejecutarDMLEnBD();
    }
    
    @Override
    protected String generarSQLParaListarTodos(){
        return """
               SELECT v.VEHICULO_ID,v.PLACA,v.MARCA,v.MODELO,v.ANHO,
               vc.CONDUCTOR_ID FROM EX2_VEHICULOS v LEFT JOIN
               EX2_VEHICULOS_POR_CONDUCTOR vc ON v.VEHICULO_ID = vc.VEHICULO_ID
               """;
    }
    
    @Override
    public ArrayList<VehiculosDTO> listarTodos() {
        return (ArrayList<VehiculosDTO>) super.listarTodos();
    }
    
    @Override
    public Integer modificar(VehiculosDTO vehiculo) {
        this.vehiculo = vehiculo;
        return super.modificar();
    }
    
    @Override
    public Integer eliminar(VehiculosDTO vehiculo) {
        this.vehiculo = vehiculo;
        return super.eliminar();
    }
    
}
