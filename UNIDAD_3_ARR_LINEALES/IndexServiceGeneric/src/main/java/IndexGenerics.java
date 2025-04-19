import java.util.Arrays;

public class IndexGenerics<T extends Comparable<? super T>> implements IndexServiceGeneric<T> {

    private static final int CHUNK = 20;
    private Object[] array;  // Mantenemos Object[] como lo deseas
    private int dim;

    IndexGenerics() {
        array = new Object[CHUNK];
        dim = 0;
    }

    @Override
    public void initialize(T[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("ARRAY IS NULL");
        }
        quicksort(elements);
        this.array = elements;  // Esto es seguro porque T[] es compatible con Object[]
        this.dim = elements.length;
    }

    public boolean search(T key) {
        if (dim == 0) {
            return false;
        }
        int ans = binarySearch(0, dim - 1, key);
        return ans != -1;
    }

    private int binarySearch(int left, int right, T key) {
        if (left <= right) {
            int mid = left + (right - left) / 2;

            @SuppressWarnings("unchecked")
            T midVal = (T) array[mid];  // Cast seguro porque sabemos que solo hay elementos T

            if(midVal == null){
                return -1;
            }


            int cmp = midVal.compareTo(key);


            if (cmp == 0) {
                return mid;
            }
            if (cmp < 0) {
                return binarySearch(mid + 1, right, key);
            }
            return binarySearch(left, mid - 1, key);
        }
        return -1;
    }

    public void insert(T key){
        if(dim == array.length){
            this.array = Arrays.copyOf(array, dim + CHUNK);
        }

        if (dim == 0) {
            array[0] = key;
            dim++;
            return;
        }

        @SuppressWarnings("unchecked")
        T lastElement = (T) array[dim - 1];

        // If array is empty or key is larger than the largest element, just append
        if(key.compareTo(lastElement) > 0){
            array[dim] = key;
            dim++;
            return;
        }

        // Find position to insert (use binary search or partition)
        int pos = binarySearch(0,dim-1,key);
        if(pos == -1){
            array[dim++] =key;
            return;
        }

        @SuppressWarnings("unchecked")
        T elemtoCompare = (T) array[pos];

        while(key.compareTo(elemtoCompare) == 0){
            pos++;
            elemtoCompare = (T) array[pos];
        }
        pos--;
        // Shift elements to make room for the new key
        for(int i = pos + 1; i <dim - 1; i++){
            array[i] = array[i+1];
        }
        array[pos] = key;
        dim++;
    }


    public void delete(T key){
        int rightpos=binarySearch(0 , dim-1 , key);
        if(rightpos == -1){
            return;
        }
        for(int i=rightpos ; i<dim-1 ; i++){
            array[i] = array[i+1]; //muevo a todos uno
        }
        dim--;

    }

    public  int occurrences(T key){
        int rightpos=binarySearch(0 , dim-1 , key);
        if(rightpos == -1){
            return 0;
        }
        int counter=1;

        T rightkeys = (T) array[rightpos + 1];
        T leftkeys = (T) array[rightpos - 1];

        for(int j=rightpos+1 ; j < dim  && rightkeys.compareTo(key) == 0 ; j++){
            rightkeys = (T) array[j];
            counter++;
        }
        for(int k=rightpos -1 ; k>=0 && leftkeys.compareTo(key) == 0 ; k--){
            leftkeys = (T) array[k];
            counter ++;
        }
        return counter;
    }

    private void quicksort(T[] arr) {
        quicksortHelper(arr, 0, arr.length - 1);
    }

    private static <T> void swap(T[] arr, int pos1, int pos2) {
        T aux = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = aux;
    }

    private void quicksortHelper(T[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        @SuppressWarnings("unchecked")
        T pivotValue = arr[left];

        swap(arr, left, right);

        int pivotPosCalculated = partition(arr, left, right - 1, pivotValue);

        swap(arr, pivotPosCalculated, right);

        quicksortHelper(arr, left, pivotPosCalculated - 1);
        quicksortHelper(arr, pivotPosCalculated + 1, right);
    }

    private int partition(T[] arr, int left, int right, T pivotValue) {
        while (left < right) {
            while (arr[left].compareTo(pivotValue) <= 0 && left < right) {
                left++;
            }
            while (arr[right].compareTo(pivotValue) >= 0 && left < right) {
                right--;
            }
            if (arr[left].compareTo(arr[right]) >= 0) {
                swap(arr, left, right);
            }
        }
        return left;
    }

    @Override
    public T[] range(T leftKey, T rightKey, boolean leftIncluded, boolean rightIncluded) {
        int left = binarySearch(0, dim - 1, leftKey);
        int right = binarySearch(0, dim - 1, rightKey);

        if (left == -1 || right == -1) {
            return (T[]) new Object[0];
        }

        left = findLeftBoundary(left, leftIncluded, leftKey);
        right = findRightBoundary(right, rightIncluded, rightKey);

        if (left > right) {
            return (T[]) new Object[0];
        }

        int resultDim = right - left + 1;
        @SuppressWarnings("unchecked")
        T[] ans = (T[]) new Object[resultDim];

        for (int i = 0; i < resultDim; i++) {
            @SuppressWarnings("unchecked")
            T element = (T) array[left + i];
            ans[i] = element;
        }
        return ans;
    }

    private int findRightBoundary(int pos, boolean included, T key) {
        @SuppressWarnings("unchecked")
        T current = (T) array[pos];

        if (!included) {
            while (pos >= 0 && current.compareTo(key) == 0) {
                pos--;
                if (pos >= 0) current = (T) array[pos];
            }
            return pos;
        } else {
            while (pos < dim && current.compareTo(key) == 0) {
                pos++;
                if (pos < dim) current = (T) array[pos];
            }
            return pos - 1;
        }
    }

    private int findLeftBoundary(int pos, boolean included, T key) {
        @SuppressWarnings("unchecked")
        T current = (T) array[pos];

        if (included) {
            while (pos >= 0 && current.compareTo(key) == 0) {
                pos--;
                if (pos >= 0) current = (T) array[pos];
            }
            return pos + 1;
        } else {
            while (pos < dim && current.compareTo(key) == 0) {
                pos++;
                if (pos < dim) current = (T) array[pos];
            }
            return pos;
        }
    }

    // devuelve el máximo elemento del índice o null si no hay elementos
    @Override
    public T getMax(){
        return (T) array[dim-1];
    }

    // devuelve el mínimo elemento del índice o null si no hay elementos
    @Override
    public T getMin(){
        return (T) array[0];
    }

}