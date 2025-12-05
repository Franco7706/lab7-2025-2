using System;
using System.Collections.Generic;
using InfraccionesDominio;
using InfraccionesNegocio;

namespace InfraccionesPresentacion
{
	public partial class ListaInfracciones : System.Web.UI.Page
	{
        private InfraccionBO infraccionBO;

		private void Page_Init(object sender, EventArgs e)
        {
            infraccionBO = new InfraccionBO();
        }
        protected void Page_Load(object sender, EventArgs e)
		{
			CargarInfracciones();
		}
		private void CargarInfracciones()
        {
            try
            {
                List<RegistroInfraccion> infracciones = infraccionBO.ListarRegistroInfracciones();
                GvInfracciones.DataSource = infracciones;
                GvInfracciones.DataBind();
            }
            catch (Exception ex)
            {
                LblMensaje.Text = "Error al cargar las infracciones: " + ex.Message;
                LblMensaje.CssClass = "alert alert-danger";
            }
        }
    }
}