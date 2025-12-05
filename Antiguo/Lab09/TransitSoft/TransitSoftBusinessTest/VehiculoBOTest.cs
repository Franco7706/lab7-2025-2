using System;
using System.ComponentModel;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TransitSoft.Business;
using TransitSoft.Model;

namespace Pe.Pucp.TransitSoft.Bo.Test
{
    [TestClass]
    public class VehiculoBOTest
    {
        private VehiculoBO vehiculoBO;

        public VehiculoBOTest()
        {
            this.vehiculoBO = new VehiculoBO();
        }

        [TestMethod]
        public void TestListarPorPropietario()
        {
            Console.WriteLine("ListarPorPropietario");
            string descripcionPropietario = "Mónica";
            VehiculoBO instance = new VehiculoBO();
            BindingList<VehiculoDTO> listaVehiculos = instance.ListarPorPropietario(descripcionPropietario);
            Assert.IsNotNull(listaVehiculos);

            foreach (VehiculoDTO vehiculoDTO in listaVehiculos)
            {
                Console.WriteLine(vehiculoDTO);
            }
        }
    }
}