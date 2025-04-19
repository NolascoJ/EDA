public class BoundedQueue<T> {
        private T[] elements;
        private int first;
        private int last;
        private int qty= 0;

        public BoundedQueue(int limit) { // TODO
            elements =(T[]) new Object[limit];
            this.first=0;
            this.last=0;
        }
        public boolean isEmpty() {
            return qty==0;
        }
        public boolean isFull() {
            return qty==elements.length;
        }
        public void enqueue(T element) { // TODO
            if(isFull()){
                throw new IllegalArgumentException("SE TE LLENO PERRO");
            }
            elements[qty++] = element;
            last=qty;
        }
        public T dequeue() { // TODO
            if(qty == 0){
                throw new IllegalArgumentException("No elements to deque");
            }
            T toReturn = elements[qty-1];
            elements[qty--]=null;
            return toReturn;
        }

        private void dump() { // TODO
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] != null) {
                    System.out.println(elements[i]);
                }
            }
        }



    public static void main(String[] args) {
        BoundedQueue<Integer> myQueue = new BoundedQueue<>(10);
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(30);
        myQueue.enqueue(40);

        System.out.println(myQueue.dequeue() );
        myQueue.dump();
        System.out.println(myQueue.dequeue() );
        myQueue.enqueue(50);
        myQueue.enqueue(60);
        myQueue.enqueue(70);
        System.out.println("\nquedaron 5 elementos");
        myQueue.dump();
    }
}