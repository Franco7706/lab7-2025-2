using System;

namespace TransitSoft.Model
{
    public class CamaraDTO
    {
        private int? id;
        private string modelo;
        private string codigoSerie;
        private long? latitud;
        private long? longitud;

        public int? Id
        {
            get { return id; }
            set { id = value; }
        }

        public string Modelo
        {
            get { return modelo; }
            set { modelo = value; }
        }

        public string CodigoSerie
        {
            get { return codigoSerie; }
            set { codigoSerie = value; }
        }

        public long? Latitud
        {
            get { return latitud; }
            set { latitud = value; }
        }

        public long? Longitud
        {
            get { return longitud; }
            set { longitud = value; }
        }

        public CamaraDTO()
        {
            this.id = null;
            this.modelo = null;
            this.codigoSerie = null;
            this.latitud = null;
            this.longitud = null;
        }

        public CamaraDTO(int? id, string modelo, string codigoSerie, long? latitud, long? longitud)
        {
            this.id = id;
            this.modelo = modelo;
            this.codigoSerie = codigoSerie;
            this.latitud = latitud;
            this.longitud = longitud;
        }
    }

}
