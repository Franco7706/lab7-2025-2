using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoftBO.TransitSoftWS;

namespace TransitSoftBO
{
    public class TipoLicenciaBO
    {
        private TipoLicenciaWSClient client;
        public TipoLicenciaBO() { 
            client = new TipoLicenciaWSClient();
        }

        public BindingList<tipoLicencia> ListarTiposLicencia()
        {
            return new BindingList<tipoLicencia>(client.listarTiposLicencia());
        }
    }
}
