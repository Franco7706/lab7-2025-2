using System;

namespace TransitSoft.Persistance.daoImpl.Util
{
    public class Columna
    {
        private string nombre;
        private bool? esLlavePrimaria;
        private bool? esAutoGenerado;

        public string Nombre
        {
            get { return nombre; }
            set { nombre = value; }
        }

        public bool? EsLlavePrimaria
        {
            get { return esLlavePrimaria; }
            set { esLlavePrimaria = value; }
        }

        public bool? EsAutoGenerado
        {
            get { return esAutoGenerado; }
            set { esAutoGenerado = value; }
        }

        public Columna(string nombre, bool? esLlavePrimaria, bool? esAutoGenerado)
        {
            this.nombre = nombre;
            this.esLlavePrimaria = esLlavePrimaria;
            this.esAutoGenerado = esAutoGenerado;
        }
    }

}