import java.util.Arrays;

public class IndexWithDuplicates implements IndexService{

    private static final int CHUNK = 20;
    private int[] array;
    private int dim;

    IndexWithDuplicates(){
        array = new int[CHUNK];
        dim=0;
    }

    @Override
    public void initialize(int [] elements){
        if(elements == null){
            throw new IllegalArgumentException("ARRAY IS NULL");
        }
        quicksort(elements);
        this.array=elements;
        this.dim=elements.length;

    }


    //clavo Binary Search
    public boolean search(int key){
        if(dim == 0){
            return false;
        }
        int ans = binarySearh(this.array , 0 , array.length-1 , key);
        if(ans == -1){
            return false;
        }
        return true;
    }

    public void insert(int key){
        if(dim == array.length){
            this.array = Arrays.copyOf(array, dim + CHUNK);
        }

        // If array is empty or key is larger than the largest element, just append
        if(dim == 0 || key > array[dim-1]){
            array[dim] = key;
            dim++;
            return;
        }

        // Find position to insert (use binary search or partition)
        int pos = binarySearh(array,0,dim-1,key);
        if(pos == -1){
            array[dim++] =key;
            return;
        }

       while(array[pos] == key){
           pos++;
       }
       pos--;
        // Shift elements to make room for the new key
        for(int i = pos + 1; i <dim - 1; i++){
            array[i] = array[i+1];
        }
        array[pos] = key;
        dim++;
    }


    public void delete(int key){
        int rightpos=binarySearh(array , 0 , dim-1 , key);
        if(rightpos == -1){
            return;
        }
        for(int i=rightpos ; i<dim-1 ; i++){
            array[i] = array[i+1]; //muevo a todos uno
        }
        dim--;

    }

    public  int occurrences(int key){
        int rightpos=binarySearh(array , 0 , dim-1 , key);
        if(rightpos == -1){
            return 0;
        }
        int counter=1;
                for(int j=rightpos+1 ; j < dim  && array[j] == key ; j++){
                    counter++;
                }
                for(int k=rightpos -1 ; k>=0 && array[k] == key ; k--){
                    counter ++;
                }
        return counter;
    }


    public static void quicksort(int []arr){
        quicksortHelper(arr , 0 , arr.length -1);
    }

    private static void swap(int [] arr , int pos1 , int pos2){
        int aux;
        aux = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = aux;
    }

    private static void quicksortHelper(int [] arr , int left , int right){
        if(left >= right){
            return;
        }
        int pivotValue = arr [left];

        swap(arr , left,right); //mando el pivot al fondo

        int pivotPosCalculated = partition(arr , left , right - 1 , pivotValue);

        swap (arr , pivotPosCalculated , right);

        quicksortHelper(arr , left , pivotPosCalculated - 1);
        quicksortHelper(arr , pivotPosCalculated + 1 , right);
    }


    //en left va a estar la posicion
    private static int partition(int [] arr , int left , int right , int pivotValue){
        while(left < right){
           while(arr[left] <= pivotValue && left<right){
               left++;
           }
           while(arr[right] >= pivotValue &&  left<right){
               right--;
           }
           if(arr[left] >= arr[right]){
               swap(arr , left , right);
           }
        }
        return left;
    }

    private static int binarySearh(int arr[] , int left , int right , int key) {

        if (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                return mid;
            }

            if (arr[mid] < key) {
                return binarySearh(arr, mid+1, right, key);
            }
            if (arr[mid] > key) {
                return binarySearh(arr, left, mid-1, key);
            }
        }
        return -1;
    }

    static void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    @Override
    public int[] range(int leftKey, int rightKey, boolean leftIncluded, boolean rightIncluded) {
        int left = binarySearh(array , 0 , dim-1 , leftKey);
        int right = binarySearh(array , 0 , dim-1 , rightKey);

        if(left == -1 || right == -1){
            return new int[0];
        }

       left =findLeftBoundary(left , leftIncluded , leftKey);
       right= findRightBoundary(right , rightIncluded , rightKey);

        if(left > right){
            return new int[0];
        }

        int dim = right - left + 1;
        int [] ans = new int[dim];

        for(int i=0 ; i<dim ; i++){
            ans[i] = array[left + i];
        }
        return ans;
    }

    private int findRightBoundary(int pos , boolean included , int key){
        if(!included){
            while(pos >=0 && array[pos] == key ){
                pos--;
            }
            return pos;
        }else{
            while( pos < dim && array[pos] == key){
                pos++;
            }
            return pos-1;
        }
    }

    private int findLeftBoundary(int pos , boolean included , int key){
        if(included){
            while(pos >= 0 && array[pos] == key ){
                pos--;
            }
            return pos+1;
        }else{
            while(pos < dim && array[pos] == key  ){
                pos++;
            }
            return pos;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    static void sort(int arr[], int l, int r)
    {
        if (l < r) {

            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

}
