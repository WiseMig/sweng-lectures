package pt.ufp.info.esof.lectures.design_patterns.bridge.prioridade;

import pt.ufp.info.esof.lectures.design_patterns.bridge.Order;

public class Expresso implements Prioridade {

    @Override
    public float custo(Order o) {
        return 20+2*o.getPeso();
    }
}
