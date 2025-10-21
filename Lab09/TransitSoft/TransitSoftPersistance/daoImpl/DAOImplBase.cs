using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Common;
using System.Data.SqlClient;
using TransitSoft.Db;
using TransitSoft.Persistance.daoImpl.Util;

namespace TransitSoft.Persistance.daoImpl
{
    public abstract class DAOImplBase
    {
        protected string nombreDeTabla;
        protected BindingList<Columna> listaColumnas;
        protected bool retornarLlavePrimaria;
        protected DbConnection conexionMySQL;
        protected DbConnection conexionMSSQL;
        protected DbCommand comandoMySQL;
        protected DbCommand comandoMSSQL;
        protected DbDataReader lectorMySQL;
        protected DbDataReader lectorMSSQL;
        protected DbTransaction transaccion;

        public string NombreTabla
        {
            get { return nombreDeTabla; }
            set { nombreDeTabla = value; }
        }

        public BindingList<Columna> ListaColumnas
        {
            get { return listaColumnas; }
            set { listaColumnas = value; }
        }

        public bool RetornarLlavePrimaria
        {
            get { return retornarLlavePrimaria; }
            set { retornarLlavePrimaria = value; }
        }

        protected DAOImplBase(string nombreTabla)
        {
            this.nombreDeTabla = nombreTabla;
            this.retornarLlavePrimaria = false;
            this.IncluirListaDeColumnas();
        }

        private void IncluirListaDeColumnas()
        {
            this.listaColumnas = new BindingList<Columna>();
            this.ConfigurarListaDeColumnas();
        }

        protected abstract void ConfigurarListaDeColumnas();

        protected void AbrirConexion()
        {
            this.conexionMySQL = DBManager.MySQLInstance.Connection;
            this.conexionMySQL.Open();
            this.conexionMSSQL = DBManager.MSSQLInstance.Connection;
            this.conexionMSSQL.Open();
        }

        protected void CerrarConexion()
        {
            if (this.conexionMySQL != null)
            {
                if (this.conexionMySQL.State == ConnectionState.Open)
                    this.conexionMySQL.Close();
            }
            if (this.conexionMSSQL != null)
            {
                if (this.conexionMSSQL.State == ConnectionState.Open)
                    this.conexionMSSQL.Close();
            }
        }

        
        protected void IniciarTransaccion()
        {
            this.AbrirConexion();
            this.transaccion = this.conexionMySQL.BeginTransaction();
        }

        protected void ComitarTransaccion()
        {
            this.transaccion.Commit();
            this.transaccion = null;
        }

        protected void RollbackTransaccion()
        {
            if (this.transaccion != null)
            {
                this.transaccion.Rollback();
            }
            this.transaccion = null;
        }

        protected void ColocarSQLEnComando(string sql)
        {
            if(this.comandoMySQL != null)
            {
                this.comandoMySQL.Connection = this.conexionMySQL;
                this.comandoMySQL.CommandText = sql;
                this.comandoMySQL.CommandType = System.Data.CommandType.Text;
            }
            if(this.comandoMSSQL != null)
            {
                this.comandoMSSQL.Connection = this.conexionMSSQL;
                this.comandoMSSQL.CommandText = sql;
                this.comandoMSSQL.CommandType = System.Data.CommandType.Text;
            }
        }

        protected int EjecutarDMLEnBD()
        {
            return this.comandoMySQL.ExecuteNonQuery();
        }

        protected void EjecutarSelectEnBD()
        {
            if(this.comandoMySQL != null)
                this.lectorMySQL = this.comandoMySQL.ExecuteReader();
            if(this.comandoMSSQL != null)
                this.lectorMSSQL = this.comandoMSSQL.ExecuteReader();
        }

        protected int Insertar()
        {
            return this.EjecutaDML(TipoOperacion.INSERTAR);
        }

        protected int Modificar()
        {
            return this.EjecutaDML(TipoOperacion.MODIFICAR);
        }

        protected int Eliminar()
        {
            return this.EjecutaDML(TipoOperacion.ELIMINAR);
        }

