import java.util.Arrays;

public class miAlgor {

    public static int unalgoritmo(int[] array) {
        int i = 0;
        int j = 0;
        int m = 0;
        int c = 0;
        while (i < array.length) {
            if (array[i] == array[j]) {
                c++;
            }
            j++;
            if (j >= array.length) {
                if (c > m) {
                    m = c;
                }
                c = 0;
                i++;
                j = i;
            }
        }
        return m;
    }

    public static int miVersion(int[] arr) {
        int max=0;

        for(int i=0 ; i<arr.length ; i++){
            max=Math.max(max,arr[i]);
        }

        int [] apariciones = new int[max+1];

        max=0;

        for(int i=0 ; i<arr.length ; i++){
            apariciones[arr[i]]++;
            if(apariciones[arr[i]] > max){
                max=apariciones[arr[i]];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,1,1,1,1,1,1,5,6,7,8,9};
        System.out.println(unalgoritmo(arr));
        System.out.println(miVersion(arr));
    }
}