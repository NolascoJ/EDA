public class ProximityIndex {
    private String[] elements;
    private int size = 0;

    public void initialize(String[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("elements no puede ser null");
        }

        for(int rec= 0; rec < elements.length-1; rec++) {
            if (elements[rec].compareTo(elements[rec+1]) >= 0)
                throw new IllegalArgumentException("hay repetidos o no está ordenado");
        }

        this.elements = elements;
        this.size = elements.length;

    }





    public String search(String element, int distance) {
        int dim = size;
        int iter=-1;

        distance = distance % size;
        boolean found = false;

        for(int i=0 ; i<size && !found ; i++){
            if(elements[i] == element) {
                found = true;
                iter = i;
            }
        }
        if(iter == -1){
            return null;
        }
        if(distance < 0){
            distance=distance+size;
        }
        iter = (distance + iter) % size;
        return elements[iter];
    }

    public static void main(String[] args) {
        ProximityIndex test = new ProximityIndex();
        String elements[] = {"Ana" , "Carlos" , "Juan" , "Yolanda"};
        test.initialize(elements);
        System.out.println(test.search("Carlos",2));
        System.out.println(test.search("Carlos",0));
        System.out.println(test.search("Carlos",3));
        System.out.println(test.search("Ana",14));
        System.out.println(test.search("Ana",-2));
        System.out.println(test.search("Ana",-17));
        System.out.println(test.search("Juan",-4));
    }
}