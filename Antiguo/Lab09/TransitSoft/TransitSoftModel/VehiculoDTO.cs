using System;

namespace TransitSoft.Model
{
    public class VehiculoDTO
    {
        private int? id;
        private string placa;
        private string marca;
        private string modelo;
        private int? anho;
        private PropietarioDTO propietario;

        public int? Id
        {
            get { return id; }
            set { id = value; }
        }

        public string Placa
        {
            get { return placa; }
            set { placa = value; }
        }

        public string Marca
        {
            get { return marca; }
            set { marca = value; }
        }

        public string Modelo
        {
            get { return modelo; }
            set { modelo = value; }
        }

        public int? Anho
        {
            get { return anho; }
            set { anho = value; }
        }

        public PropietarioDTO Propietario
        {
            get { return propietario; }
            set { propietario = value; }
        }

        public VehiculoDTO()
        {
            this.id = null;
            this.placa = null;
            this.marca = null;
            this.modelo = null;
            this.anho = null;
            this.propietario = null;
        }

        public VehiculoDTO(int? id, string placa, string marca, string modelo, int? anho, PropietarioDTO propietario)
        {
            this.id = id;
            this.placa = placa;
            this.marca = marca;
            this.modelo = modelo;
            this.anho = anho;
            this.propietario = propietario;
        }

        public override string ToString()
        {
            string cadena = "ID_VEHICULO: ";
            cadena += this.id?.ToString() ?? "null";
            cadena += " PLACA: " + this.placa;
            cadena += " MARCA: " + this.marca;
            cadena += " MODELO: " + this.modelo;
            cadena += " AÑO: " + (this.anho?.ToString() ?? "null");

            if (this.propietario != null)
            {
                cadena += " ID_PROPIETARIO: " + (this.propietario.Id?.ToString() ?? "null");
                cadena += " DNI: " + this.propietario.Dni;
                cadena += " NOMBRES: " + this.propietario.Nombres;
                cadena += " APELLIDOS: " + this.propietario.Apellidos;
                cadena += " DIRECCIÓN: " + this.propietario.Direccion;
            }

            return cadena;
        }
    }

}
