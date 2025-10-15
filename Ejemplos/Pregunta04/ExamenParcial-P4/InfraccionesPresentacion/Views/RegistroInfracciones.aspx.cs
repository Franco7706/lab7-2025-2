using System;
using InfraccionesNegocio;
using InfraccionesDominio;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI.WebControls;


namespace InfraccionesPresentacion
{
    public partial class RegistroInfracciones : System.Web.UI.Page
    {
        private InfraccionBO infraccionBO;
        private ConductorBO conductorBO;
        private VehiculoBO vehiculoBO;
        private int InfraccionSeleccionada
        {
            get { return (int)Session["Infraccion"]; }
            set { Session["Infraccion"] = value;  }
        }
        private int ConductorSeleccionado
        {
            get { return (int)Session["Conductor"]; }
            set { Session["Conductor"] = value; }
        }
        
        private string MensajeGuardado
        {
            get { return (string)Session["MensajeGuardado"]; }
            set { Session["MensajeGuardado"] = value; }
        }

        protected void Page_Init(object sender, EventArgs e)
        {
            infraccionBO = new InfraccionBO();
            conductorBO = new ConductorBO();
            vehiculoBO = new VehiculoBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (MensajeGuardado != null)
            {
                LblMensaje.Text = MensajeGuardado;
                MensajeGuardado = null;
            }
            RptInfracciones.DataSource = infraccionBO.ListarInfracciones();
            RptInfracciones.DataBind();
            DdlVehiculos.Items.Add("-- Seleccionar --");
        }

        protected void BtnBuscarConductor_Click(object sender, EventArgs e)
        {
            string numLicencia = TxtNumLicencia.Text;
            if (string.IsNullOrEmpty(numLicencia))
            {
                LblMensaje.Text = "Por favor, ingrese un número de licencia.";
                return;
            }
            Conductor conductor = conductorBO.ListarPorLicencia(numLicencia);
            if(conductor == null)
            {
                LblMensaje.Text = "Conductor no encontrado.";
                return;
            }
            ConductorSeleccionado = conductor.ConductorId;
            TxtConductor.Text= conductor.Paterno + " " + conductor.Materno + " " + conductor.Nombres;
            List<Vehiculo> vehiculos = vehiculoBO.ListarPorConductor(conductor.ConductorId);
            DdlVehiculos.DataSource = vehiculos.Select(v=>v.Placa);
            DdlVehiculos.DataBind();
            DdlVehiculos.Items.Insert(0,"-- Seleccionar --");
            LblPuntosAcumulados.Text = conductor.PuntosAcumulados.ToString();
        }

        protected void BtnSeleccionarInfraccion_Click(object sender, EventArgs e)
        {
            int infraccionId = Int32.Parse((sender as LinkButton).CommandArgument);
            InfraccionSeleccionada = infraccionId;
            Infraccion infraccion = infraccionBO.ObtenerInfraccion(infraccionId);
            TxtInfraccion.Text = infraccion.Descripcion;
            TxtMontoMulta.Text = $"S/. {infraccion.MontoMulta}";
            TxtGravedad.Text = infraccion.Gravedad.ToString();
            TxtPuntos.Text = infraccion.Puntos.ToString();
        }

        protected void BtnRegistrar_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(TxtInfraccion.Text))
            {
                LblMensaje.Text = "Por favor, seleccione la infracción";
                return;
            }
            if (string.IsNullOrEmpty(TxtConductor.Text))
            {
                LblMensaje.Text = "Por favor, seleccione el conductor";
                return;
            }
            if (string.Equals("-- Seleccionar --",DdlVehiculos.SelectedValue))
            {
                LblMensaje.Text = "Por favor, seleccione un vehículo";
                return;
            }
            if (string.IsNullOrEmpty(TxtFecha.Text))
            {
                LblMensaje.Text = "Por favor, seleccione una fecha";
                return;
            }
            if (DateTime.Parse(TxtFecha.Text) > DateTime.Now)
            {
                LblMensaje.Text = "La fecha no puede ser futura";
                return;
            }

            Infraccion infraccion = infraccionBO.ObtenerInfraccion(InfraccionSeleccionada);
            Conductor conductor = conductorBO.ObtenerConductor(ConductorSeleccionado);
            conductor.PuntosAcumulados+=infraccion.Puntos;
            RegistroInfraccion registro = new RegistroInfraccion();
            registro.Conductor = conductor;
            registro.Infraccion = infraccion;
            registro.Fecha = DateTime.Parse(TxtFecha.Text);
            registro.Vehiculo = vehiculoBO.ListarPorConductor(ConductorSeleccionado).Where(v => v.Placa == DdlVehiculos.SelectedValue).FirstOrDefault();
            
            infraccionBO.RegistrarInfraccionConductor(registro);
            conductorBO.ActualizarConductor(conductor);
            MensajeGuardado = "Infracción registrada correctamente.";
            Response.Redirect("/Views/RegistroInfracciones.aspx");
        }
    }
}