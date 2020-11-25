package pt.ufp.info.esof.lectures.bridge.destino;

import pt.ufp.info.esof.lectures.bridge.Order;
import pt.ufp.info.esof.lectures.bridge.prioridade.Prioridade;

public abstract class Entrega {
    private final Prioridade calculaEntrega;

    public Entrega(Prioridade calculaEntrega) {
        this.calculaEntrega = calculaEntrega;
    }

    protected float getCustoPrioridade(Order o){
        return calculaEntrega.custo(o);
    }

    public abstract float custo(Order o);
}
