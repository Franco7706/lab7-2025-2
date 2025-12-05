using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Soft.Model
{
    public class ObjetoDTO
    {
        private int? objeto_id;
        private String nombre;

        public ObjetoDTO()
        {
            this.Objeto_id = null;
            this.Nombre = null;
        }

        public ObjetoDTO(int objeto_id,String nombre)
        {
            this.Objeto_id=objeto_id;
            this.Nombre = nombre;
        }

        public int? Objeto_id { get => objeto_id; set => objeto_id = value; }
        public String Nombre { get => nombre; set => nombre = value; }
    }
}
