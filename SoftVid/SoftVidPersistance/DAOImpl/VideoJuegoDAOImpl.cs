using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVidPersistance.DAO;
using SoftVidModel;
using System.Data.Common;

namespace SoftVidPersistance.DAOImpl
{
    public class VideoJuegoDAOImpl : DAOImplBase, VideojuegoDAO
    {
        Videojuego videojuego;
        public VideoJuegoDAOImpl() : base("videojuego")
        {
        }
        protected override void IncluirValorDeParametrosParaInsercion()
        {
            AgregarParametro("_id_videojuego", videojuego.Id_videojuego);
            AgregarParametro("_fid_genero", videojuego.Genero.Id_genero);
            AgregarParametro("_fid_categoria", videojuego.Categoria.Id_categoria);
            AgregarParametro("_nombre_videojuego", videojuego.Nombre_videojuego);
            AgregarParametro("_fecha_lanzamiento", videojuego.Fecha_lanzamiento);
            AgregarParametro("_precio_aprox_mercado", videojuego.Precio_aprox_mercado);
            AgregarParametro("_num_jugadores_mc", videojuego.Num_jugadores_mc);
        }

        protected override void AgregarObjetoALaLista(IList<Object> lista, DbDataReader lector)
        {
            this.videojuego = new Videojuego();
            this.videojuego.Id_videojuego = lector.GetInt32(0);
            this.videojuego.Genero = new Genero();
            this.videojuego.Genero.Id_genero = lector.GetInt32(5);
            this.videojuego.Categoria = new Categoria();
            this.videojuego.Categoria.Id_categoria = lector.GetString(6)[0];
            this.videojuego.Nombre_videojuego = lector.GetString(1);
            this.videojuego.Fecha_lanzamiento = lector.GetDateTime(2);
            this.videojuego.Precio_aprox_mercado = lector.GetDouble(3);
            this.videojuego.Num_jugadores_mc = lector.GetInt32(4);
            lista.Add(videojuego);
        }

        public int insertar(Videojuego videojuego)
        {
            this.videojuego=videojuego;
            return base.Insertar("call INSERTAR_VIDEOJUEGO(?,?,?,?,?,?,?)");
        }

        public IList<Videojuego> listarVideojuegos()
        {
            return base.ListarTodos("call LISTAR_VIDEOJUEGOS()").Cast<Videojuego>().ToList();
        }

    }
}
