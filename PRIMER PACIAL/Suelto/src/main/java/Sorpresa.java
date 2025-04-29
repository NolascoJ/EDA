import java.util.Arrays;

public class Sorpresa {

    //COMPLEJIDAD ARRAYS.SORT -> nlogn

    static public boolean sorpresaV3(int[] arreglo, int n) {
        if (arreglo == null || n < 0 || arreglo.length < n)
            throw new RuntimeException("bad parameter");

        Arrays.sort(arreglo,0,n);

        for(int i=0 ; i<n-1 ; i++){
            if( arreglo[i] == arreglo[i+1]){
                return false;
            }
        }
        return true;
    }

    static public boolean sorpresaV1(int[] arreglo, int n)
    {
        if (arreglo== null || n < 0 || arreglo.length < n)
            throw new RuntimeException("bad parameter");
        for ( int rec= 0; rec < n - 1; rec++)
            for ( int iter = rec + 1 ; iter <= n - 1 ; iter++)
                if ( arreglo[rec] == arreglo[iter] )
                    return false;

        return true;
    }


    static public boolean sorpresaV2(int[] arreglo, int n)
    {
        if (arreglo== null || n < 0 || arreglo.length < n)
            throw new RuntimeException("bad parameter");
        for ( int rec= 0; rec <= n - 1; rec++)
            for ( int iter = 0 ; iter <= n - 1 ; iter++)
                if ( rec != iter && arreglo[rec] == arreglo[iter] )
                    return false;

        return true;
    }


    public static void main(String args[]) {
        System.out.println("Primer dataset");
        System.out.println("sorpresaV1= " + sorpresaV1(new int[]{30, 20, 15, 80, 10, 20}, 6));
        System.out.println("sorpresaV2= " + sorpresaV2(new int[]{30, 20, 15, 80, 10, 20}, 6));
        System.out.println("sorpresaV3= " + sorpresaV3(new int[]{30, 20, 15, 80, 10, 20}, 6));
        System.out.println("Segundo dataset");
        System.out.println("sorpresaV1= " + sorpresaV1(new int[]{30, 20, 15, 80, 10, 20}, 4));
        System.out.println("sorpresaV2= " + sorpresaV2(new int[]{30, 20, 15, 80, 10, 20}, 4));
        System.out.println("sorpresaV3= " + sorpresaV3(new int[]{30, 20, 15, 80, 10, 20}, 4));
        System.out.println("Tercer dataset");
        int[] auxi = new int[100];
        auxi[0] = 30;
        auxi[1] = 20;
        auxi[2] = 10;
        auxi[3] = 120;
        auxi[4] = 140;
        auxi[5] = 150;
        auxi[6] = 150;
        System.out.println("sorpresaV1= " + sorpresaV1(auxi, 7));
        System.out.println("sorpresaV2= " + sorpresaV2(auxi, 7));
        System.out.println("sorpresaV3= " + sorpresaV3(auxi, 7));
        System.out.println("Cuarto dataset");
        auxi = new int[100];
        auxi[0] = 20;
        auxi[1] = 30;
        auxi[2] = 60;
        auxi[3] = 70;
        auxi[4] = 50;
        auxi[5] = 40;
        System.out.println("sorpresaV1= " + sorpresaV1(auxi, 6));
        System.out.println("sorpresaV2= " + sorpresaV2(auxi, 6));
        System.out.println("sorpresaV3= " + sorpresaV3(auxi, 6));
        System.out.println("Quinto dataset");
        System.out.println("sorpresaV1= " + sorpresaV1(new int[]{30, 20, 15, 80, 10, 20}, 8));
        System.out.println("sorpresaV2= " + sorpresaV2(new int[]{30, 20, 15, 80, 10, 20}, 8));
        System.out.println("sorpresaV3= " + sorpresaV3(new int[]{30, 20, 15, 80, 10, 20}, 8));
    }
}
