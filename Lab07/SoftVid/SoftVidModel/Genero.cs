using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVidModel
{
    public class Genero
    {
        private int id_genero;
        private String descripcion_genero;

        public Genero()
        {
            Id_genero = 0;
            Descripcion_genero = null;
        }

        public Genero(int id_genero, String descripcion_genero)
        {
            this.Id_genero = id_genero;
            this.Descripcion_genero = descripcion_genero;
        }

        public int Id_genero { get => id_genero; set => id_genero = value; }
        public string Descripcion_genero { get => descripcion_genero; set => descripcion_genero = value; }
    }
}
