package pe.com.transitsoft.app;

import pe.com.transitsoft.BOImpl.ConductorBOImpl;
import pe.com.transitsoft.bo.IConductorBO;
import pe.com.transitsoft.bo.TipoCampanha;

/**
 *
 * @author eric
 */
public class TransitSoftAppPrueba {
    public static void main(String[] args) {
        IConductorBO conductorBO = new ConductorBOImpl();
        int pgeneral=conductorBO.calcularPuntos(1, TipoCampanha.MODO_GENERAL);
        int pgraves=conductorBO.calcularPuntos(2, TipoCampanha.GRAVES_Y_MUY_GRAVES);
        int pmuygraves=conductorBO.calcularPuntos(3, TipoCampanha.SOLO_MUY_GRAVES);
        System.out.println(pgeneral + ", " + pgraves + ", " + pmuygraves);
    }
}
