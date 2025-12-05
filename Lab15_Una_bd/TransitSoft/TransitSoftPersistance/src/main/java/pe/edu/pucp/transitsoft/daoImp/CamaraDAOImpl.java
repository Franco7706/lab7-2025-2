package pe.edu.pucp.transitsoft.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.CamaraDAO;
import pe.edu.pucp.transitsoft.daoImp.util.Columna;
import pe.edu.pucp.transitsoft.model.CamaraDTO;

public class CamaraDAOImpl extends DAOImplBase implements CamaraDAO {

    private CamaraDTO camaraDTO;

    public CamaraDAOImpl() {
        super("camara");
        this.camaraDTO = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID", true, true));
        this.listaColumnas.add(new Columna("MODELO", false, false));
        this.listaColumnas.add(new Columna("CODIGO_SERIE", false, false));
        this.listaColumnas.add(new Columna("LATITUD", false, false));
        this.listaColumnas.add(new Columna("LONGITUD", false, false));
    }
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        instanciarObjetoDelResultSet();
        lista.add(camaraDTO);
    }
    
    @Override
    public ArrayList<CamaraDTO> listarTodos() {
        return (ArrayList<CamaraDTO>) super.listarTodos(super.generarSQLParaListarTodos(),null,null);
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, camaraDTO.getId());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        camaraDTO = new CamaraDTO();
        camaraDTO.setId(this.resultSet.getInt(1));
        camaraDTO.setModelo(this.resultSet.getString(2));
        camaraDTO.setCodigoSerie(this.resultSet.getString(3));
        camaraDTO.setLatitud(this.resultSet.getLong(4));
        camaraDTO.setLongitud(this.resultSet.getLong(5));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        camaraDTO = new CamaraDTO();
    }
    @Override
    public CamaraDTO obtenerPorId(int camaraId) {
        camaraDTO = new CamaraDTO();
        camaraDTO.setId(camaraId);
        super.obtenerPorId();
        return camaraDTO;
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1,camaraDTO.getModelo());
        this.statement.setString(2,camaraDTO.getCodigoSerie());
        this.statement.setLong(3,camaraDTO.getLatitud());
        this.statement.setLong(4,camaraDTO.getLongitud());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1,camaraDTO.getModelo());
        this.statement.setString(2,camaraDTO.getCodigoSerie());
        this.statement.setLong(3,camaraDTO.getLatitud());
        this.statement.setLong(4,camaraDTO.getLongitud());
        this.statement.setInt(5,camaraDTO.getId());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,camaraDTO.getId());
    }
    @Override
    public int eliminar(int camaraId) {
        this.camaraDTO = new CamaraDTO();
        camaraDTO.setId(camaraId);
        return super.eliminar();
    }

    @Override
    public int modificar(CamaraDTO camara) {
        this.camaraDTO = camara;
        return super.modificar();
    }

    @Override
    public int insertar(CamaraDTO camara) {
        this.camaraDTO = camara;
        return super.insertar();
    }

    
}
