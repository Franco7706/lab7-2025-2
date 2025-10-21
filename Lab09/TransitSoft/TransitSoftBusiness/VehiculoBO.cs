using System;
using System.ComponentModel;
using TransitSoft.Model;
using TransitSoft.Persistance.dao;
using TransitSoft.Persistance.daoImpl;

namespace TransitSoft.Business
{
    public class VehiculoBO
    {
        private VehiculoDAO vehiculoDAO;

        public VehiculoDAO VehiculoDAO
        {
            get { return vehiculoDAO; }
            set { vehiculoDAO = value; }
        }

        public VehiculoBO()
        {
            this.vehiculoDAO = new VehiculoDAOImpl();
        }

        public BindingList<VehiculoDTO> ListarPorPropietario(string descripcionPropietario)
        {
            return this.vehiculoDAO.ListarPorPropietario(descripcionPropietario);
        }
    }
}