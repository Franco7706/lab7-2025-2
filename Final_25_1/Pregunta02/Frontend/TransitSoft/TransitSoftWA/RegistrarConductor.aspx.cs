using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.EnterpriseServices;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TransitSoftBO;
using TransitSoftBO.TransitSoftWS;

namespace TransitSoftWA
{
    public partial class RegistrarConductor : System.Web.UI.Page
    {
        /*Colocar datos
         * -----------------------------------------
         * Código PUCP: 20232163
         * Nombre Completo: Franco Fernando Gallardo Zárate
         */

        private VehiculoBO boVehiculo;
        private TipoLicenciaBO boTipoLicencia;
        private ConductorBO boConductor;

        private vehiculo vehiculo;

        private BindingList<vehiculo> vehiculos;

        private conductor conductor;

        private vehiculoConductor vehiculoConductor;

        private BindingList<vehiculoConductor> vehiculosConductor;

        private BindingList<tipoLicencia> tiposLicencia;

        public RegistrarConductor()
        {
            boVehiculo = new VehiculoBO();
            boTipoLicencia = new TipoLicenciaBO();
            boConductor = new ConductorBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                tiposLicencia = boTipoLicencia.ListarTiposLicencia();
                ddlTipoLicencia.DataSource = tiposLicencia;
                ddlTipoLicencia.DataTextField = "nombre";
                ddlTipoLicencia.DataValueField = "idTipoLicencia";
                ddlTipoLicencia.DataBind();
            }

            if (Session["vehiculo"] != null)
                vehiculo = (vehiculo)Session["vehiculo"];

            if (Session["vehiculosConductor"] == null)
                vehiculosConductor = new BindingList<vehiculoConductor>();
            else
                vehiculosConductor = (BindingList<vehiculoConductor>)Session["vehiculosConductor"];

            gvVehiculosConductor.DataSource = vehiculosConductor;
            gvVehiculosConductor.DataBind();

            if (Session["vehiculos"] != null)
                vehiculos = (BindingList<vehiculo>)Session["vehiculos"];
        }

        protected void btnBuscarVehiculo_Click(object sender, EventArgs e)
        {
            vehiculos = boVehiculo.listarVehiculosPorPlaca(txtPlacaVehiculoModal.Text);
            gvVehiculos.DataSource = vehiculos;
            gvVehiculos.DataBind();
            Session["vehiculos"] = vehiculos;
            string script = "showModalFormVehiculo();";
            ScriptManager.RegisterStartupScript(this, GetType(), "showModalFormVehiculo", script, true);
        }

        protected void btnAgregarVehiculo_Click(object sender, EventArgs e)
        {
            vehiculoConductor = new vehiculoConductor();
            vehiculoConductor.vehiculo = (vehiculo)Session["vehiculo"];
            vehiculoConductor.conductor = conductor;
            vehiculoConductor.fechaAdquisicion = DateTime.Parse(dtpFechaAdquisicion.Value);
            vehiculoConductor.fechaAdquisicionSpecified = true;
            vehiculosConductor.Add(vehiculoConductor);
            Session["vehiculosConductor"] = vehiculosConductor;
            gvVehiculosConductor.DataSource = vehiculosConductor;
            gvVehiculosConductor.DataBind();
            upContenido.Update();
        }

        protected void lbSeleccionarVehiculo_Click(object sender, EventArgs e)
        {
            int idVehiculo = Int32.Parse(((LinkButton)sender).CommandArgument);
            vehiculo = ((BindingList<vehiculo>)Session["vehiculos"]).SingleOrDefault(x => x.idVehiculo == idVehiculo);
            Session["vehiculo"] = vehiculo;
            txtMarca.Text = vehiculo.marca;
            txtModelo.Text = vehiculo.modelo;
            txtPlaca.Text = vehiculo.placa;
            txtAnho.Text = vehiculo.anho.ToString();
            upContenido.Update();
            string script = "hideModalFormVehiculos();";
            ScriptManager.RegisterStartupScript(this, GetType(), "HideModalFormProducto", script, true);
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Home.aspx");
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            conductor = new conductor();
            conductor.nombres = txtNombre.Text;
            conductor.apellidoPaterno = txtApellidoPaterno.Text;
            conductor.apellidoMaterno = txtApellidoMaterno.Text;
            conductor.numeroLicencia = txtNumeroLicencia.Text;
            conductor.tipoLicencia = new tipoLicencia();
            conductor.tipoLicencia.idTipoLicencia = Int32.Parse(ddlTipoLicencia.SelectedValue);
            boConductor.InsertarConductor(conductor,vehiculosConductor);
            Session["vehiculosConductor"] = null;
            gvVehiculosConductor.DataSource = vehiculosConductor;
            gvVehiculosConductor.DataBind();
            upContenido.Update();
            Response.Redirect(Request.RawUrl);
        }

        protected void lbBusquedaVehiculoModal_Click(object sender, EventArgs e)
        {
            vehiculos = boVehiculo.listarVehiculosPorPlaca(txtPlacaVehiculoModal.Text);
            gvVehiculos.DataSource = vehiculos;
            gvVehiculos.DataBind();
            upBusqVehiculos.Update();
        }

        protected void gvVehiculos_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "placa").ToString();
                e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "marca").ToString();
                e.Row.Cells[2].Text = DataBinder.Eval(e.Row.DataItem, "modelo").ToString();
                e.Row.Cells[3].Text = DataBinder.Eval(e.Row.DataItem, "anho").ToString();
            }
        }

        protected void gvVehiculos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvVehiculos.PageIndex = e.NewPageIndex;
            gvVehiculos.DataSource = (BindingList<vehiculo>)Session["vehiculos"];
            gvVehiculos.DataBind();
            upBusqVehiculos.Update();
        }
        protected void gvVehiculosConductor_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if(e.CommandName == "Eliminar")
            {
                int id = Int32.Parse((string)e.CommandArgument);
                vehiculosConductor.Remove(vehiculosConductor.First(v=>v.vehiculo.idVehiculo==id));
                Session["vehiculosConductor"] = vehiculosConductor;
                gvVehiculosConductor.DataSource = vehiculosConductor;
                gvVehiculosConductor.DataBind();
                upContenido.Update();
            }
        }
        protected void gvVehiculosConductores_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = ((vehiculo)DataBinder.Eval(e.Row.DataItem, "vehiculo")).placa.ToString();
                e.Row.Cells[1].Text = ((vehiculo)DataBinder.Eval(e.Row.DataItem, "vehiculo")).marca.ToString();
                e.Row.Cells[2].Text = ((vehiculo)DataBinder.Eval(e.Row.DataItem, "vehiculo")).modelo.ToString();
                e.Row.Cells[3].Text = ((vehiculo)DataBinder.Eval(e.Row.DataItem, "vehiculo")).anho.ToString();
                e.Row.Cells[4].Text = ((DateTime)DataBinder.Eval(e.Row.DataItem, "fechaAdquisicion")).ToString("dd-MM-yyyy");
            }
        }
    }
}