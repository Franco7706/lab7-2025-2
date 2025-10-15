package pe.com.transitsoft.estrategia;

import pe.com.transitsoft.dao.IConductorDAO;
import pe.com.transitsoft.daoimpl.ConductorDAOImpl;

/**
 *
 * @author eric
 */
public abstract class EstrategiaCalculo {
    private final IConductorDAO conductorDAO;
    
    public EstrategiaCalculo() {
        this.conductorDAO = new ConductorDAOImpl();
    }
    
    public IConductorDAO Dao() {
        return this.conductorDAO;
    }
    
    public abstract int calcularPuntos(int idConductor);
}
