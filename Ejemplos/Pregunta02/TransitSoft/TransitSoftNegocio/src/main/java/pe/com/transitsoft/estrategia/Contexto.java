package pe.com.transitsoft.estrategia;

import java.util.Map;
import pe.com.transitsoft.bo.TipoCampanha;

/**
 *
 * @author eric
 */
public class Contexto {
    private static final Map<TipoCampanha, EstrategiaCalculo> estrategias = Map.of(
        TipoCampanha.SOLO_MUY_GRAVES, new EstrategiaSoloMuyGraves(),
        TipoCampanha.GRAVES_Y_MUY_GRAVES, new EstrategiaGravesYMuyGraves(),
        TipoCampanha.MODO_GENERAL, new EstrategiaModoGeneral()
    );

    private final EstrategiaCalculo estrategia;

    public Contexto(TipoCampanha campanha) {
        this.estrategia = estrategias.getOrDefault(campanha, new EstrategiaModoGeneral());
    }

    public int ejecutar(int idConductor) {
        return estrategia.calcularPuntos(idConductor);
    }
}
