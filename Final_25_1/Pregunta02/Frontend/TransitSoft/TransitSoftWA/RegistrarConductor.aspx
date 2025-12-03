<%@ Page Title="" Language="C#" MasterPageFile="~/TransitSoft.Master" AutoEventWireup="true" CodeBehind="RegistrarConductor.aspx.cs" Inherits="TransitSoftWA.RegistrarConductor" %>
<asp:Content ID="cph_Title" ContentPlaceHolderID="cph_Title" runat="server">
    Registrar Conductor
</asp:Content>
<asp:Content ID="cph_Scripts" ContentPlaceHolderID="cph_Scripts" runat="server">
    <script src="Scripts/TransitSoft/registrarConductor.js"></script>
</asp:Content>
<asp:Content ID="cph_Contenido" ContentPlaceHolderID="cph_Contenido" runat="server">
    <asp:ScriptManager ID="smContenido" runat="server" />
    <asp:UpdatePanel ID="upContenido" runat="server" UpdateMode="Conditional">
        <ContentTemplate>
            <div class="container">
                <div class="card">
                    <div class="card-header">
                        <h2><asp:Label ID="lblTitulo" runat="server" CssClass="fw-bold" Text="Registrar Conductor"></asp:Label></h2>
                    </div>
                    <div class="card-body pb-2">
                        <div class="card border">
                            <div class="card-header bg-light">
                                <h5 class="card-title mb-0 fw-bold">Información del Conductor</h5>
                            </div>
                            <div class="card-body">
                                <div class="mb-3 row align-items-center">
                                    <asp:Label ID="lblNombre" runat="server" Text="Nombres:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold"></asp:Label>
                                    <div class="col-sm-4">
                                        <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>
                                </div>
                                <div class="mb-3 row align-items-center">
                                    <asp:Label ID="Label1" runat="server" Text="Apellido Paterno:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold"></asp:Label>
                                    <div class="col-sm-4">
                                        <asp:TextBox ID="txtApellidoPaterno" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>
                                    <asp:Label ID="Label2" runat="server" Text="Apellido Materno:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold"></asp:Label>
                                    <div class="col-sm-4">
                                        <asp:TextBox ID="txtApellidoMaterno" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>
                                </div>
                                <div class="mb-3 row align-items-center">
                                    <asp:Label ID="Label3" runat="server" Text="Numero de Licencia:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold"></asp:Label>
                                    <div class="col-sm-3">
                                        <asp:TextBox ID="txtNumeroLicencia" runat="server" CssClass="form-control"></asp:TextBox>
                                    </div>
                                    <asp:Label ID="lblTipoLicencia" runat="server" Text="Tipo de Licencia:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold"></asp:Label>
                                    <div class="col-sm-3">
                                        <asp:DropDownList ID="ddlTipoLicencia" runat="server" CssClass="form-select" AutoPostBack="true"></asp:DropDownList>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card border">
                            <div class="card-header bg-light">
                                <h5 class="card-title mb-0 fw-bold">Información de los Vehículos del Conductor</h5>
                            </div>
                            <div class="card-body">
                                <div class="mb-3 row align-items-center">
                                    <asp:Label ID="lblPlaca" runat="server" Text="Placa:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold"></asp:Label>
                                    <div class="col-sm-2">
                                        <asp:TextBox ID="txtPlaca" runat="server" CssClass="form-control text-center" Enabled="false"></asp:TextBox>
                                    </div>
                                    <asp:LinkButton ID="btnBuscarVehiculo" runat="server" Text="<i class='fa-solid fa-magnifying-glass'></i>" CssClass="btn btn-primary col-sm-auto" OnClick="btnBuscarVehiculo_Click" />
                                </div>
                                <div class="mb-3 row align-items-center">
                                    <asp:Label ID="lblMarca" runat="server" Text="Marca:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold"></asp:Label>
                                    <div class="col-sm-3">
                                        <asp:TextBox ID="txtMarca" runat="server" CssClass="form-control text-center" Enabled="false"></asp:TextBox>
                                    </div>
                                    <asp:Label ID="lblModelo" runat="server" Text="Modelo:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold"></asp:Label>
                                    <div class="col-sm-3">
                                        <asp:TextBox ID="txtModelo" runat="server" CssClass="form-control text-center" Enabled="false"></asp:TextBox>
                                    </div>
                                    <asp:Label ID="lblAnho" runat="server" Text="Año:" CssClass="col-sm-auto pe-sm-2 col-form-label fw-bold"></asp:Label>
                                    <div class="col-sm-2">
                                        <asp:TextBox ID="txtAnho" runat="server" CssClass="form-control text-center" Enabled="false"></asp:TextBox>
                                    </div>
                                </div>
                                <div class="mb-3 row align-items-center">
                                    <asp:Label ID="lblFechaAdquisicion" runat="server" Text="Fecha Adquisicion:" CssClass="col-sm-2 col-form-label fw-bold" />
                                    <div class="col-sm-3">
                                        <input class="form-control" type="date" id="dtpFechaAdquisicion" runat="server">
                                    </div>
                                    <asp:LinkButton ID="lbBusquedaVehiculo" runat="server" Text="<i class='fa-solid fa-plus'></i>" CssClass="btn btn-primary col-sm-auto" OnClick="btnAgregarVehiculo_Click"/>
                                </div>
                                <div class="mb-3 row align-items-center">
                                    <asp:GridView ID="gvVehiculosConductor" runat="server"
                                        AllowPaging="true" PageSize="5" AutoGenerateColumns="false"
                                        CssClass="table table-hover table-responsive table-striped" OnRowDataBound="gvVehiculosConductores_RowDataBound"
                                        OnRowCommand="gvVehiculosConductor_RowCommand">
                                        <Columns>
                                            <asp:BoundField HeaderText="Placa"/>
                                            <asp:BoundField HeaderText="Marca" />
                                            <asp:BoundField HeaderText="Modelo" />
                                            <asp:BoundField HeaderText="Año" />
                                            <asp:BoundField HeaderText="Fecha de Adquisición" />
                                            <asp:TemplateField>
                                                <itemtemplate>
                                                    <asp:LinkButton runat="server" Text="<i class='fa-solid fa-x'></i>" CssClass="btn-danger"
                                                        CommandName="Eliminar" CommandArgument='<%# Eval("vehiculo.idVehiculo") %>' />
                                                </itemtemplate>
                                            </asp:TemplateField>
                                        </columns>
                                    </asp:GridView>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer clearfix">
                        <asp:LinkButton ID="btnRegresar" runat="server" Text="<i class='fa-solid fa-rotate-left'></i> Regresar"  CssClass="float-start btn btn-secondary" OnClick="btnRegresar_Click"/>
                        <asp:LinkButton ID="btnGuardar" runat="server" Text="<i class='fa-regular fa-floppy-disk'></i> Guardar" CssClass="float-end btn btn-primary" OnClick="btnGuardar_Click" />
                    </div>
                </div>
            </div>
            <div class="modal" id="form-modal-vehiculos">
                <div class="modal-dialog modal-xl">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title fw-bold">Búsqueda de Vehículos</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <asp:UpdatePanel ID="upBusqVehiculos" runat="server" UpdateMode="Conditional">
                                <ContentTemplate>
                                    <div class="container row pb-3 pt-3">
                                        <div class="row align-items-center">
                                            <div class="col-auto">
                                                <asp:Label runat="server" Text="Ingresar Placa:" CssClass="fw-bold"></asp:Label>
                                            </div>
                                            <div class="col-sm-3">
                                                <asp:TextBox ID="txtPlacaVehiculoModal" CssClass="form-control" runat="server"></asp:TextBox>
                                            </div>
                                            <div class="col-sm-2">
                                                <asp:LinkButton ID="lbBusquedaVehiculoModal" runat="server" CssClass="btn btn-info" Text="<i class='fa-solid fa-magnifying-glass pe-2'></i> Buscar"  OnClick="lbBusquedaVehiculoModal_Click"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="container row">
                                        <asp:GridView ID="gvVehiculos" runat="server" PageSize="5" AllowPaging="true" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped" OnRowDataBound="gvVehiculos_RowDataBound" OnPageIndexChanging="gvVehiculos_PageIndexChanging">
                                            <Columns>
                                                <asp:BoundField HeaderText="Placa" />
                                                <asp:BoundField HeaderText="Marca" />
                                                <asp:BoundField HeaderText="Modelo" />
                                                <asp:BoundField HeaderText="Año" />
                                                <asp:TemplateField>
                                                    <ItemTemplate>
                                                        <asp:LinkButton runat="server" Text="Seleccionar" CssClass="btn-success" OnClick="lbSeleccionarVehiculo_Click" CommandArgument='<%# Eval("idVehiculo") %>'/>
                                                    </ItemTemplate>
                                                </asp:TemplateField>
                                            </Columns>
                                        </asp:GridView>
                                    </div>
                                </ContentTemplate>
                            </asp:UpdatePanel>
                        </div>
                        <div class="modal-footer"></div>
                    </div>
                </div>
            </div>
        </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>
