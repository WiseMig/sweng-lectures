package pt.ufp.info.esof.lectures.bridge.destino;

import pt.ufp.info.esof.lectures.bridge.Order;
import pt.ufp.info.esof.lectures.bridge.prioridade.Prioridade;

public class EntregaEuropa extends Entrega{

    public EntregaEuropa(Prioridade calculaEntrega) {
        super(calculaEntrega);
    }

    @Override
    public float custo(Order order) {
        return 30+this.getCustoPrioridade(order);
    }
}
