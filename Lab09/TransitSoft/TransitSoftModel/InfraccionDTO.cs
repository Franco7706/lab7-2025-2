using System;

namespace TransitSoft.Model
{
    public class InfraccionDTO
    {
        private string placa;
        private double? velocidad;
        private double? limite;
        private double? exceso;
        private string marcaVehiculo;
        private string modeloVehiculo;
        private int? anhoVehiculo;
        private string dniPropietario;
        private string nombresPropietario;
        private string apellidosPropietario;
        private string direccionPropietario;
        private string modeloCamara;
        private string codigoSerieCamara;
        private long? latitud;
        private long? longitud;
        private double? monto;
        private DateTime? fechaCaptura;
        private DateTime? fechaRegistro;

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

        public double? Limite
        {
            get { return limite; }
            set { limite = value; }
        }

        public double? Exceso
        {
            get { return exceso; }
            set { exceso = value; }
        }

        public string MarcaVehiculo
        {
            get { return marcaVehiculo; }
            set { marcaVehiculo = value; }
        }

        public string ModeloVehiculo
        {
            get { return modeloVehiculo; }
            set { modeloVehiculo = value; }
        }

        public int? AnhoVehiculo
        {
            get { return anhoVehiculo; }
            set { anhoVehiculo = value; }
        }

        public string DniPropietario
        {
            get { return dniPropietario; }
            set { dniPropietario = value; }
        }

        public string NombresPropietario
        {
            get { return nombresPropietario; }
            set { nombresPropietario = value; }
        }

        public string ApellidosPropietario
        {
            get { return apellidosPropietario; }
            set { apellidosPropietario = value; }
        }

        public string DireccionPropietario
        {
            get { return direccionPropietario; }
            set { direccionPropietario = value; }
        }

        public string ModeloCamara
        {
            get { return modeloCamara; }
            set { modeloCamara = value; }
        }

        public string CodigoSerieCamara
        {
            get { return codigoSerieCamara; }
            set { codigoSerieCamara = value; }
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

        public double? Monto
        {
            get { return monto; }
            set { monto = value; }
        }

        public DateTime? FechaCaptura
        {
            get { return fechaCaptura; }
            set { fechaCaptura = value; }
        }

        public DateTime? FechaRegistro
        {
            get { return fechaRegistro; }
            set { fechaRegistro = value; }
        }

        public InfraccionDTO()
        {
            this.placa = null;
            this.velocidad = null;
            this.limite = null;
            this.exceso = null;
            this.marcaVehiculo = null;
            this.modeloVehiculo = null;
            this.anhoVehiculo = null;
            this.dniPropietario = null;
            this.nombresPropietario = null;
            this.apellidosPropietario = null;
            this.direccionPropietario = null;
            this.modeloCamara = null;
            this.codigoSerieCamara = null;
            this.latitud = null;
            this.longitud = null;
            this.monto = null;
            this.fechaCaptura = null;
            this.fechaRegistro = null;
        }

        public InfraccionDTO(string placa, double? velocidad, double? limite, double? exceso,
                            string marcaVehiculo, string modeloVehiculo, int? anhoVehiculo,
                            string dniPropietario, string nombresPropietario, string apellidosPropietario,
                            string direccionPropietario, string modeloCamara, string codigoSerieCamara,
                            long? latitud, long? longitud, double? monto,
                            DateTime? fechaCaptura, DateTime? fechaRegistro)
        {
            this.placa = placa;
            this.velocidad = velocidad;
            this.limite = limite;
            this.exceso = exceso;
            this.marcaVehiculo = marcaVehiculo;
            this.modeloVehiculo = modeloVehiculo;
            this.anhoVehiculo = anhoVehiculo;
            this.dniPropietario = dniPropietario;
            this.nombresPropietario = nombresPropietario;
            this.apellidosPropietario = apellidosPropietario;
            this.direccionPropietario = direccionPropietario;
            this.modeloCamara = modeloCamara;
            this.codigoSerieCamara = codigoSerieCamara;
            this.latitud = latitud;
            this.longitud = longitud;
            this.monto = monto;
            this.fechaCaptura = fechaCaptura;
            this.fechaRegistro = fechaRegistro;
        }
    }
}
