using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.Common;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoft.Db.Util;

namespace TransitSoft.Db
{
    public abstract class DBManager
    {
        protected DbConnection conexion;
        protected string baseDeDatos;
        protected string nombreDeHost;
        protected string puerto;
        protected string usuario;
        protected string contraseña;
        protected static DBManager dbManager;

        protected DBManager()
        {
            //constructor protegido para evitar que se creen instancias.
            //Solo se podrá crear una instancia y esta debe hacerse usando el 
            //método getInstance()
        }

        public static DBManager Instance
        {
            get
            {
                if (dbManager == null)
                {
                    DBManager.CreateInstance();
                }
                return dbManager;
            }
        }

        public static DBManager MySQLInstance
        {
            get
            {
                if (dbManager == null || dbManager is DBManagerMSSQL)
                {
                    DBManagerMySQL.CreateConcreteInstance();
                    dbManager.LeerArchivoDeConfiguracion();
                }
                return dbManager;
            }
        }

        public static DBManager MSSQLInstance
        {
            get
            {
                if (dbManager == null || dbManager is DBManagerMySQL)
                {
                    DBManagerMSSQL.CreateConcreteInstance();
                    dbManager.LeerArchivoDeConfiguracion();
                }
                return dbManager;
            }
        }

        private static void CreateInstance()
        {
            if (DBManager.dbManager == null)
            {
                if (DBManager.ObtenerMotorDeBaseDeDatos() == MotorDeBaseDeDatos.MYSQL)
                    DBManagerMySQL.CreateConcreteInstance();
                else
                    DBManagerMSSQL.CreateConcreteInstance();
                DBManager.dbManager.LeerArchivoDeConfiguracion();
            }
        }        

        private static MotorDeBaseDeDatos ObtenerMotorDeBaseDeDatos()
        {
            string tipoDeBaseDeDatos = ConfigurationManager.AppSettings["tipoDeBaseDeDatos"];            
            if (tipoDeBaseDeDatos.Equals(MotorDeBaseDeDatos.MYSQL.ToString()))
                return MotorDeBaseDeDatos.MYSQL;
            else
                return MotorDeBaseDeDatos.MSSQL;
        }

        protected abstract void LeerArchivoDeConfiguracion();

        public DbConnection Connection
        {
            get
            {                
                string cadenaDeConexion = this.ObtenerCadenaDeConexion();
                this.RealizarConexionABaseDeDatos(cadenaDeConexion);
                return this.conexion;
            }
        }

        protected abstract void RealizarConexionABaseDeDatos(string cadenaDeConexion);

        protected abstract string ObtenerCadenaDeConexion();

        public abstract DbCommand CrearComando();

        public abstract string RetornarSQLParaUltimoAutoGenerado();

        public static string Cifrar(string texto)
        {
            return Convert.ToBase64String(Encoding.Unicode.GetBytes(texto));
        }

        public static string Descifrar(string textoEncriptado)
        {
            return Encoding.Unicode.GetString(Convert.FromBase64String(textoEncriptado));
        }

    }
}
