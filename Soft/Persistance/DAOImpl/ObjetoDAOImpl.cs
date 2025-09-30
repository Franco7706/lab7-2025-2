using Soft.Model;
using Soft.Persistance.DAO;
using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Soft.Persistance.DAOImpl
{
    public class ObjetoDAOImpl : DAOImplBase, ObjetoDAO
    {
        private ObjetoDTO objeto;
        public ObjetoDAOImpl() : base("OBJETO"){
            objeto = null;
        }

        // Eliminable
        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Add(new Util.Columna("OBJETO_ID", true, false));
            this.listaColumnas.Add(new Util.Columna("NOMBRE", false, false));
        }
        // Fin eliminable

        public int Insertar(ObjetoDTO objeto)
        {
            this.objeto = objeto;
            return base.Insertar();
        }

        public int Modificar(ObjetoDTO objeto)
        {
            this.objeto = objeto;
            return base.Modificar();
        }

        public int Eliminar(ObjetoDTO objeto)
        {
            this.objeto = objeto;
            return base.Eliminar();
        }

        public ObjetoDTO ObtenerPorId(int objetoId)
        {
            this.objeto = new ObjetoDTO();
            this.objeto.Objeto_id = objetoId;
            base.ObtenerPorId();
            return objeto;
        }

        IList<ObjetoDTO> ObjetoDAO.ListarTodos()
        {
            return base.ListarTodos().Cast<ObjetoDTO>().ToList(); // IMPORTANTE
        }

        // Polimorfismos

        protected override void IncluirValorDeParametrosParaInsercion()
        {
            AgregarParametro("OBJETO_ID",this.objeto.Objeto_id);
            AgregarParametro("NOMBRE",this.objeto.Nombre);
        }
        protected override void IncluirValorDeParametrosParaModificacion()
        {
            AgregarParametro("OBJETO_ID", this.objeto.Objeto_id);
            AgregarParametro("NOMBRE", this.objeto.Nombre);
        }
        protected override void IncluirValorDeParametrosParaEliminacion()
        {
            AgregarParametro("OBJETO_ID", this.objeto.Objeto_id);
        }

        protected override void IncluirValorDeParametrosParaObtenerPorId()
        {
            AgregarParametro("OBJETO_ID", this.objeto.Objeto_id);
        }

        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            this.objeto = new ObjetoDTO();
            this.objeto.Objeto_id = lector.GetInt32(0);
            this.objeto.Nombre = lector.GetString(1);
        }
        protected override void LimpiarObjetoDelResultSet()
        {
            this.objeto = null;
        }

        protected override void AgregarObjetoALaLista(IList<object> lista, DbDataReader lector)
        {
            this.InstanciarObjetoDelResultSet(lector);
            lista.Add(this.objeto);
        }

        // EN CASO HAYA PROCEDURES
        //protected override string GenerarSQLParaInsercion()
        //{
        //    return "CALL SP_INSERTAR_OBJETO(?)";
        //}

        //protected override string GenerarSQLParaModificacion()
        //{
        //    return "CALL SP_MODIFICAR_OBJETO(?,?)";
        //}

        //protected override string GenerarSQLParaEliminacion()
        //{
        //    return "CALL SP_ELIMINAR_OBJETO(?)";
        //}

        //protected override string GenerarSQLParaObtenerPorId()
        //{
        //    return "CALL SP_OBTENER_OBJETO_POR_ID(?)";
        //}

        //protected override string GenerarSQLParaListarTodos()
        //{
        //    return "CALL SP_LISTAR_TODOS_LOS_OBJETOS()";
        //}
    }
}
