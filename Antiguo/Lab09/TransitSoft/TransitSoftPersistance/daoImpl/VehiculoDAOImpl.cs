using System;
using System.ComponentModel;
using System.Data;
using System.Data.Common;
using TransitSoft.Model;
using TransitSoft.Persistance.dao;
using TransitSoft.Persistance.daoImpl.Util;

namespace TransitSoft.Persistance.daoImpl
{
    public class VehiculoDAOImpl : DAOImplBase, VehiculoDAO
    {
        private VehiculoDTO vehiculo;

        public VehiculoDAOImpl() : base("VEHICULO")
        {
            this.retornarLlavePrimaria = true;
            this.vehiculo = null;
        }

        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Add(new Columna("ID", true, true));
            this.listaColumnas.Add(new Columna("PLACA", false, false));
            this.listaColumnas.Add(new Columna("MARCA", false, false));
            this.listaColumnas.Add(new Columna("MODELO", false, false));
            this.listaColumnas.Add(new Columna("ANHO", false, false));
        }

        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            PropietarioDTO propietario = new PropietarioDTO();
            propietario.Id = lector.GetInt32(lector.GetOrdinal("ID_PROPIETARIO"));
            propietario.Dni = lector.GetString(lector.GetOrdinal("DNI"));
            propietario.Nombres = lector.GetString(lector.GetOrdinal("NOMBRES"));
            propietario.Apellidos = lector.GetString(lector.GetOrdinal("APELLIDOS"));
            propietario.Direccion = lector.GetString(lector.GetOrdinal("DIRECCION"));

            this.vehiculo = new VehiculoDTO();
            this.vehiculo.Id = lector.GetInt32(lector.GetOrdinal("ID_VEHICULO"));
            this.vehiculo.Placa = lector.GetString(lector.GetOrdinal("PLACA"));
            this.vehiculo.Marca = lector.GetString(lector.GetOrdinal("MARCA"));
            this.vehiculo.Modelo = lector.GetString(lector.GetOrdinal("MODELO"));
            this.vehiculo.Anho = lector.GetInt32(lector.GetOrdinal("ANHO"));
            this.vehiculo.Propietario = propietario;
        }

        protected override void LimpiarObjetoDelResultSet()
        {
            this.vehiculo = null;
        }

        protected override void AgregarObjetoALaLista(BindingList<Object> lista, DbDataReader lector)
        {
            this.InstanciarObjetoDelResultSet(lector);
            lista.Add(this.vehiculo);
        }

        public BindingList<VehiculoDTO> ListarPorPropietario(string descripcionPropietario)
        {
            string sql = this.GenerarSQLParaListarPorPropietario();
            BindingList<Object> lista;
            lista = base.ListarTodos(sql, this.IncluirValorDeParametrosParaListarPorPropietario, descripcionPropietario);
            BindingList<VehiculoDTO> retorno = new BindingList<VehiculoDTO>();
            foreach (VehiculoDTO objeto in lista)
            {
                retorno.Add(objeto);
            }
            return retorno;
        }

        private string GenerarSQLParaListarPorPropietario()
        {
            string sql = "SELECT V.ID AS ID_VEHICULO, ";
            sql += "V.PLACA, ";
            sql += "V.MARCA, ";
            sql += "V.MODELO, ";
            sql += "V.ANHO, ";
            sql += "P.ID AS ID_PROPIETARIO, ";
            sql += "P.DNI, ";
            sql += "P.NOMBRES, ";
            sql += "P.APELLIDOS, ";
            sql += "P.DIRECCION ";
            sql += "FROM vehiculo V ";
            sql += "JOIN vehiculo_propietario VP ON VP.ID_VEHICULO = V.ID ";
            sql += "JOIN propietario P ON P.ID = VP.ID_PROPIETARIO ";
            sql += "WHERE (P.DNI LIKE @p1 OR P.NOMBRES LIKE @p2 OR P.APELLIDOS LIKE @p3)";
            return sql;
        }

        private void IncluirValorDeParametrosParaListarPorPropietario(object objetoParametros)
        {
            string cadena = (string)objetoParametros;
            cadena = cadena.Trim();
            cadena = "%" + cadena + "%";
            AgregarParametro("@p1", cadena, this.comandoMySQL);
            AgregarParametro("@p2", cadena, this.comandoMySQL);
            AgregarParametro("@p3", cadena, this.comandoMySQL);

            AgregarParametro("@p1", cadena, this.comandoMSSQL);
            AgregarParametro("@p2", cadena, this.comandoMSSQL);
            AgregarParametro("@p3", cadena, this.comandoMSSQL);
        }
    }
}