package pe.edu.pucp.transitsoft.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.PropietarioDAO;
import pe.edu.pucp.transitsoft.daoImp.util.Columna;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;

public class PropietarioDAOImpl extends DAOImplBase implements PropietarioDAO {

    private PropietarioDTO propietarioDTO;

    public PropietarioDAOImpl() {
        super("propietario");
        this.propietarioDTO = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID", true, true));
        this.listaColumnas.add(new Columna("DNI", false, false));
        this.listaColumnas.add(new Columna("NOMBRES", false, false));
        this.listaColumnas.add(new Columna("APELLIDOS", false, false));
        this.listaColumnas.add(new Columna("DIRECCION", false, false));
    }
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        instanciarObjetoDelResultSet();
        lista.add(propietarioDTO);
    }
    
    @Override
    public ArrayList<PropietarioDTO> listarTodos() {
        return (ArrayList<PropietarioDTO>) super.listarTodos(super.generarSQLParaListarTodos(),null,null);
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, propietarioDTO.getId());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        propietarioDTO = new PropietarioDTO();
        propietarioDTO.setId(this.resultSet.getInt(1));
        propietarioDTO.setDni(this.resultSet.getString(2));
        propietarioDTO.setNombres(this.resultSet.getString(3));
        propietarioDTO.setApellidos(this.resultSet.getString(4));
        propietarioDTO.setDireccion(this.resultSet.getString(5));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        propietarioDTO = new PropietarioDTO();
    }
    @Override
    public PropietarioDTO obtenerPorId(int propietarioId) {
        propietarioDTO = new PropietarioDTO();
        propietarioDTO.setId(propietarioId);
        super.obtenerPorId();
        return propietarioDTO;
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1,propietarioDTO.getDni());
        this.statement.setString(2,propietarioDTO.getNombres());
        this.statement.setString(3,propietarioDTO.getApellidos());
        this.statement.setString(4,propietarioDTO.getDireccion());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1,propietarioDTO.getDni());
        this.statement.setString(2,propietarioDTO.getNombres());
        this.statement.setString(3,propietarioDTO.getApellidos());
        this.statement.setString(4,propietarioDTO.getDireccion());
        this.statement.setInt(5,propietarioDTO.getId());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,propietarioDTO.getId());
    }
    @Override
    public int eliminar(int propietarioId) {
        this.propietarioDTO = new PropietarioDTO();
        propietarioDTO.setId(propietarioId);
        return super.eliminar();
    }

    @Override
    public int modificar(PropietarioDTO propietario) {
        this.propietarioDTO = propietario;
        return super.modificar();
    }

    @Override
    public int insertar(PropietarioDTO propietario) {
        this.propietarioDTO = propietario;
        return super.insertar();
    }

    
}
