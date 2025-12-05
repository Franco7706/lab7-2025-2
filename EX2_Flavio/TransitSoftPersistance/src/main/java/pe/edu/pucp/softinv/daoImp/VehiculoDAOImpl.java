package pe.edu.pucp.softinv.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.pucp.transitsoft.dao.VehiculoDAO;
import pe.pucp.transitsoft.daoImp.util.Columna;
import pe.pucp.transitsoft.model.PropietarioDTO;
import pe.pucp.transitsoft.model.VehiculoDTO;
import pe.pucp.transitsoft.util.MotorDeBaseDeDatos;

public class VehiculoDAOImpl extends DAOImplBase implements VehiculoDAO {

    private VehiculoDTO vehiculoDTO;
    private int conPropietario;
            
    public VehiculoDAOImpl() {
        super("vehiculo");
        this.vehiculoDTO = null;
        this.retornarLlavePrimaria = true;
        this.conPropietario = 0;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id", true, true));
        this.listaColumnas.add(new Columna("placa", false, false));
        this.listaColumnas.add(new Columna("marca", false, false));
        this.listaColumnas.add(new Columna("modelo", false, false));
        this.listaColumnas.add(new Columna("anho", false, false));
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.vehiculoDTO = new VehiculoDTO();
        
        if (this.conPropietario == 1) {
            PropietarioDTO propietario = new PropietarioDTO();
            propietario.setId(this.resultSet.getInt("id_propietario"));
            propietario.setDni(this.resultSet.getString("dni"));
            propietario.setNombres(this.resultSet.getString("nombres"));
            propietario.setApellidos(this.resultSet.getString("apellidos"));
            propietario.setDireccion(this.resultSet.getString("direccion"));
            this.vehiculoDTO.setPropietario(propietario);
            
            this.vehiculoDTO.setId(this.resultSet.getInt("id_vehiculo"));
            this.vehiculoDTO.setPlaca(this.resultSet.getString("placa"));
            this.vehiculoDTO.setMarca(this.resultSet.getString("marca"));
            this.vehiculoDTO.setModelo(this.resultSet.getString("modelo"));
            this.vehiculoDTO.setAnho(this.resultSet.getInt("anho"));
            
            this.conPropietario = 0;
        } else {
            this.vehiculoDTO.setId(this.resultSet.getInt("id"));
            this.vehiculoDTO.setPlaca(this.resultSet.getString("placa"));
            this.vehiculoDTO.setMarca(this.resultSet.getString("marca"));
            this.vehiculoDTO.setModelo(this.resultSet.getString("modelo"));
            this.vehiculoDTO.setAnho(this.resultSet.getInt("anho"));
        }
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        int i = 1;
        this.statement.setString(i++, vehiculoDTO.getPlaca());
        this.statement.setString(i++, vehiculoDTO.getMarca());
        this.statement.setString(i++, vehiculoDTO.getModelo());
        this.statement.setInt(i++, vehiculoDTO.getAnho());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException  {
        int i = 1;
        this.statement.setString(i++, vehiculoDTO.getPlaca());
        this.statement.setString(i++, vehiculoDTO.getMarca());
        this.statement.setString(i++, vehiculoDTO.getModelo());
        this.statement.setInt(i++, vehiculoDTO.getAnho());
        this.statement.setInt(i++, vehiculoDTO.getId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException  {
        int i = 1;
        this.statement.setInt(i++, vehiculoDTO.getId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException  {
        int i = 1;
        this.statement.setInt(i++, vehiculoDTO.getId());
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.vehiculoDTO = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.vehiculoDTO);
    }

    @Override
    public ArrayList<VehiculoDTO> listarPorPropietario(String descripion_propietario) {
        String sql = this.generarSQLParaListarPorPropietario();
        this.conPropietario = 1;
        ArrayList<VehiculoDTO> listado = (ArrayList<VehiculoDTO>) super.listarTodos(sql, this::incluirValorDeParametrosParaListarPorPropietario, descripion_propietario);
        ArrayList<VehiculoDTO> listadoMSQL = (ArrayList<VehiculoDTO>) super.listarTodos(MotorDeBaseDeDatos.MSSQL, sql, this::incluirValorDeParametrosParaListarPorPropietario, descripion_propietario);
        if (listadoMSQL != null) {
            listado.addAll(listadoMSQL);
        }
        
        return listado;
    }

    private String generarSQLParaListarPorPropietario() {
        String sql = "SELECT v.id AS id_vehiculo, ";
        sql = sql.concat("v.placa, ");
        sql = sql.concat("v.marca, ");
        sql = sql.concat("v.modelo, ");
        sql = sql.concat("v.anho, ");
        sql = sql.concat("p.id AS id_propietario, ");
        sql = sql.concat("p.dni, ");
        sql = sql.concat("p.nombres, ");
        sql = sql.concat("p.apellidos, ");
        sql = sql.concat("p.direccion ");
        sql = sql.concat("FROM vehiculo v ");
        sql = sql.concat("JOIN vehiculo_propietario vp ON vp.id_vehiculo = v.id ");
        sql = sql.concat("JOIN propietario p ON p.id = vp.id_propietario ");
        sql = sql.concat("WHERE (p.dni LIKE ? OR p.nombres LIKE ? OR p.apellidos LIKE ?); ");
        return sql;
    }
    
    private void incluirValorDeParametrosParaListarPorPropietario(Object objetoParametros) {
        String cadena = (String) objetoParametros;
        if (cadena == null) cadena = "";
        cadena = cadena.trim();
        cadena = "%".concat(cadena).concat("%");
        try {
            this.statement.setString(1, cadena);
            this.statement.setString(2, cadena);
            this.statement.setString(3, cadena);
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int insertar(VehiculoDTO vehiculo) {
        this.vehiculoDTO  = vehiculo;
        return super.insertar();
    }

    @Override
    public int modificar(VehiculoDTO vehiculo) {
        this.vehiculoDTO  = vehiculo;
        return super.modificar();
    }

    @Override
    public int eliminar(VehiculoDTO vehiculo) {
        this.vehiculoDTO  = vehiculo;
        return super.eliminar();
    }

    @Override
    public VehiculoDTO obtenerPorId(Integer id) {
        this.vehiculoDTO = new VehiculoDTO();
        this.vehiculoDTO.setId(id);
        super.obtenerPorId();
        return this.vehiculoDTO;
    }

    @Override
    public ArrayList<VehiculoDTO> listarTodos() {
        return (ArrayList<VehiculoDTO>)super.listarTodos();
    }
}