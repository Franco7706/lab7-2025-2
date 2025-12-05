using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVidModel;

namespace SoftVidPersistance.DAO
{
    public interface CategoriaDAO
    {
        int insertar(Categoria categoria);
        IList<Categoria> listarCategorias();
    }
}
