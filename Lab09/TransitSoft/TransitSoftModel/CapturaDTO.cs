using System;

namespace TransitSoft.Model
{
    public class CapturaDTO
    {
        private int? id;
        private string placa;
        private double? velocidad;
        private DateTime? fechaCaptura;
        private CamaraDTO camara;
        private EstadoCapturaDTO? estado;
        private VehiculoDTO vehiculo;

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

        public double? Velocidad
        {
            get { return velocidad; }
            set { velocidad = value; }
        }

        public DateTime? FechaCaptura
        {
            get { return fechaCaptura; }
            set { fechaCaptura = value; }
        }

        public CamaraDTO Camara
        {
            get { return camara; }
            set { camara = value; }
        }

        public EstadoCapturaDTO? Estado
        {
            get { return estado; }
            set { estado = value; }
        }

        public VehiculoDTO Vehiculo
        {
            get { return vehiculo; }
            set { vehiculo = value; }
        }

        public CapturaDTO()
        {
            this.id = null;
            this.placa = null;
            this.velocidad = null;
            this.fechaCaptura = null;
            this.camara = null;
            this.estado = null;
            this.vehiculo = null;
        }

        public CapturaDTO(int? id, string placa, double? velocidad, DateTime? fechaCaptura,
                         CamaraDTO camara, EstadoCapturaDTO? estado, VehiculoDTO vehiculo)
        {
            this.id = id;
            this.placa = placa;
            this.velocidad = velocidad;
            this.fechaCaptura = fechaCaptura;
            this.camara = camara;
            this.estado = estado;
            this.vehiculo = vehiculo;
        }
    }
}