        private int EjecutaDML(TipoOperacion tipoOperacion)
        {
            int resultado = 0;
            try
            {
                this.comandoMySQL = DBManager.MySQLInstance.CrearComando();
                this.comandoMSSQL = null;
                this.IniciarTransaccion();
                string sql = "";
                switch (tipoOperacion)
                {
                    case TipoOperacion.INSERTAR:
                        sql = this.GenerarSQLParaInsercion();
                        break;
                    case TipoOperacion.MODIFICAR:
                        sql = this.GenerarSQLParaModificacion();
                        break;
                    case TipoOperacion.ELIMINAR:
                        sql = this.GenerarSQLParaEliminacion();
                        break;
                }
                this.ColocarSQLEnComando(sql);
                switch (tipoOperacion)
                {
                    case TipoOperacion.INSERTAR:
                        this.IncluirValorDeParametrosParaInsercion();
                        break;
                    case TipoOperacion.MODIFICAR:
                        this.IncluirValorDeParametrosParaModificacion();
                        break;
                    case TipoOperacion.ELIMINAR:
                        this.IncluirValorDeParametrosParaEliminacion();
                        break;
                }
                EjecutarDMLEnBD();
                if (this.retornarLlavePrimaria)
                {
                    int id = this.RetornarUltimoAutoGenerado();
                    resultado = id;
                }
                this.ComitarTransaccion();
                
            }
            catch (Exception ex)
            {
                this.RollbackTransaccion();
                
                throw new Exception(ex.Message);
            }
            finally
            {
                this.CerrarConexion();
            }
            return resultado;
        }

        protected string GenerarSQLParaInsercion()
        {
            //sentencia SQL a generar es similar a 
            //INSERT INTO INV_ALMACENES (NOMBRE, ALMACEN_CENTRAL) VALUES (?,?)
            string sql = "INSERT INTO ";
            sql += this.nombreDeTabla;
            sql += "(";
            string sql_columnas = "";
            string sql_parametros = "";
            foreach (Columna columna in this.listaColumnas)
            {
                if ((bool)!columna.EsAutoGenerado)
                {
                    if (!string.IsNullOrWhiteSpace(sql_columnas))
                    {
                        sql_columnas += ", ";
                        sql_parametros += ", ";
                    }
                    sql_columnas += columna.Nombre;
                    sql_parametros += "@" + columna.Nombre;
                }
            }
            sql += sql_columnas;
            sql += ") VALUES (";
            sql += sql_parametros;
            sql += ")";
            return sql;
        }

        protected string GenerarSQLParaModificacion()
        {
            //sentencia SQL a generar es similar a 
            //UPDATE INV_ALMACENES SET NOMBRE=?, ALMACEN_CENTRAL=? WHERE ALMACEN_ID=?
            string sql = "UPDATE ";
            sql += this.nombreDeTabla;
            sql += " SET ";
            string sql_columnas = "";
            string sql_predicado = "";
            foreach (Columna columna in this.listaColumnas)
            {
                if ((bool)columna.EsLlavePrimaria)
                {
                    if (!string.IsNullOrWhiteSpace(sql_predicado))
                    {
                        sql_predicado += ", ";
                    }
                    sql_predicado += columna.Nombre;
                    sql_predicado += "=@" + columna.Nombre;
                }
                else
                {
                    if (!string.IsNullOrWhiteSpace(sql_columnas))
                    {
                        sql_columnas += ", ";
                    }
                    sql_columnas += columna.Nombre;
                    sql_columnas += "=@" + columna.Nombre;
                }
            }
            sql += sql_columnas;
            sql += " WHERE ";
            sql += sql_predicado;
            return sql;
        }

        protected string GenerarSQLParaEliminacion()
        {
            //sentencia SQL a generar es similar a 
            //DELETE FROM INV_ALMACENES WHERE ALMACEN_ID=?
            string sql = "DELETE FROM ";
            sql += this.nombreDeTabla;
            sql += " WHERE ";
            string sql_predicado = "";
            foreach (Columna columna in this.listaColumnas)
            {
                if ((bool)columna.EsLlavePrimaria)
                {
                    if (!string.IsNullOrWhiteSpace(sql_predicado))
                    {
                        sql_predicado += ", ";
                    }
                    sql_predicado += columna.Nombre;
                    sql_predicado += "=@" + columna.Nombre;
                }
            }
            sql += sql_predicado;
            return sql;
        }

        protected string GenerarSQLParaObtenerPorId()
        {
            //sentencia SQL a generar es similar a 
            //SELECT ALMACEN_ID, NOMBRE, ALMACEN_CENTRAL FROM INV_ALMACENES WHERE ALMACEN_ID = ?
            string sql = "SELECT ";
            string sql_columnas = "";
            string sql_predicado = "";
            foreach (Columna columna in this.listaColumnas)
            {
                if ((bool)columna.EsLlavePrimaria)
                {
                    if (!string.IsNullOrWhiteSpace(sql_predicado))
                    {
                        sql_predicado += ", ";
                    }
                    sql_predicado += columna.Nombre;
                    sql_predicado += "=@" + columna.Nombre;
                }
                if (!string.IsNullOrWhiteSpace(sql_columnas))
                {
                    sql_columnas += ", ";
                }
                sql_columnas += columna.Nombre;
            }
            sql += sql_columnas;
            sql += " FROM ";
            sql += this.nombreDeTabla;
            sql += " WHERE ";
            sql += sql_predicado;
            return sql;
        }

