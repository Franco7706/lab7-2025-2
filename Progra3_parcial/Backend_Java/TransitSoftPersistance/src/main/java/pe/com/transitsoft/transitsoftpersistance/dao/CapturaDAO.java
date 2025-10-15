package pe.com.transitsoft.transitsoftpersistance.dao;

import java.util.List;
import pe.com.transitsoft.transitsoftmodel.Captura;

public interface CapturaDAO {
    public List<Object> listarCapturas();
    public void ModificarEstadoCaptura(Captura captura);
}
