using System;
using System.ComponentModel;
using TransitSoft.Model;

namespace TransitSoft.Persistance.dao
{
    public interface VehiculoDAO
    {
        BindingList<VehiculoDTO> ListarPorPropietario(String descripcion_propietario);
    }
}
