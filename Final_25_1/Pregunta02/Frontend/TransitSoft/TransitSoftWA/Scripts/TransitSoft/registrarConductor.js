let modalFormVehiculo;
function showModalFormVehiculo() {
    modalFormVehiculo = new bootstrap.Modal(document.getElementById('form-modal-vehiculos'));
    modalFormVehiculo.show();
}
function hideModalFormVehiculos() {
    modalFormVehiculo.hide();
}