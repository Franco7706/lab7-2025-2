package pe.edu.pucp.softinv.daoImp;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import pe.pucp.transitsoft.dao.InfraccionDAO; // Asumiendo existencia
import pe.pucp.transitsoft.daoImp.util.Columna;
import pe.pucp.transitsoft.model.InfraccionDTO;

/**
 *
 * @author alulab14
 */
public class InfraccionDAOImpl extends DAOImplBase implements InfraccionDAO {

    private InfraccionDTO infraccionDTO;

    public InfraccionDAOImpl() {
        super("infraccion");
        this.infraccionDTO = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id", true, true));
        
        // Datos de la captura
        this.listaColumnas.add(new Columna("placa", false, false));
        this.listaColumnas.add(new Columna("velocidad", false, false));
        this.listaColumnas.add(new Columna("limite", false, false));
        this.listaColumnas.add(new Columna("exceso", false, false));
        
        // Datos del Vehículo (Nombres de columna con prefijo según tu tabla)
        this.listaColumnas.add(new Columna("vehiculo_marca", false, false));
        this.listaColumnas.add(new Columna("vehiculo_modelo", false, false));
        this.listaColumnas.add(new Columna("vehiculo_anho", false, false));
        
        // Datos del Propietario
        this.listaColumnas.add(new Columna("propietario_dni", false, false));
        this.listaColumnas.add(new Columna("propietario_nombres", false, false));
        this.listaColumnas.add(new Columna("propietario_apellidos", false, false));
        this.listaColumnas.add(new Columna("propietario_direccion", false, false));
        
        // Datos de la Cámara 
        // Nota: Omito 'id_camara' porque el DTO no lo tiene y es nullable en la tabla
        this.listaColumnas.add(new Columna("camara_modelo", false, false));
        this.listaColumnas.add(new Columna("camara_codigo_serie", false, false));
        this.listaColumnas.add(new Columna("camara_latitud", false, false));
        this.listaColumnas.add(new Columna("camara_longitud", false, false));
        
        // Datos de la Infracción
        this.listaColumnas.add(new Columna("monto", false, false));
        this.listaColumnas.add(new Columna("fecha_captura", false, false));
        this.listaColumnas.add(new Columna("fecha_registro", false, false));
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.infraccionDTO = new InfraccionDTO();
        
       this.infraccionDTO.setId(this.resultSet.getInt("id"));
        
        this.infraccionDTO.setPlaca(this.resultSet.getString("placa"));
        this.infraccionDTO.setVelocidad(this.resultSet.getDouble("velocidad"));
        this.infraccionDTO.setLimite(this.resultSet.getDouble("limite"));
        this.infraccionDTO.setExceso(this.resultSet.getDouble("exceso"));
        
        this.infraccionDTO.setMarcaVehiculo(this.resultSet.getString("vehiculo_marca"));
        this.infraccionDTO.setModeloVehiculo(this.resultSet.getString("vehiculo_modelo"));
        this.infraccionDTO.setAnhoVehiculo(this.resultSet.getInt("vehiculo_anho"));
        
        this.infraccionDTO.setDniPropietario(this.resultSet.getString("propietario_dni"));
        this.infraccionDTO.setNombresPropietario(this.resultSet.getString("propietario_nombres"));
        this.infraccionDTO.setApellidosPropietario(this.resultSet.getString("propietario_apellidos"));
        this.infraccionDTO.setDireccionPropietario(this.resultSet.getString("propietario_direccion"));
        
        this.infraccionDTO.setModeloCamara(this.resultSet.getString("camara_modelo"));
        this.infraccionDTO.setCodigoSerieCamara(this.resultSet.getString("camara_codigo_serie"));
        this.infraccionDTO.setLatitud(this.resultSet.getLong("camara_latitud"));
        this.infraccionDTO.setLongitud(this.resultSet.getLong("camara_longitud"));
        
        this.infraccionDTO.setMonto(this.resultSet.getDouble("monto"));
        
        Timestamp fechaCapturaSql = this.resultSet.getTimestamp("fecha_captura");
        if(fechaCapturaSql != null) 
            this.infraccionDTO.setFechaCaptura(new java.util.Date(fechaCapturaSql.getTime()));
            
        Timestamp fechaRegistroSql = this.resultSet.getTimestamp("fecha_registro");
        if(fechaRegistroSql != null) 
            this.infraccionDTO.setFechaRegistro(new java.util.Date(fechaRegistroSql.getTime()));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        int i = 1;
        this.statement.setString(i++, infraccionDTO.getPlaca());
        this.statement.setDouble(i++, infraccionDTO.getVelocidad());
        this.statement.setDouble(i++, infraccionDTO.getLimite());
        this.statement.setDouble(i++, infraccionDTO.getExceso());
        
        this.statement.setString(i++, infraccionDTO.getMarcaVehiculo());
        this.statement.setString(i++, infraccionDTO.getModeloVehiculo());
        this.statement.setInt(i++, infraccionDTO.getAnhoVehiculo());
        
        this.statement.setString(i++, infraccionDTO.getDniPropietario());
        this.statement.setString(i++, infraccionDTO.getNombresPropietario());
        this.statement.setString(i++, infraccionDTO.getApellidosPropietario());
        this.statement.setString(i++, infraccionDTO.getDireccionPropietario());
        
        this.statement.setString(i++, infraccionDTO.getModeloCamara());
        this.statement.setString(i++, infraccionDTO.getCodigoSerieCamara());
        // Manejo de Long para lat/long
        this.statement.setLong(i++, infraccionDTO.getLatitud());
        this.statement.setLong(i++, infraccionDTO.getLongitud());
        
        this.statement.setDouble(i++, infraccionDTO.getMonto());
        
        this.statement.setTimestamp(i++, new Timestamp(infraccionDTO.getFechaCaptura().getTime()));
        this.statement.setTimestamp(i++, new Timestamp(infraccionDTO.getFechaRegistro().getTime()));
    }


    @Override
    protected void limpiarObjetoDelResultSet() {
        this.infraccionDTO = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.infraccionDTO);
    }
    
    @Override
    public int insertar(InfraccionDTO infraccionDTO) {
        this.infraccionDTO = infraccionDTO;
        return super.insertar();
    }

    @Override
    public ArrayList<InfraccionDTO> listarTodos() {
        return (ArrayList<InfraccionDTO>) super.listarTodos();
    }
}