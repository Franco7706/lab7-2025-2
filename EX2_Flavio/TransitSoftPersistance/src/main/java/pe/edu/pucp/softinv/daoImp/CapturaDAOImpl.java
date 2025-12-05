package pe.edu.pucp.softinv.daoImp;

import java.sql.SQLException;
import java.sql.Timestamp; // Necesario para datetime
import java.util.ArrayList;
import java.util.List;
import pe.pucp.transitsoft.dao.CapturaDAO;
import pe.pucp.transitsoft.daoImp.util.Columna;
import pe.pucp.transitsoft.model.CamaraDTO;
import pe.pucp.transitsoft.model.CapturaDTO;
import pe.pucp.transitsoft.model.EstadoCapturaDTO; // Asumo que esto es un Enum
import pe.pucp.transitsoft.model.VehiculoDTO;

/**
 *
 * @author alulab14
 */
public class CapturaDAOImpl extends DAOImplBase implements CapturaDAO {

    private CapturaDTO capturaDTO;

    public CapturaDAOImpl() {
        super("captura");
        this.capturaDTO = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id", true, true));
        this.listaColumnas.add(new Columna("id_camara", false, false)); // FK hacia Camara
        this.listaColumnas.add(new Columna("placa", false, false));
        this.listaColumnas.add(new Columna("velocidad", false, false));
        this.listaColumnas.add(new Columna("fecha_captura", false, false));
        this.listaColumnas.add(new Columna("estado", false, false));
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.capturaDTO = new CapturaDTO();
        this.capturaDTO.setId(this.resultSet.getInt("id"));

        // 1. Mapeo de CAMARA (FK)
        // Solo recuperamos el ID para no hacer consultas anidadas pesadas aquí.
        CamaraDTO camara = new CamaraDTO();
        camara.setId(this.resultSet.getInt("id_camara"));
        this.capturaDTO.setCamara(camara);

        // 2. Mapeo de PLACA y VEHICULO
        String placa = this.resultSet.getString("placa");
        this.capturaDTO.setPlaca(placa);
        
        // Opcional: Inicializar el objeto VehiculoDTO con la placa obtenida
        VehiculoDTO vehiculo = new VehiculoDTO();
        vehiculo.setPlaca(placa);
        this.capturaDTO.setVehiculo(vehiculo);

        this.capturaDTO.setVelocidad(this.resultSet.getDouble("velocidad"));

        // 3. Mapeo de FECHA (SQL Timestamp -> Java Util Date)
        Timestamp fechaSql = this.resultSet.getTimestamp("fecha_captura");
        if (fechaSql != null) {
            this.capturaDTO.setFechaCaptura(new java.util.Date(fechaSql.getTime()));
        }

        // 4. Mapeo de ESTADO (String -> Enum)
        // Asumiendo que EstadoCapturaDTO es un Enum. Si es una clase, ajusta según corresponda.
        String estadoStr = this.resultSet.getString("estado");
        if (estadoStr != null) {
            this.capturaDTO.setEstado(EstadoCapturaDTO.valueOf(estadoStr));
        }
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        int i = 1;
        // El orden debe coincidir con configurarListaDeColumnas
        
        // id_camara
        this.statement.setInt(i++, capturaDTO.getCamara().getId());
        
        // placa
        this.statement.setString(i++, capturaDTO.getPlaca());
        
        // velocidad
        this.statement.setDouble(i++, capturaDTO.getVelocidad());
        
        // fecha_captura (Java Util Date -> SQL Timestamp)
        this.statement.setTimestamp(i++, new Timestamp(capturaDTO.getFechaCaptura().getTime()));
        
        // estado (Enum -> String)
        this.statement.setString(i++, capturaDTO.getEstado().toString());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        int i = 1;
        this.statement.setInt(i++, capturaDTO.getCamara().getId());
        this.statement.setString(i++, capturaDTO.getPlaca());
        this.statement.setDouble(i++, capturaDTO.getVelocidad());
        this.statement.setTimestamp(i++, new Timestamp(capturaDTO.getFechaCaptura().getTime()));
        this.statement.setString(i++, capturaDTO.getEstado().toString());
        
        // ID para el WHERE
        this.statement.setInt(i++, capturaDTO.getId());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        int i = 1;
        this.statement.setInt(i++, capturaDTO.getId());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        int i = 1;
        this.statement.setInt(i++, capturaDTO.getId());
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.capturaDTO = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.capturaDTO);
    }

    // Métodos de la interfaz

    @Override
    public int insertar(CapturaDTO capturaDTO) {
        this.capturaDTO = capturaDTO;
        return super.insertar();
    }

    @Override
    public int modificar(CapturaDTO capturaDTO) {
        this.capturaDTO = capturaDTO;
        return super.modificar();
    }

    @Override
    public int eliminar(CapturaDTO capturaDTO) {
        this.capturaDTO = capturaDTO;
        return super.eliminar();
    }

    @Override
    public CapturaDTO obtenerPorId(Integer id) {
        this.capturaDTO = new CapturaDTO();
        this.capturaDTO.setId(id);
        super.obtenerPorId();
        return this.capturaDTO;
    }

    @Override
    public ArrayList<CapturaDTO> listarTodos() {
        return (ArrayList<CapturaDTO>) super.listarTodos();
    }
}