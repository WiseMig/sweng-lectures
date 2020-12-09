package pt.ufp.info.esof.lectures.bridge.prioridade;

import pt.ufp.info.esof.lectures.bridge.Order;

public class Normal implements Prioridade {


    @Override
    public float custo(Order o) {
        return 10+o.getPeso();
    }
}
