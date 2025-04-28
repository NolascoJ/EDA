
/*

MISMA IDEA QUE LEVENSHTEIN. USAMOS PROGRAMACION DINAMICA Y VAMOS GUARDANDO EL MINIMO DE LA SUMA POSIBLE QUE PUEDE
IR EN CADA SLOT. COMO SOLO TENEMOS DERECHA Y ABAJO, ES MUY FACIL CALCULAR LE SUMA QUE VA A QUEDAR AL FINAL
USAMOS UNA TABLA Y LA VAMOS LLENANDO CON LA MINIMA SUMA QUE PUEDE IR AHI (RESPETANDO CONSIGNA).
DEVOLVEMOS LA TABLA EN [N-1][M-1]

 */

public class MinPathFinder {

    public static int minCostPath(int[][] matriz) {
        int n = matriz.length;
        int m = matriz[0].length;

        int tabla[][] = new int[n][m];

        tabla[0][0]=matriz[0][0];

        //inicializo la primera fila
        for(int j=1 ; j<m ; j++){
            tabla[0][j] = tabla[0][j-1] + matriz[0][j];
        }
        //inicializo mi primera columna
        for(int i=1 ; i<n ; i++){
            tabla[i][0] = tabla[i-1][0] + matriz[i][0];
        }

        //ahora voy rellenando la tabla con las sumas minimas.
        for(int i=1 ; i<n ; i++){
            for(int j=1 ; j<m ; j++){
                tabla[i][j] = matriz[i][j] + Math.min(tabla[i-1][j] , tabla[i][j-1]);
            }
        }
        return tabla[n-1][m-1];
    }

    public static void main(String[] args) {
        int [][] matriz = new int [][]
                {{2, 8, 32, 30},
                        {12, 6, 18, 19},
                        {1, 2, 4, 8}};

        int minCost = minCostPath(matriz);
        System.out.println("Costo mínimo: " + minCost); // Output: 9
    }
}

