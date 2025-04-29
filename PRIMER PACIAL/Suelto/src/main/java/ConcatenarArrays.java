public class ConcatenarArrays {
    public static void main(String[] args) {
        int[][] multiplesArrays = new int[][] {
                new int[] {49, 392, 10, -5},
                new int[] {293, 1, 29, 19},
                new int[] {1, 1, 1, -4},
                new int[] {0, 0, -1, 1}
        };
        ConcatenarArrays concatenador = new ConcatenarArrays();
        int[] nuevoArray = concatenador.concatenarMultiples(multiplesArrays);
        for(int i=0; i<nuevoArray.length; i++) {
            System.out.println(nuevoArray[i]);
        }
    }
    private int[] concatenarMultiples(int[][] multiplesArrays) {
        int[] nuevoArray = new int[0]; // 1 asignacvion
        for (int i=0; i<multiplesArrays.length; i++) { // De 0 a n-1 +1 corte -> n+1
            nuevoArray = concatenarDosArrays(nuevoArray, multiplesArrays[i]); //
        }
        return nuevoArray;
    }
    private int[] concatenarDosArrays(int[] array1, int[] array2) {
        int[] ret = new int[array1.length + array2.length]; //1 asignacion
        for(int i = 0; i<array1.length; i++) { //de 0 a n(m-1)
            ret[i] = array1[i];
        }
        for(int i = 0; i<array2.length; i++) {
            ret[i+array1.length] = array2[i];
        }
        return ret;
    }
}