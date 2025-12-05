using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVidModel;

namespace SoftVidPersistance.DAO
{
    public interface GeneroDAO
    {
        int insertar(Genero genero);
        IList<Genero> listarGeneros();
    }
}
