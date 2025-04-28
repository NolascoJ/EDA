public class URLfy {

    public static void main(String[] args) {

        URLfy urlfy = new URLfy();
        char[] arreglo1 = new char[]{'e', ' ', '\0', '\0'};


        System.out.println("a");
        urlfy.reemplazarEspacios(arreglo1);
        System.out.println(arreglo1);

        char[] arreglo = new char[]{'e', 's', ' ', 'u', 'n', ' ', 'e', 'j', 'e', 'm', 'p', 'l', 'o', '\0', '\0', '\0', '\0'};

        urlfy.reemplazarEspacios(arreglo);

        System.out.println(arreglo);


        arreglo = new char[]{'a', ' ', 'b', ' ', 'c', ' ', 'd', ' ', 'e', ' ', 'f', ' ', 'g', ' ', 'h', 'o', 'l', 'a', '\0', '\0',
                '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};

        urlfy.reemplazarEspacios(arreglo);

        System.out.println(arreglo);


        arreglo = new char[]{' ', ' ', 'e', 's', 'p', 'a', 'c', 'i', 'o', 's', ' ', ' ', '\0', '\0', '\0', '\0', '\0', '\0',
                '\0', '\0'};

        urlfy.reemplazarEspacios(arreglo);

        System.out.println(arreglo);
    }

//    public void reemplazarEspacios(char [] arregloParam) {
//        int len = arregloParam.length;
//        int i = len - 1;
//
//        while(i >= 0 && arregloParam[i] == '\0'){
//            i--;
//        }
//        len--;
//        while(i >= 0) {
//            if (arregloParam[i] == ' ') {
//                arregloParam[len--] = '0';
//                arregloParam[len--] = '2';
//                arregloParam[len--] = '%';
//                i--;
//            } else {
//                arregloParam[len--] = arregloParam[i--];
//            }
//        }
//    }

    public void reemplazarEspacios(char[] arregloParam) {
        int insertIdx = arregloParam.length - 1;
        char[] keys = {'0', '2', '%'};
        int i = arregloParam.length - 1;
        while (arregloParam[i] == '\0') {
            i--; //ACOMODO EL COSO.
        }
        for (; i >= 0; i--) {
            if (arregloParam[i] == ' ') {
                arregloParam[insertIdx--] = keys[0];
                arregloParam[insertIdx--] = keys[1];
                arregloParam[insertIdx--] = keys[2];
            } else  {
                arregloParam[insertIdx--] = arregloParam[i];
        }

    }
    }
}




/*
LA COMPLEJIDAD TEMPORAL ES 0(1) PUES SE CREA UNICAMENTE 2 ints Y UN CHAR [] DE 3. ENTONCES 0(5) QUE SE ACOTA A O(1)
LA COMPLEJIDAD TEMPROAL ES O(N)
 */

