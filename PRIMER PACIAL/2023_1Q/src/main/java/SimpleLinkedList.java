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

    // insert resuelto todo en la clase SortedLinkedList, iterativo
    public boolean insert(T data) {
        if(root == null ){
            root=new Node(data,null);
            return true;
        }
        Node prev = null;
        Node current=root;
        while(current != null ){
            prev=current;
            current=current.next;
        }
        current = new Node(data,null);
        prev.next=current;
        return true;
    }
}