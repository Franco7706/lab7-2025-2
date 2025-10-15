using SoftVidModel;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVidPersistance.DAO;
using SoftVidPersistance.DAOImpl;

namespace SoftVidBusiness
{
    public class Business
    {
        private CategoriaDAO categoriaDAO;
        private GeneroDAO generoDAO;
        private VideojuegoDAO videojuegoDAO;

        public Business()
        {
            categoriaDAO = new CategoriaDAOImpl();
            generoDAO = new GeneroDAOImpl();
            videojuegoDAO = new VideoJuegoDAOImpl();
        }
        public void normalizarBD()
        {
            IList<Videojuego> videojuegos = videojuegoDAO.listarVideojuegos();
            IList<Categoria> categorias = categoriaDAO.listarCategorias();
            IList<Genero> generos = generoDAO.listarGeneros();
            BindingList<Categoria> categoriasAInsertar = new BindingList<Categoria>();
            BindingList<Genero> generosAInsertar = new BindingList<Genero>();

            foreach (var categoria in categorias)
            {
                bool enc = false;
                foreach (var categoriainsertada in categoriasAInsertar)
                {
                    if (categoria.Id_categoria == categoriainsertada.Id_categoria)
                    {
                        enc = true;
                    }
                }
                if (!enc)
                {
                    categoriasAInsertar.Add(categoria);
                }
            }
            foreach (var genero in generos)
            {
                bool enc = false;
                foreach (var generoinsertado in generosAInsertar)
                {
                    if (genero.Id_genero == generoinsertado.Id_genero)
                    {
                        enc = true;
                    }
                }
                if (!enc)
                {
                    generosAInsertar.Add(genero);
                }
            }

            foreach (var categoria in categoriasAInsertar)
            {
                categoriaDAO.insertar(categoria);
            }
            foreach (var genero in generosAInsertar)
            {
                generoDAO.insertar(genero);
            }
            foreach (var videojuego in videojuegos)
            {
                videojuegoDAO.insertar(videojuego);
            }
            // finalmente, se han insertado todas las categorias, generos y videojuegos sin duplicados
        }
    }
}
