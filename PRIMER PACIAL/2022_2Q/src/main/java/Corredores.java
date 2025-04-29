import java.util.Arrays;

public class Corredores {

    public int[] tiemposEntre(int[] tiempos, Pedido[] p) {
        int [] toReturn = new int[p.length];
            if(tiempos.length > 500000){
                throw new IllegalArgumentException("No puede haber mas de 500000 corredores");
            }
            if(tiempos[tiempos.length-1] >1000){
                throw new IllegalArgumentException("No puede habe run tiempo mas largo que 1000");
            }
            if(p.length > 1000000){
                throw new IllegalArgumentException("No pueden haber mas de 1000000 de pedidos");
            }
            for(int i=0 ; i<p.length ; i++){
                int lower = getClosestPosition(p[i].desde , tiempos);
                int upper = getClosestPosition(p[i].hasta+1 , tiempos);

                toReturn[i] = upper-lower;
            }

            return toReturn;
    }

    private int getClosestPosition(int key , int [] tiempos) {
        int l = 0, r = tiempos.length;
        while(l < r) {
            int mid = l + (r - l) / 2;
            if (tiempos[mid] >= key) {
                r = mid;
            } else {
                l = mid+1;
            }
        }
        return l;
    }

//
//    public int range(int leftKey, int rightKey , int []tiempos){
//        //int[] tgt = new int[BLOCK];
//
//        int posL = getClosestPosition(leftKey,tiempos) ;
//        int posR = getClosestPosition(rightKey,tiempos) ;
//
////        if(posR - posL < 1){
////            return new int[0];
////        }
//
//        while(posR < tiempos.length && tiempos[posR] == rightKey){
//            posR++;
//        }
//
//        int[] tgt= Arrays.copyOfRange(tiempos, posL, posR);
//        //System.arraycopy(indexes, posL, tgt , 0 ,  posR - posL);
//        System.out.print("[ ");
//        for(int i = 0; i< tgt.length; i++) System.out.print(tgt[i] + " ");
//        System.out.println("]");
//
//        return posR - posL ;
//    }

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