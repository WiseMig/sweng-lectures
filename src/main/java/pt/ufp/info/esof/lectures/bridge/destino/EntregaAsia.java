package pt.ufp.info.esof.lectures.bridge.destino;

import pt.ufp.info.esof.lectures.bridge.Order;
import pt.ufp.info.esof.lectures.bridge.prioridade.Prioridade;

public class EntregaAsia extends Entrega{
    public EntregaAsia(Prioridade calculaEntrega) {
        super(calculaEntrega);
    }

    @Override
    public float custo(Order o) {
        return 50+this.getCustoPrioridade(o);
    }
}
