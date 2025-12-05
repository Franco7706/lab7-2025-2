package pe.edu.pucp.softinv.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.pucp.transitsoft.dao.CamaraDAO; // Asumo que esta interfaz existe o la crearás
import pe.pucp.transitsoft.daoImp.util.Columna;
import pe.pucp.transitsoft.model.CamaraDTO;

/**
 *
 * @author alulab14
 */
public class CamaraDAOImpl extends DAOImplBase implements CamaraDAO {

    private CamaraDTO camaraDTO;

    public CamaraDAOImpl() {
        super("camara");
        this.camaraDTO = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        // El primer parámetro es el nombre EXACTO de la columna en la base de datos MySQL
        this.listaColumnas.add(new Columna("id", true, true));
        this.listaColumnas.add(new Columna("modelo", false, false));
        this.listaColumnas.add(new Columna("codigo_serie", false, false));
        this.listaColumnas.add(new Columna("latitud", false, false));
        this.listaColumnas.add(new Columna("longitud", false, false));
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.camaraDTO = new CamaraDTO();
        this.camaraDTO.setId(this.resultSet.getInt("id"));
        this.camaraDTO.setModelo(this.resultSet.getString("modelo"));
        this.camaraDTO.setCodigoSerie(this.resultSet.getString("codigo_serie"));
        // Usamos getLong porque en el DTO son Long, aunque en BD sean int
        this.camaraDTO.setLatitud(this.resultSet.getLong("latitud"));
        this.camaraDTO.setLongitud(this.resultSet.getLong("longitud"));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        int i = 1;
        // El orden debe coincidir con configurarListaDeColumnas (saltando el ID auto-inc)
        this.statement.setString(i++, camaraDTO.getModelo());
        this.statement.setString(i++, camaraDTO.getCodigoSerie());
        this.statement.setLong(i++, camaraDTO.getLatitud());
        this.statement.setLong(i++, camaraDTO.getLongitud());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        int i = 1;
        this.statement.setString(i++, camaraDTO.getModelo());
        this.statement.setString(i++, camaraDTO.getCodigoSerie());
        this.statement.setLong(i++, camaraDTO.getLatitud());
        this.statement.setLong(i++, camaraDTO.getLongitud());
        // El ID va al final para el WHERE
        this.statement.setInt(i++, camaraDTO.getId());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        int i = 1;
        this.statement.setInt(i++, camaraDTO.getId());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        int i = 1;
        this.statement.setInt(i++, camaraDTO.getId());
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.camaraDTO = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.camaraDTO);
    }

    // Métodos públicos de la interfaz CamaraDAO

    @Override
    public int insertar(CamaraDTO camaraDTO) {
        this.camaraDTO = camaraDTO;
        return super.insertar();
    }

    @Override
    public int modificar(CamaraDTO camaraDTO) {
        this.camaraDTO = camaraDTO;
        return super.modificar();
    }

    @Override
    public int eliminar(CamaraDTO camaraDTO) {
        this.camaraDTO = camaraDTO;
        return super.eliminar();
    }

    @Override
    public CamaraDTO obtenerPorId(Integer id) {
        this.camaraDTO = new CamaraDTO();
        this.camaraDTO.setId(id);
        super.obtenerPorId();
        return this.camaraDTO;
    }

    @Override
    public ArrayList<CamaraDTO> listarTodos() {
        return (ArrayList<CamaraDTO>) super.listarTodos();
    }
}