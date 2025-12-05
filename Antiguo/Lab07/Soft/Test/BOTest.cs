using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Soft.Model;
using Soft.Business;

namespace Soft.Test
{
    [TestClass]
    public class BOTest
    {
        private ObjetoBO objetoBO;
        [TestMethod]
        public void ObjetoBOTest()
        {
            Console.WriteLine("BOTest - ObjetoBOTest");
            objetoBO = new ObjetoBO();
            objetoBO.InsertarDatos();
            IList<ObjetoDTO> objetos = objetoBO.ListarObjetos();
            Assert.IsTrue(objetos.Count > 0);
            objetoBO.MezclarNombres();
            IList<ObjetoDTO> objetos2 = objetoBO.ListarObjetos();
            Assert.AreNotEqual(objetos, objetos2);
            objetoBO.EliminarDatos();
            IList<ObjetoDTO> objetos3 = objetoBO.ListarObjetos();
            Assert.IsTrue(objetos3.Count == 0);
        }
    }
}
