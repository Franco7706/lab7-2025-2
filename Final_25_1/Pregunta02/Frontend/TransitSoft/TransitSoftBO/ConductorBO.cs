using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.ServiceModel.Channels;
using System.Text;
using System.Threading.Tasks;
using TransitSoftBO.TransitSoftWS;

namespace TransitSoftBO
{
    public class ConductorBO
    {
        private ConductorWSClient client;
        public ConductorBO() { 
            client = new ConductorWSClient();
        }

        public int InsertarConductor(conductor con, BindingList<vehiculoConductor> vehiculos)
        {
            return client.insertarConductor(con, vehiculos.ToArray());
        }
    }
}
