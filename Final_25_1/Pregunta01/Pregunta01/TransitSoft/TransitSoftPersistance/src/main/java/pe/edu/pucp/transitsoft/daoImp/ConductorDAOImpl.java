package pe.edu.pucp.transitsoft.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.ConductorDAO;
import pe.edu.pucp.transitsoft.daoImp.util.Columna;
import pe.edu.pucp.transitsoft.model.ConductoresDTO;
import pe.edu.pucp.transitsoft.model.VehiculosDTO;

public class ConductorDAOImpl extends DAOImplBase implements ConductorDAO {
    
    private ConductoresDTO conductor;
    
    public ConductorDAOImpl() {
        super("EX2_CONDUCTORES");
        this.conductor = null;
        this.retornarLlavePrimaria = true;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("CONDUCTOR_ID", true, true));
        this.listaColumnas.add(new Columna("PATERNO", false, false));
        this.listaColumnas.add(new Columna("MATERNO", false, false));
        this.listaColumnas.add(new Columna("NOMBRES", false, false));
        this.listaColumnas.add(new Columna("NUM_LICENCIA", false, false));
        this.listaColumnas.add(new Columna("TIPO_LICENCIA_ID", false, false));
        this.listaColumnas.add(new Columna("PUNTOS_ACUMULADOS", false, false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, conductor.getApellidoPaterno());
        this.statement.setString(2, conductor.getApellidoMaterno());
        this.statement.setString(3, conductor.getNombres());
        this.statement.setString(4, conductor.getNumeroLicencia());
        this.statement.setInt(5, conductor.getIdTipoLicencia());
        this.statement.setInt(6, conductor.getPuntosAcumulados());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {        
        this.statement.setString(1, conductor.getApellidoPaterno());
        this.statement.setString(2, conductor.getApellidoMaterno());
        this.statement.setString(3, conductor.getNombres());
        this.statement.setString(4, conductor.getNumeroLicencia());
        this.statement.setInt(5, conductor.getIdTipoLicencia());
        this.statement.setInt(6, conductor.getPuntosAcumulados());
        this.statement.setInt(7, conductor.getIdConductor());
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, conductor.getIdConductor());
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, conductor.getIdConductor());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        conductor = new ConductoresDTO();
        conductor.setIdConductor(this.resultSet.getInt(1));
        conductor.setApellidoPaterno(this.resultSet.getString(2));
        conductor.setApellidoMaterno(this.resultSet.getString(3));
        conductor.setNombres(this.resultSet.getString(4));
        conductor.setNumeroLicencia(this.resultSet.getString(5));
        conductor.setIdTipoLicencia(this.resultSet.getInt(6));
        conductor.setPuntosAcumulados(this.resultSet.getInt(7));
        VehiculosDTO vehiculo = new VehiculosDTO();
        vehiculo.setIdVehiculo(this.resultSet.getInt(8));
        conductor.setVehiculo(vehiculo);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.conductor = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.conductor);
    }
    
    @Override
    public Integer insertar(ConductoresDTO conductor) {        
        this.conductor = conductor;
        return super.insertar();
    }
    
    @Override
    public ConductoresDTO obtenerPorId(Integer conductorId) {
        this.conductor = new ConductoresDTO();
        this.conductor.setIdConductor(conductorId);
        super.obtenerPorId();
        return this.conductor;
    }
    
    @Override
    protected String generarSQLParaListarTodos(){
        return """
               SELECT c.CONDUCTOR_ID,c.PATERNO,c.MATERNO,c.NOMBRES,c.NUM_LICENCIA,
               c.TIPO_LICENCIA_ID,c.PUNTOS_ACUMULADOS,
               vc.VEHICULO_ID FROM EX2_CONDUCTORES c LEFT JOIN
               EX2_VEHICULOS_POR_CONDUCTOR vc ON c.CONDUCTOR_ID = vc.CONDUCTOR_ID
               """;
    }
    
    @Override
    public ArrayList<ConductoresDTO> listarTodos() {
        return (ArrayList<ConductoresDTO>) super.listarTodos();
    }
    
    @Override
    public Integer modificar(ConductoresDTO conductor) {
        this.conductor = conductor;
        return super.modificar();
    }
    
    @Override
    public Integer eliminar(ConductoresDTO conductor) {
        this.conductor = conductor;
        return super.eliminar();
    }
    
}
