package pt.ufp.info.esof.lectures.design_patterns.bridge.destino;

import pt.ufp.info.esof.lectures.design_patterns.bridge.Order;
import pt.ufp.info.esof.lectures.design_patterns.bridge.prioridade.Prioridade;

public class EntregaAsia extends Entrega{
    public EntregaAsia(Prioridade calculaEntrega) {
        super(calculaEntrega);
    }

    @Override
    public float custo(Order o) {
        return 50+this.getCustoPrioridade(o);
    }
}
