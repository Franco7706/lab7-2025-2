using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVidModel
{
    public class Categoria
    {
        private char id_categoria;
        private String descripcion_categoria;
        public Categoria()
        {
            Id_categoria = ' ';
            Descripcion_categoria = null;
        }
        public Categoria(char id_categoria, string descripcion_categoria)
        {
            Id_categoria = id_categoria;
            Descripcion_categoria = descripcion_categoria;
        }
        public char Id_categoria { get => id_categoria; set => id_categoria = value; }
        public string Descripcion_categoria { get => descripcion_categoria; set => descripcion_categoria = value; }
    }

}
