using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVidModel
{
    public class Videojuego
    {
        private int? id_videojuego;
        private Genero genero;
        private Categoria categoria;
        private String nombre_videojuego;
        private DateTime fecha_lanzamiento;
        private Double precio_aprox_mercado;
        private int? num_jugadores_mc;
        public Videojuego() { 
            Id_videojuego = null;
            Nombre_videojuego = null;
            Fecha_lanzamiento = DateTime.MinValue;
            Precio_aprox_mercado = 0.0;
            Num_jugadores_mc = null;
            Genero = new Genero();
            Categoria = new Categoria();
        }

        public Videojuego(int id_videojuego,
            String nombre_videojuego, DateTime fecha_lanzamiento,
            Double precio_aprox_mercado, int num_jugadores_mc,
            Genero genero, Categoria categoria)
        {
            this.Id_videojuego = id_videojuego;
            this.Nombre_videojuego = nombre_videojuego;
            this.Fecha_lanzamiento = fecha_lanzamiento;
            this.Precio_aprox_mercado = precio_aprox_mercado;
            this.Num_jugadores_mc = num_jugadores_mc;
            this.Genero = genero;
            this.Categoria = categoria;
        }

        public int? Id_videojuego { get => id_videojuego; set => id_videojuego = value; }
        public string Nombre_videojuego { get => nombre_videojuego; set => nombre_videojuego = value; }
        public DateTime Fecha_lanzamiento { get => fecha_lanzamiento; set => fecha_lanzamiento = value; }
        public double Precio_aprox_mercado { get => precio_aprox_mercado; set => precio_aprox_mercado = value; }
        public int? Num_jugadores_mc { get => num_jugadores_mc; set => num_jugadores_mc = value; }
        public Genero Genero { get => genero; set => genero = value; }
        public Categoria Categoria { get => categoria; set => categoria = value; }
    }
}
