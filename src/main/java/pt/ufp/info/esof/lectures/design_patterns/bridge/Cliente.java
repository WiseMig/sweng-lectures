package pt.ufp.info.esof.lectures.design_patterns.bridge;

import pt.ufp.info.esof.lectures.design_patterns.bridge.destino.Entrega;
import pt.ufp.info.esof.lectures.design_patterns.bridge.destino.EntregaAsia;
import pt.ufp.info.esof.lectures.design_patterns.bridge.destino.EntregaEuropa;
import pt.ufp.info.esof.lectures.design_patterns.bridge.destino.EntregaPT;
import pt.ufp.info.esof.lectures.design_patterns.bridge.prioridade.Expresso;
import pt.ufp.info.esof.lectures.design_patterns.bridge.prioridade.Normal;

public class Cliente {

    public static void main(String[] args) {

        Order order=new Order();
        order.setPeso(10f);

        Entrega entrega =new EntregaPT(new Normal());
        System.out.println(entrega.custo(order));

        entrega =new EntregaPT(new Expresso());
        System.out.println(entrega.custo(order));

        entrega =new EntregaEuropa(new Normal());
        System.out.println(entrega.custo(order));

        entrega =new EntregaEuropa(new Expresso());
        System.out.println(entrega.custo(order));

        entrega =new EntregaAsia(new Normal());
        System.out.println(entrega.custo(order));

        entrega =new EntregaAsia(new Expresso());
        System.out.println(entrega.custo(order));

    }
}
