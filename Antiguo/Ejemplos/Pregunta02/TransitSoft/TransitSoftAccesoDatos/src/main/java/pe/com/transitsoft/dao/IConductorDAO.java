package pe.com.transitsoft.dao;

import pe.com.transitsoft.modelo.Gravedad;

/**
 *
 * @author eric
 */
public interface IConductorDAO {
    int obtenerPuntos(int idConductor, Gravedad gravedad);
}
