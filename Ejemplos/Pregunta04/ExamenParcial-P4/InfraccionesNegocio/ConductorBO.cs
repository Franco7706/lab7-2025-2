using InfraccionesDominio;
using InfraccionesPersistencia;
using InfraccionesPersistencia.DAO;
using System;
using System.Collections.Generic;

namespace InfraccionesNegocio
{
    public class ConductorBO
    {
        private readonly ConductorDAO conductorDAO;

        public ConductorBO()
        {
            this.conductorDAO = new ConductorDAOImpl();
        }

        public bool RegistrarConductor(Conductor conductor)
        {
            try
            {
                ValidarConductor(conductor);
                return conductorDAO.Agregar(conductor);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al registrar el conductor", ex);
            }
        }

        public bool ActualizarConductor(Conductor conductor)
        {
            try
            {
                ValidarConductor(conductor);
                return conductorDAO.Actualizar(conductor);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al actualizar el conductor", ex);
            }
        }

        public bool EliminarConductor(int conductorId)
        {
            try
            {
                return conductorDAO.Eliminar(conductorId);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al eliminar el conductor", ex);
            }
        }

        public Conductor ObtenerConductor(int conductorId)
        {
            try
            {
                return conductorDAO.Obtener(conductorId);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al obtener el conductor", ex);
            }
        }

        public List<Conductor> ListarConductores()
        {
            try
            {
                return conductorDAO.ListarTodos();
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al listar los conductores", ex);
            }
        }

        public Conductor ListarPorLicencia(string numLicencia)
        {
            try
            {
                if (string.IsNullOrEmpty(numLicencia))
                    throw new ArgumentException("El número de licencia es requerido");

                return conductorDAO.ListarPorLicencia(numLicencia);
            }
            catch (Exception ex)
            {
                throw new InvalidOperationException("Error al buscar conductor por licencia", ex);
            }
        }

        private void ValidarConductor(Conductor conductor)
        {
            if (conductor == null)
                throw new ArgumentNullException(nameof(conductor));

            if (string.IsNullOrEmpty(conductor.Paterno))
                throw new ArgumentException("El apellido paterno es requerido");

            if (string.IsNullOrEmpty(conductor.Nombres))
                throw new ArgumentException("Los nombres son requeridos");

            if (string.IsNullOrEmpty(conductor.NumLicencia))
                throw new ArgumentException("El número de licencia es requerido");

            if (conductor.TipoLicencia == null)
                throw new ArgumentException("El tipo de licencia es requerido");

            if (conductor.PuntosAcumulados < 0)
                throw new ArgumentException("Los puntos acumulados no pueden ser negativos");
        }
    }
}