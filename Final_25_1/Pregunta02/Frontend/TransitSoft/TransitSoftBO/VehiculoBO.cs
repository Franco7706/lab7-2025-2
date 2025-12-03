using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoftBO.TransitSoftWS;

namespace TransitSoftBO
{
    public class VehiculoBO
    {
        private VehiculoWSClient client;
        public VehiculoBO() { 
            client = new VehiculoWSClient();
        }
        public BindingList<vehiculo> listarVehiculosPorPlaca(string placa)
        {
            return new BindingList<vehiculo>(client.listarVehiculosPorPlaca(placa));
        }
    }
}
