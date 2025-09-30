using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Soft.Model;
using Soft.Persistance.DAO;
using Soft.Persistance.DAOImpl;

namespace Soft.Business
{
    public class ObjetoBO
    {
        private ObjetoDAO objetoDAO;
        public ObjetoBO() {
            objetoDAO = new ObjetoDAOImpl();
        }

        public void InsertarDatos()
        {
            BindingList<ObjetoDTO> listaObjetos = new BindingList<ObjetoDTO>();
            listaObjetos.Add(new ObjetoDTO(1,"Franco Gallardo"));
            listaObjetos.Add(new ObjetoDTO(2,"Diego Ayala"));
            listaObjetos.Add(new ObjetoDTO(3,"Axel Ramirez"));
            listaObjetos.Add(new ObjetoDTO(4,"Joseph Polo"));
            listaObjetos.Add(new ObjetoDTO(5,"Brayan Cordova"));
            listaObjetos.Add(new ObjetoDTO(6,"Ismael Yupanqui"));
            listaObjetos.Add(new ObjetoDTO(7,"Carlos Chia"));
            listaObjetos.Add(new ObjetoDTO(8,"Leonardo Yarleque"));
            foreach(ObjetoDTO obj in listaObjetos)
            {
                objetoDAO.Insertar(obj);
            }
        }

        public void MezclarNombres()
        {
            IList<ObjetoDTO> lista = ListarObjetos();
            for(int i=0; i<lista.Count-1; i+=2)
            {
                string temp = lista[i].Nombre;
                lista[i].Nombre = lista[i+1].Nombre;
                lista[i+1].Nombre = temp;
                objetoDAO.Modificar(lista[i]);
            }

            foreach (ObjetoDTO obj in lista)
            {
                ObjetoDTO temp = objetoDAO.ObtenerPorId((int)obj.Objeto_id);
                Console.WriteLine(temp.Nombre);
            }
        }

        public void EliminarDatos()
        {
            IList<ObjetoDTO> lista = ListarObjetos();
            foreach(ObjetoDTO obj in lista)
            {
                objetoDAO.Eliminar(obj);
            }
        }

        public IList<ObjetoDTO> ListarObjetos()
        {
            IList<ObjetoDTO> lista = objetoDAO.ListarTodos();
            foreach(ObjetoDTO obj in lista)
            {
                Console.WriteLine(obj.Nombre);
            }
            return lista;
        }
    }
}
