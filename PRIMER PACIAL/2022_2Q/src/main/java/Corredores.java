public class Corredores {

    public int[] tiemposEntre(int[] tiempos, Pedido[] p) {
        // TODO: completar
    }

    public static void main(String[] args) {
        Corredores c = new Corredores();

        Pedido[] pedidos = new Pedido[] {
                new Pedido(200, 240),
                new Pedido(180, 210),
                new Pedido(220, 280),
                new Pedido(0, 200),
                new Pedido(290, 10000)
        };

        int[] tiempos = new int[] {
                192,
                200,
                210,
                221,
                229,
                232,
                240,
                240,
                243,
                247,
                280,
                285
        };

        int [] respuestas = c.tiemposEntre(tiempos, pedidos);
        for(int i=0; i< respuestas.length; i++) {
            System.out.println(respuestas[i]);
        }

    }
}

class Pedido {
    public int desde;
    public int hasta;
    public Pedido(int desde, int hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }
}