using SoftVid.Db;
using SoftVidModel;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.Common;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVidPersistance.DAOImpl
{
    public abstract class DAOImplBase
    {
        protected string nombreDeTabla;
        protected DbConnection conexion;
        protected DbTransaction transaccion;
        protected DbCommand comando;
        protected DbDataReader lector;
        protected bool mostrarSentenciaSQL;
        protected bool retornarLlavePrimaria;
        protected bool usarTransaccion;

        public DAOImplBase(String nombreDeTabla)
        {
            this.nombreDeTabla = nombreDeTabla;
            this.retornarLlavePrimaria = false;
            this.mostrarSentenciaSQL = true;
            this.usarTransaccion = true;
        }

        protected void AbrirConexion()
        {
            this.conexion = DBManager.Instance.Connection;
            this.conexion.Open();
        }

        protected void CerrarConexion()
        {
            if (this.conexion != null)
            {
                this.conexion.Close();
            }
        }

        protected void IniciarTransaccion()
        {
            this.AbrirConexion();
            this.transaccion = this.conexion.BeginTransaction();
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

        protected void ColocarSQLenComando(string sql)
        {
            this.comando.Connection = this.conexion;
            this.comando.CommandText = sql;
            this.comando.CommandType = System.Data.CommandType.Text;
        }

        protected void EjecutarConsultaEnBD(bool esProcedimiento = false)
        {
             this.lector = this.comando.ExecuteReader();
        }

        protected int Insertar(string sql)
        {
            return this.Ejecuta_DML(sql);
        }

        private int Ejecuta_DML(string sql)
        {
            int resultado = 0;
            try
            {
                if (this.usarTransaccion)
                {
                    this.IniciarTransaccion();
                }
                else
                {
                    this.AbrirConexion();
                }
                this.comando = DBManager.Instance.CrearComando();
                
                this.ColocarSQLenComando(sql);
                
                this.IncluirValorDeParametrosParaInsercion();
                
                this.comando.ExecuteNonQuery();
                if (this.retornarLlavePrimaria)
                {
                    int id = this.RetornarUltimoAutoGenerado();
                    resultado = id;
                }
                if (this.usarTransaccion)
                {
                    this.ComitarTransaccion();
                }
            }
            catch (Exception ex)
            {
                if (this.usarTransaccion)
                {
                    this.RollbackTransaccion();
                }
                throw new Exception(ex.Message);
            }
            finally
            {
                this.CerrarConexion();
            }
            return resultado;
        }

        protected virtual void IncluirValorDeParametrosParaInsercion()
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }
        private int RetornarUltimoAutoGenerado()
        {
            int resultado = -1;
            String sql = DBManager.Instance.RetornarSQLParaUltimoAutoGenerado();
            this.ColocarSQLenComando(sql);
            this.EjecutarConsultaEnBD();
            if (this.lector.Read())
            {
                resultado = this.lector.GetInt32(0);
            }
            this.lector.Close();
            return resultado;
        }



        protected IList<Object> ListarTodos(string sql)
        {
            IList<Object> lista = new BindingList<Object>();
            this.comando = DBManager.Instance.CrearComando();
            try
            {
                this.AbrirConexion();
                // Imprime el nombre de la base de datos conectada
                Console.WriteLine($"Conectado a la base de datos: {this.conexion.Database}");
                this.ColocarSQLenComando(sql);
                this.EjecutarConsultaEnBD();
                while (this.lector.Read())
                {
                    this.AgregarObjetoALaLista(lista, this.lector);
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

        protected virtual void AgregarObjetoALaLista(IList<Object> lista, DbDataReader lector)
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        protected void AgregarParametro(string nombre, object value)
        {
            DbParameter parametero = this.comando.CreateParameter();
            parametero.ParameterName = nombre;
            parametero.Value = value;
            this.comando.Parameters.Add(parametero);
        }
    }
}
