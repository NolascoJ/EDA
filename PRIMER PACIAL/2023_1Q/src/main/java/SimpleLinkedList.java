public class SimpleLinkedList <T>{
    private Node root = null;

    public void dump() {
        Node current = root;

        while (current!=null ) {
            // avanzo
            System.out.println(current.data);
            current= current.next;
        }
    }

    private final class Node {
        private T data;
        private Node next;

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}