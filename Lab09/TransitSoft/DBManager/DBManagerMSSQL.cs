using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Data.SqlClient;

namespace TransitSoft.Db
{
    public class DBManagerMSSQL: DBManager
    {
        private DBManagerMSSQL()
        {
            //construtor ocultado que no se puedan crear instancias desde fuera de la clase
        }

        public static void CreateConcreteInstance()
        {
            if (DBManager.dbManager == null)
                DBManager.dbManager = new DBManagerMSSQL();
        }

        protected override void RealizarConexionABaseDeDatos(string cadenaDeConexion)
        {
            this.conexion = new SqlConnection(cadenaDeConexion);
        }

        protected override string ObtenerCadenaDeConexion()
        {
            SqlConnectionStringBuilder csBuilder = new SqlConnectionStringBuilder()
            {
                DataSource = $"{this.nombreDeHost},{this.puerto}",
                InitialCatalog = this.baseDeDatos,
                UserID = this.usuario,
                Password = DBManager.Descifrar(this.contraseña),
                TrustServerCertificate = true
            };

            return csBuilder.ConnectionString;            
        }
        protected override void LeerArchivoDeConfiguracion()
        {
            this.baseDeDatos = ConfigurationManager.AppSettings["baseDeDatosMSSQL"];
            this.nombreDeHost = ConfigurationManager.AppSettings["nombreDeHostMSSQL"];
            this.puerto = ConfigurationManager.AppSettings["puertoMSSQL"];
            this.usuario = ConfigurationManager.AppSettings["usuarioMSSQL"];
            this.contraseña = ConfigurationManager.AppSettings["contraseña"];
        }

        public override DbCommand CrearComando()
        {
            return new SqlCommand();
        }

        public override string RetornarSQLParaUltimoAutoGenerado()
        {
            string sql = "select @@IDENTITY as id";
            return sql;
        }
    }
}
