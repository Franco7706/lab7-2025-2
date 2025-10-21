using System;

namespace TransitSoft.Model
{
    public class PropietarioDTO
    {
        private int? id;
        private string dni;
        private string nombres;
        private string apellidos;
        private string direccion;

        public int? Id
        {
            get { return id; }
            set { id = value; }
        }

        public string Dni
        {
            get { return dni; }
            set { dni = value; }
        }

        public string Nombres
        {
            get { return nombres; }
            set { nombres = value; }
        }

        public string Apellidos
        {
            get { return apellidos; }
            set { apellidos = value; }
        }

        public string Direccion
        {
            get { return direccion; }
            set { direccion = value; }
        }

        public PropietarioDTO()
        {
            this.id = null;
            this.dni = null;
            this.nombres = null;
            this.apellidos = null;
            this.direccion = null;
        }

        public PropietarioDTO(int? id, string dni, string nombres, string apellidos, string direccion)
        {
            this.id = id;
            this.dni = dni;
            this.nombres = nombres;
            this.apellidos = apellidos;
            this.direccion = direccion;
        }
    }
}
