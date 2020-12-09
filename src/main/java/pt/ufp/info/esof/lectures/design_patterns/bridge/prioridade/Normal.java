package pt.ufp.info.esof.lectures.design_patterns.bridge.prioridade;

import pt.ufp.info.esof.lectures.design_patterns.bridge.Order;

public class Normal implements Prioridade {


    @Override
    public float custo(Order o) {
        return 10+o.getPeso();
    }
}
