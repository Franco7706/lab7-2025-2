using Soft.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Soft.Persistance.DAO
{
    public interface ObjetoDAO
    {
        int Insertar(ObjetoDTO objeto);
        int Modificar(ObjetoDTO objeto);
        int Eliminar(ObjetoDTO objeto);

        ObjetoDTO ObtenerPorId(int objetoId);

        IList<ObjetoDTO> ListarTodos();        
    }
}
