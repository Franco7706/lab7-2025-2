package pe.edu.pucp.softinv.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.pucp.transitsoft.dao.PropietarioDAO;
import pe.pucp.transitsoft.dao.VehiculoDAO;
import pe.pucp.transitsoft.daoImp.util.Columna;
import pe.pucp.transitsoft.model.PropietarioDTO;
import pe.pucp.transitsoft.model.VehiculoDTO;
import pe.pucp.transitsoft.util.MotorDeBaseDeDatos;

/**
 *
 * @author alulab14
 */
public class PropietarioDAOImpl extends DAOImplBase implements PropietarioDAO {

    private PropietarioDTO propietarioDTO;
            
    public PropietarioDAOImpl() {
        super("propietario");
        this.propietarioDTO = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id", true, true));
        this.listaColumnas.add(new Columna("dni", false, false));
        this.listaColumnas.add(new Columna("nombres", false, false));
        this.listaColumnas.add(new Columna("apellidos", false, false));
        this.listaColumnas.add(new Columna("direccion", false, false));
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {        
        this.propietarioDTO = new PropietarioDTO();
        this.propietarioDTO.setId(this.resultSet.getInt("id"));
        this.propietarioDTO.setDni(this.resultSet.getString("dni"));
        this.propietarioDTO.setNombres(this.resultSet.getString("nombres"));
        this.propietarioDTO.setApellidos(this.resultSet.getString("apellidos"));
        this.propietarioDTO.setDireccion(this.resultSet.getString("direccion"));
    }
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        int i=1;
        this.statement.setString(i++, propietarioDTO.getDni());
        this.statement.setString(i++, propietarioDTO.getNombres());
        this.statement.setString(i++, propietarioDTO.getApellidos());
        this.statement.setString(i++, propietarioDTO.getDireccion());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion()throws SQLException  {
        int i=1;
        this.statement.setString(i++, propietarioDTO.getDni());
        this.statement.setString(i++, propietarioDTO.getNombres());
        this.statement.setString(i++, propietarioDTO.getApellidos());
        this.statement.setString(i++, propietarioDTO.getDireccion());
        this.statement.setInt(i++, propietarioDTO.getId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion()throws SQLException  {
        int i=1;
        this.statement.setInt(i++, propietarioDTO.getId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId()throws SQLException  {
        int i=1;
        this.statement.setInt(i++, propietarioDTO.getId());
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.propietarioDTO = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.propietarioDTO);
    }

    @Override
    public int insertar(PropietarioDTO propietarioDTO) {
        this.propietarioDTO  = propietarioDTO;
        return super.insertar();
    }

    @Override
    public int modificar(PropietarioDTO propietarioDTO) {
        this.propietarioDTO  = propietarioDTO;
        return super.modificar();
    }

    @Override
    public int eliminar(PropietarioDTO propietarioDTO) {
        this.propietarioDTO  = propietarioDTO;
        return super.eliminar();
    }

    @Override
    public PropietarioDTO obtenerPorId(Integer id) {
        this.propietarioDTO = new PropietarioDTO();
        this.propietarioDTO.setId(id);
        super.obtenerPorId();
        return this.propietarioDTO;
    }

    @Override
    public ArrayList<PropietarioDTO> listarTodos() {
        //Añadir la elección de bd
        return (ArrayList<PropietarioDTO>)super.listarTodos();
    }
}
