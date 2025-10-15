using InfraccionesDominio;
using InfraccionesPersistencia;
using System;
using System.Collections.Generic;

namespace InfraccionesNegocio
{
    public class VehiculoBO
    {
        private readonly VehiculoDAO vehiculoDAO;

        public VehiculoBO()
        {
            this.vehiculoDAO = new VehiculoDAOImpl();
        }

        public bool RegistrarVehiculo(Vehiculo vehiculo)
        {
            try
            {
                ValidarVehiculo(vehiculo);
                return vehiculoDAO.Agregar(vehiculo);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al registrar el vehículo", ex);
            }
        }

        public bool ActualizarVehiculo(Vehiculo vehiculo)
        {
            try
            {
                ValidarVehiculo(vehiculo);
                return vehiculoDAO.Actualizar(vehiculo);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al actualizar el vehículo", ex);
            }
        }

        public bool EliminarVehiculo(int vehiculoId)
        {
            try
            {
                return vehiculoDAO.Eliminar(vehiculoId);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al eliminar el vehículo", ex);
            }
        }

        public Vehiculo ObtenerVehiculo(int vehiculoId)
        {
            try
            {
                return vehiculoDAO.Obtener(vehiculoId);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al obtener el vehículo", ex);
            }
        }

        public List<Vehiculo> ListarVehiculos()
        {
            try
            {
                return vehiculoDAO.ListarTodos();
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al listar los vehículos", ex);
            }
        }

        public List<Vehiculo> ListarPorConductor(int conductorId)
        {
            try
            {
                if (conductorId <= 0)
                    throw new ArgumentException("El ID del conductor debe ser mayor a cero");

                return vehiculoDAO.ListarPorConductor(conductorId);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al listar los vehículos del conductor", ex);
            }
        }

        public void AsignarAConductor(VehiculoPorConductor asignacion)
        {
            try
            {
                ValidarAsignacion(asignacion);
                vehiculoDAO.AsignarAConductor(asignacion);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al asignar el vehículo al conductor", ex);
            }
        }

        private void ValidarVehiculo(Vehiculo vehiculo)
        {
            if (vehiculo == null)
                throw new ArgumentNullException(nameof(vehiculo));

            if (string.IsNullOrEmpty(vehiculo.Placa))
                throw new ArgumentException("La placa es requerida");

            if (string.IsNullOrEmpty(vehiculo.Marca))
                throw new ArgumentException("La marca es requerida");

            if (string.IsNullOrEmpty(vehiculo.Modelo))
                throw new ArgumentException("El modelo es requerido");

            if (vehiculo.Anho <= 1900 || vehiculo.Anho > DateTime.Now.Year)
                throw new ArgumentException("El año del vehículo no es válido");
        }

        private void ValidarAsignacion(VehiculoPorConductor asignacion)
        {
            if (asignacion == null)
                throw new ArgumentNullException(nameof(asignacion));

            if (asignacion.Vehiculo == null || asignacion.Vehiculo.VehiculoId <= 0)
                throw new ArgumentException("El vehículo es requerido");

            if (asignacion.Conductor == null || asignacion.Conductor.ConductorId <= 0)
                throw new ArgumentException("El conductor es requerido");

            if (asignacion.FechaAdquisicion > DateTime.Now)
                throw new ArgumentException("La fecha de adquisición no puede ser futura");
        }
    }
}