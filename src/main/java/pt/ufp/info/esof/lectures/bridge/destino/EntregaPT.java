package pt.ufp.info.esof.lectures.bridge.destino;

import pt.ufp.info.esof.lectures.bridge.Order;
import pt.ufp.info.esof.lectures.bridge.prioridade.Prioridade;

public class EntregaPT extends Entrega{
    public EntregaPT(Prioridade calculaEntrega) {
        super(calculaEntrega);
    }

    @Override
    public float custo(Order order) {
        return this.getCustoPrioridade(order);
    }
}
