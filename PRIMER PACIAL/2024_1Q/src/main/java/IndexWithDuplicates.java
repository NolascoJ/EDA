import java.util.Arrays;

/**
 * @author dpenaloza
 *
 */
public class IndexWithDuplicates  {

    final static private int chunksize= 5;

    private int[] indexedData;
    private int cantElems;


    public IndexWithDuplicates() {
        indexedData= new int[chunksize];
        cantElems= 0;
    }

    public void initialize(int[] unsortedElements) {

        if (unsortedElements == null)
            throw new RuntimeException("Problem: null data collection");

        indexedData= unsortedElements;
        Arrays.sort(indexedData);
        cantElems= indexedData.length;
    }


    public int[] getIndexedData() {
        return indexedData;
    }

    public void print() {
        System.out.print("[");
        for (int i : indexedData)
            System.out.print(i + " ") ;
        System.out.println("]");

    }

    public void merge(IndexWithDuplicates other) {
        int[] toReturn = new int[this.cantElems + other.cantElems];
        int ptr1 = 0;
        int ptr2 = 0;
        int i=0;
        while(ptr1 < cantElems && ptr2 < other.cantElems){
            if( indexedData[ptr1] < other.indexedData[ptr2]){
                toReturn[i++]=indexedData[ptr1++];
            }else if( other.indexedData[ptr2] < indexedData[ptr1] ){
                toReturn[i++] = other.indexedData[ptr2++];
            }else {  //son iguales
                toReturn[i++] = indexedData[ptr1++];
                toReturn[i++] = other.indexedData[ptr2++];
            }
        }
        if(ptr1 == cantElems){
            while(ptr2 < other.cantElems){
                toReturn[i++] = other.indexedData[ptr2++];
            }
        }else if(ptr2 == other.cantElems){
            while(ptr1 < cantElems){
                toReturn[i++] = indexedData[ptr1++];
            }
        }
        cantElems=i;
        indexedData=toReturn;
    }

    public static void main(String[] args) {
        IndexWithDuplicates index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 3, 5, 7});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 6, 8});
        index1.merge(index2);
// Resultado esperado: [1, 2, 3, 4, 5, 6, 7, 8]

        for(int i=0 ; i<index1.cantElems ; i++){
            System.out.print(index1.indexedData[i]);
        }
    // Resultado esperado: [1, 1, 2, 3, 4, 4, 5, 6, 7, 8]

    }

}