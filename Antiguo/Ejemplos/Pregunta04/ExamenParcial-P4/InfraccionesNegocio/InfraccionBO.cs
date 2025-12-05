using InfraccionesDominio;
using InfraccionesPersistencia;
using System;
using System.Collections.Generic;

namespace InfraccionesNegocio
{
    public class InfraccionBO
    {
        private readonly InfraccionDAO infraccionDAO;

        public InfraccionBO()
        {
            this.infraccionDAO = new InfraccionDAOImpl();
        }

        public bool RegistrarInfraccion(Infraccion infraccion)
        {
            try
            {
                ValidarInfraccion(infraccion);
                return infraccionDAO.Agregar(infraccion);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al registrar la infracción", ex);
            }
        }

        public bool ActualizarInfraccion(Infraccion infraccion)
        {
            try
            {
                ValidarInfraccion(infraccion);
                return infraccionDAO.Actualizar(infraccion);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al actualizar la infracción", ex);
            }
        }

        public bool EliminarInfraccion(int infraccionId)
        {
            try
            {
                if (infraccionId <= 0)
                    throw new ArgumentException("El ID de la infracción debe ser mayor a cero");
                
                return infraccionDAO.Eliminar(infraccionId);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al eliminar la infracción", ex);
            }
        }

        public Infraccion ObtenerInfraccion(int infraccionId)
        {
            try
            {
                if (infraccionId <= 0)
                    throw new ArgumentException("El ID de la infracción debe ser mayor a cero");
                
                return infraccionDAO.Obtener(infraccionId);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al obtener la infracción", ex);
            }
        }

        public List<Infraccion> ListarInfracciones()
        {
            try
            {
                return infraccionDAO.ListarTodos();
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al listar las infracciones", ex);
            }
        }
        
        public void RegistrarInfraccionConductor(RegistroInfraccion registro)
        {
            try
            {
                ValidarRegistroInfraccion(registro);
                infraccionDAO.RegistrarInfraccionConductor(registro);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al registrar la infracción del conductor", ex);
            }
        }

        public List<RegistroInfraccion> ListarRegistroInfracciones()
        {
            try
            {
                return infraccionDAO.ListarRegistroInfracciones();
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al listar el registro de infracciones", ex);
            }
        }

        private void ValidarRegistroInfraccion(RegistroInfraccion registro)
        {
            if (registro == null)
                throw new ArgumentNullException(nameof(registro));

            if (registro.Fecha > DateTime.Now)
                throw new ArgumentException("La fecha de la infracción no puede ser futura");

            if (registro.Conductor == null || registro.Conductor.ConductorId <= 0)
                throw new ArgumentException("El conductor es requerido");

            if (registro.Vehiculo == null || registro.Vehiculo.VehiculoId <= 0)
                throw new ArgumentException("El vehículo es requerido");

            if (registro.Infraccion == null || registro.Infraccion.InfraccionId <= 0)
                throw new ArgumentException("La infracción es requerida");
        }

        private void ValidarInfraccion(Infraccion infraccion)
        {
            if (infraccion == null)
                throw new ArgumentNullException(nameof(infraccion));

            if (string.IsNullOrEmpty(infraccion.Descripcion))
                throw new ArgumentException("La descripción es requerida");

            if (infraccion.MontoMulta <= 0)
                throw new ArgumentException("El monto de la multa debe ser mayor a cero");

            if (!Enum.IsDefined(typeof(Gravedad), infraccion.Gravedad))
                throw new ArgumentException("La gravedad especificada no es válida");

            if (infraccion.Puntos < 0)
                throw new ArgumentException("Los puntos no pueden ser negativos");
        }
    }
}