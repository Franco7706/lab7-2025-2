package pe.edu.pucp.transitsoft.daoImp;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.daoImp.util.Columna;
import pe.edu.pucp.transitsoft.model.CamaraDTO;
import pe.edu.pucp.transitsoft.model.CapturaDTO;
import pe.edu.pucp.transitsoft.model.EstadoCapturaDTO;

public class CapturaDAOImpl extends DAOImplBase implements CapturaDAO {

    private CapturaDTO capturaDTO;

    public CapturaDAOImpl() {
        super("captura");
        this.capturaDTO = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID", true, true));
        this.listaColumnas.add(new Columna("ID_CAMARA", false, false));
        this.listaColumnas.add(new Columna("PLACA", false, false));
        this.listaColumnas.add(new Columna("VELOCIDAD", false, false));
        this.listaColumnas.add(new Columna("FECHA_CAPTURA", false, false));
        this.listaColumnas.add(new Columna("ESTADO", false, false));
    }
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        instanciarObjetoDelResultSet();
        lista.add(capturaDTO);
    }
    
    @Override
    public ArrayList<CapturaDTO> listarTodos() {
        return (ArrayList<CapturaDTO>) super.listarTodos(super.generarSQLParaListarTodos(),null,null);
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, capturaDTO.getId());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        capturaDTO = new CapturaDTO();
        CamaraDTO camara = new CamaraDTO();
        capturaDTO.setCamara(camara);
        capturaDTO.setId(this.resultSet.getInt(1));
        camara.setId(this.resultSet.getInt(2));
        capturaDTO.setPlaca(this.resultSet.getString(3));
        capturaDTO.setVelocidad(this.resultSet.getDouble(4));
        capturaDTO.setFechaCaptura(this.resultSet.getTimestamp(5));
        capturaDTO.setEstado(this.resultSet.getString(6).equals("REGISTRADO")?EstadoCapturaDTO.REGISTRADO:EstadoCapturaDTO.PROCESADO);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        capturaDTO = new CapturaDTO();
    }
    @Override
    public CapturaDTO obtenerPorId(int capturaId) {
        capturaDTO = new CapturaDTO();
        capturaDTO.setId(capturaId);
        super.obtenerPorId();
        return capturaDTO;
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1,capturaDTO.getCamara().getId());
        this.statement.setString(2,capturaDTO.getPlaca());
        this.statement.setDouble(3,capturaDTO.getVelocidad());
        this.statement.setTimestamp(4,new Timestamp(capturaDTO.getFechaCaptura().getTime()));
        this.statement.setString(5,capturaDTO.getEstado().equals(EstadoCapturaDTO.REGISTRADO)?"REGISTRADO":"PROCESADO");
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setInt(1,capturaDTO.getCamara().getId());
        this.statement.setString(2,capturaDTO.getPlaca());
        this.statement.setDouble(3,capturaDTO.getVelocidad());
        this.statement.setTimestamp(4,new Timestamp(capturaDTO.getFechaCaptura().getTime()));
        this.statement.setString(5,capturaDTO.getEstado().equals(EstadoCapturaDTO.REGISTRADO)?"REGISTRADO":"PROCESADO");
        this.statement.setInt(6,capturaDTO.getId());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,capturaDTO.getId());
    }
    @Override
    public int eliminar(int capturaId) {
        this.capturaDTO = new CapturaDTO();
        capturaDTO.setId(capturaId);
        return super.eliminar();
    }

    @Override
    public int modificar(CapturaDTO captura) {
        this.capturaDTO = captura;
        return super.modificar();
    }

    @Override
    public int insertar(CapturaDTO captura) {
        this.capturaDTO = captura;
        return super.insertar();
    }

    
}
