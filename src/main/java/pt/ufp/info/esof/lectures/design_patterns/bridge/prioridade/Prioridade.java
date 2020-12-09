package pt.ufp.info.esof.lectures.design_patterns.bridge.prioridade;

import pt.ufp.info.esof.lectures.design_patterns.bridge.Order;

public interface Prioridade {
    float custo(Order order);
}
