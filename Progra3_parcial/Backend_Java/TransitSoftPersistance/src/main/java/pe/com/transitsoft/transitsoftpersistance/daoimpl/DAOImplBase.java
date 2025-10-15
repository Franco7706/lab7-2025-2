package pe.com.transitsoft.transitsoftpersistance.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.com.transitsoft.transitsoftdbmanager.DBManager;
import pe.com.transitsoft.transitsoftdbmanager.util.MotorDeBaseDeDatos;
import pe.com.transitsoft.transitsoftpersistance.daoimpl.util.Tipo_DML;

public class DAOImplBase {
    protected Connection conexion;
    protected CallableStatement statement;
    protected ResultSet resultSet;
    protected MotorDeBaseDeDatos motorBD;
    
    protected DAOImplBase(){
        conexion=null;
        statement=null;
        motorBD=MotorDeBaseDeDatos.MYSQL;
    }
    
    protected void abrirConexion() {
        switch(motorBD){
            case MYSQL:
                this.conexion = DBManager.getMySQLInstance().getConnection();
                break;
            case MSSQL:
                this.conexion = DBManager.getMSSQLInstance().getConnection();
                break;
        }
    }
    
    protected void rollbackTransaccion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.rollback();
        }
    }

    protected void commitTransaccion() throws SQLException {
        this.conexion.commit();
    }

    protected void iniciarTransaccion() throws SQLException {
        this.abrirConexion();
        this.conexion.setAutoCommit(false);

    }

    protected void cerrarConexion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.close();
        }
    }
    private ResultSet ejecutarQueryEnBD() throws SQLException {
        this.resultSet = this.statement.executeQuery();
        return resultSet;
    }
    
    private Integer ejecutarDMLenBD() throws SQLException {
        return this.statement.executeUpdate();
    }
    
    protected void colocarSQLEnStatement(String sql) throws SQLException {
        this.statement = this.conexion.prepareCall(sql);
    }
    
    protected void listarTodos(){
        try{
            abrirConexion();
            String sql = generarSQLParaListarTodos();
            colocarSQLEnStatement(sql);
            ejecutarQueryEnBD();
            extraerResultSetParaListarTodos();
        }
        catch(SQLException e){
            System.err.println("Error al hacer query: " + e);
        }
        finally{
            try{
                cerrarConexion();
            }
            catch(Exception ex2){
                System.err.println("Error al cerrar conexion: " + ex2);
            }
        }
    }
    
    protected int insertar(){
        return ejecutaDML(Tipo_DML.INSERTAR);
    }
    
    protected int modificar(){
        return ejecutaDML(Tipo_DML.MODIFICAR);
    }
    
    protected int eliminar(){
        return ejecutaDML(Tipo_DML.ELIMINAR);
    }
    
    protected int ejecutaDML(Tipo_DML tipo_operacion){
        try{
            abrirConexion();
            iniciarTransaccion();
            String sql = generarSQLParaDML(tipo_operacion);
            colocarSQLEnStatement(sql);
            incluirValorDeParametrosParaDML(tipo_operacion);
            int resultado = ejecutarDMLenBD();
            commitTransaccion();
            if(tipo_operacion==Tipo_DML.INSERTAR){
                return recuperaParametrosDeSalidaParaDML(tipo_operacion);
            }
            return resultado;
            
        }
        catch(SQLException e){
            System.err.println("Error al ejecutar dml: " + e);
            try{
                rollbackTransaccion();
            }
            catch(Exception ex1){
                System.err.println("Error al hacer rollback: " + ex1);
            }
        }
        finally{
            try{
                cerrarConexion();
            }
            catch(Exception ex2){
                System.err.println("Error al cerrar conexion: " + ex2);
            }
        }
        return 0;
    }

    protected String generarSQLParaListarTodos() {
        return null;
    }
    
    protected void extraerResultSetParaListarTodos() throws SQLException {
    }

    protected String generarSQLParaDML(Tipo_DML tipo_operacion) {
        switch(tipo_operacion){
            case INSERTAR:
                return generarSQLParaInsercion();
            case MODIFICAR:
                return generarSQLParaModificacion();
            case ELIMINAR:
                return generarSQLParaEliminacion();
            default:
                return null;
        }
    }

    protected void incluirValorDeParametrosParaDML(Tipo_DML tipo_operacion) throws SQLException {
        switch(tipo_operacion){
            case INSERTAR:
                incluirValorDeParametrosParaInsercion();
                break;
            case MODIFICAR:
                incluirValorDeParametrosParaModificacion();
                break;
            case ELIMINAR:
                incluirValorDeParametrosParaEliminacion();
        }
    }

    protected String generarSQLParaEliminacion() {
        return null;
    }

    protected String generarSQLParaModificacion() {
        return null;
    }

    protected String generarSQLParaInsercion() {
        return null;
    }

    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
    }

    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
    }

    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
    }
    
    public Integer retornarUltimoAutoGenerado() {
        Integer resultado = null;
        try {
            String sql = DBManager.retornarSQLParaUltimoAutoGenerado();
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                resultado = this.resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar retornarUltimoAutoGenerado - " + ex);
        }
        return resultado;
    }

    private int recuperaParametrosDeSalidaParaDML(Tipo_DML tipo_operacion) throws SQLException {
        switch (tipo_operacion) {
            case INSERTAR:
                return recuperaParametrosDeSalidaParaInsercion();
            case MODIFICAR:
                return recuperaParametrosDeSalidaParaModificacion();
            case ELIMINAR:
                return recuperaParametrosDeSalidaParaEliminacion();
        }
        return 0;
    }

    protected int recuperaParametrosDeSalidaParaInsercion() throws SQLException {
        return 0;
    }

    protected int recuperaParametrosDeSalidaParaModificacion() throws SQLException {
        return 0;
    }

    protected int recuperaParametrosDeSalidaParaEliminacion() throws SQLException {
        return 0;
    }
	
}