        protected string GenerarSQLParaListarTodos()
        {
            //sentencia SQL a generar es similar a 
            //SELECT ALMACEN_ID, NOMBRE, ALMACEN_CENTRAL FROM INV_ALMACENES
            string sql = "SELECT ";
            string sql_columnas = "";
            foreach (Columna columna in this.listaColumnas)
            {
                if (!string.IsNullOrWhiteSpace(sql_columnas))
                {
                    sql_columnas += ", ";
                }
                sql_columnas += columna.Nombre;
            }
            sql += sql_columnas;
            sql += " FROM ";
            sql += this.nombreDeTabla;
            return sql;
        }

        protected virtual void IncluirValorDeParametrosParaInsercion()
        {
            throw new NotSupportedException("Not supported yet.");
        }

        protected virtual void IncluirValorDeParametrosParaModificacion()
        {
            throw new NotSupportedException("Not supported yet.");
        }

        protected virtual void IncluirValorDeParametrosParaEliminacion()
        {
            throw new NotSupportedException("Not supported yet.");
        }

        private int RetornarUltimoAutoGenerado()
        {
            int resultado = -1;
            String sql = DBManager.Instance.RetornarSQLParaUltimoAutoGenerado();
            this.comandoMySQL = DBManager.MySQLInstance.CrearComando();
            this.comandoMSSQL = null;
            this.ColocarSQLEnComando(sql);
            this.EjecutarSelectEnBD();
            if (this.lectorMySQL.Read())
            {
                resultado = this.lectorMySQL.GetInt32(0);
            }
            this.lectorMySQL.Close();
            return resultado;
        }

        public void ObtenerPorId()
        {
            try
            {
                this.AbrirConexion();
                this.comandoMySQL=DBManager.MySQLInstance.CrearComando();
                string sql = this.GenerarSQLParaObtenerPorId();
                this.ColocarSQLEnComando(sql);
                this.IncluirValorDeParametrosParaObtenerPorId();
                this.EjecutarSelectEnBD();
                if (this.lectorMySQL.Read())
                {
                    this.InstanciarObjetoDelResultSet(lectorMySQL);
                }
                else
                {
                    this.LimpiarObjetoDelResultSet();
                }
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            finally
            {
                this.CerrarConexion();
            }
        }

        protected virtual void IncluirValorDeParametrosParaObtenerPorId()
        {
            throw new NotSupportedException("El método no ha sido sobreescrito.");
        }

        protected virtual void InstanciarObjetoDelResultSet()
        {
            throw new NotSupportedException("Not supported yet.");
        }

        protected virtual void LimpiarObjetoDelResultSet()
        {
            throw new NotSupportedException("Not supported yet.");
        }
        protected virtual void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        public BindingList<object> ListarTodos()
        {
            string sql = null;
            Action<object> incluirValorDeParametros = null;
            object parametros = null;
            return this.ListarTodos(sql, incluirValorDeParametros, parametros);
        }

        public BindingList<object> ListarTodos(string sql, Action<object> incluirValorDeParametros, object parametros)
        {
            BindingList<object> lista = new BindingList<object>();
            try
            {
                this.AbrirConexion();
                if (sql == null)
                {
                    sql = this.GenerarSQLParaListarTodos();
                }
                this.comandoMySQL = DBManager.MySQLInstance.CrearComando();
                this.comandoMSSQL = DBManager.MSSQLInstance.CrearComando();
                this.ColocarSQLEnComando(sql);
                if (incluirValorDeParametros != null)
                {
                    incluirValorDeParametros(parametros);
                }
                this.EjecutarSelectEnBD();
                while (this.lectorMySQL.Read())
                {
                    AgregarObjetoALaLista(lista,this.lectorMySQL);
                }
                while (this.lectorMSSQL.Read())
                {
                    AgregarObjetoALaLista(lista,this.lectorMSSQL);
                }
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            finally
            {
                this.CerrarConexion();
            }
            return lista;
        }

        protected virtual void AgregarObjetoALaLista(BindingList<object> lista, DbDataReader lector)
        {
            throw new NotSupportedException("Not supported yet.");
        }


        protected void AgregarParametro(string nombre, object value, DbCommand comando)
        {
            DbParameter parametero = comando.CreateParameter();
            parametero.ParameterName = nombre;
            parametero.Value = value;
            comando.Parameters.Add(parametero);
        }
    }
}

