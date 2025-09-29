using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftVidBusiness;

namespace SoftVidTest
{
    [TestClass]
    public class BusinessTest
    {
        [TestMethod]
        public void TestMethod1()
        {
            SoftVidBusiness.Business business = new SoftVidBusiness.Business();
            business.normalizarBD();
        }
    }
}
