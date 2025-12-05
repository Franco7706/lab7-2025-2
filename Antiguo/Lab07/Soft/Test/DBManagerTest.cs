using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Soft.Db;
using System.Data.Common;

namespace Soft.Test
{
    [TestClass]
    public class DBManagerTest
    {

        [TestMethod]
        public void TestGetInstance()
        {
            Console.WriteLine("GetInstance");
            DBManager dBManager1 = DBManager.Instance;
            DBManager dBManager2 = DBManager.Instance;
            Assert.IsNotNull(dBManager1);
            Assert.IsNotNull(dBManager2);
            Assert.AreEqual(dBManager1, dBManager2);
        }

        [TestMethod]
        public void TestGetConnection()
        {
            Console.WriteLine("GetConnection");
            DbConnection conexion = DBManager.Instance.Connection;
            Assert.IsNotNull(conexion);
            conexion.Open();
        }
    }
}
