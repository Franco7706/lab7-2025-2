package pe.com.transitsoft.BOImpl;

import pe.com.transitsoft.bo.IConductorBO;
import pe.com.transitsoft.bo.TipoCampanha;
import pe.com.transitsoft.estrategia.Contexto;

/**
 *
 * @author eric
 */
public class ConductorBOImpl implements IConductorBO {
    @Override
    public int calcularPuntos(int idConductor, TipoCampanha campanha) {
        Contexto ctx = new Contexto(campanha);
        return ctx.ejecutar(idConductor);
    }
}
