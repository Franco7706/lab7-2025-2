<%@ Page Title="" Language="C#" MasterPageFile="~/MainLayout.Master" AutoEventWireup="true"
    CodeBehind="RegistroInfracciones.aspx.cs" Inherits="InfraccionesPresentacion.RegistroInfracciones" %>

<asp:Content ID="Content1" ContentPlaceHolderID="Content" runat="server">
    <div class="container mt-4">
        <h2>Registro de Infracciones</h2>

        <div class="row mb-3">
            <div class="col-md-6">
                <label for="TxtNumLicencia">Número de Licencia:</label>
                <div class="input-group">
                    <asp:TextBox ID="TxtNumLicencia" runat="server" CssClass="form-control"></asp:TextBox>
                    <div class="input-group-append">
                        <asp:Button ID="BtnBuscarConductor" runat="server" Text="Buscar"
                            CssClass="btn btn-primary" OnClick="BtnBuscarConductor_Click" />
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label for="LblConductor">Conductor:</label>
                <asp:TextBox ID="TxtConductor" runat="server" ReadOnly="true" CssClass="form-control"></asp:TextBox>
            </div>
            <div class="col-md-6">
                <label for="DdlVehiculos">Vehículo:</label>
                <asp:DropDownList ID="DdlVehiculos" runat="server" CssClass="form-select">
                </asp:DropDownList>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label>Infracción:</label>
                <div class="input-group">
                    <asp:TextBox ID="TxtInfraccion" runat="server" ReadOnly="true" CssClass="form-control"></asp:TextBox>
                    <div class="input-group-append">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalInfracciones">
                            Seleccionar
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <label for="TxtFecha">Fecha de Infracción:</label>
                <asp:TextBox ID="TxtFecha" runat="server" CssClass="form-control" TextMode="Date"></asp:TextBox>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-12">
                <div class="bg-light p-3 rounded border border-primary">
                    <h5 class="text-primary mb-3">Detalles de la Infracción</h5>
                    <div class="row">
                        <div class="col-md-4">
                            <label>Monto Multa:</label>
                            <asp:TextBox ID="TxtMontoMulta" runat="server" ReadOnly="true" CssClass="form-control bg-white"></asp:TextBox>
                        </div>
                        <div class="col-md-4">
                            <label>Gravedad:</label>
                            <asp:TextBox ID="TxtGravedad" runat="server" ReadOnly="true" CssClass="form-control bg-white"></asp:TextBox>
                        </div>
                        <div class="col-md-4">
                            <label>Puntos:</label>
                            <asp:TextBox ID="TxtPuntos" runat="server" ReadOnly="true" CssClass="form-control bg-white"></asp:TextBox>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-3">           
            <div class="col-md-4">
                <label for="LblPuntosAcumulados">Puntos Acumulados:</label> 
                <asp:Label ID="LblPuntosAcumulados" runat="server" CssClass="fw-bold fs-4"> 0
                </asp:Label>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <asp:Button ID="BtnRegistrar" runat="server" Text="Registrar Infracción" CssClass="btn btn-success"
                    OnClick="BtnRegistrar_Click" />
                <asp:Label ID="LblMensaje" runat="server" CssClass="ml-3"></asp:Label>
            </div>
        </div>        
    </div>

    <!-- Modal de Infracciones -->
<div class="modal fade" id="modalInfracciones" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Seleccionar Infracción</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Descripción</th>
                            <th>Monto</th>
                            <th>Gravedad</th>
                            <th>Puntos</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <asp:Repeater ID="RptInfracciones" runat="server">
                            <ItemTemplate>
                                <tr>
                                    <td><%# Eval("Descripcion") %></td>
                                    <td><%# Eval("MontoMulta", "{0:C}") %></td>
                                    <td><%# Eval("Gravedad") %></td>
                                    <td><%# Eval("Puntos") %></td>
                                    <td>
                                        <asp:LinkButton ID="BtnSeleccionarInfraccion" runat="server" 
                                            CssClass="btn btn-sm btn-primary"
                                            CommandName="Seleccionar" 
                                            CommandArgument='<%# Eval("InfraccionId") %>'
                                            OnClick="BtnSeleccionarInfraccion_Click">
                                            Seleccionar
                                        </asp:LinkButton>
                                    </td>
                                </tr>
                            </ItemTemplate>
                        </asp:Repeater>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</asp:Content>
