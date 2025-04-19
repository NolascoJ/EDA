import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

// copiado del drive de Nash
public class SortedLinkedListWithHeader<T extends Comparable<? super T>> implements SortedListService<T> {
    private final Header header;

    private final class Header {
        private Node first;
        private Node last;
        private int size;
        public Header(){
            first = last = null;
            size = 0;
        }
    }

    private final class Node {
        private T data;
        private Node next;
        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
        private Node insert(T data, boolean[] rta) {
            if(data == null){
                throw new IllegalArgumentException("Data is null");
            }
            if (this.data.compareTo(data) == 0) { //SI YA EXISTE EL NODO PINCHE
                System.err.printf("Insertion failed %s%n", data);
                rta[0] = false;
                return this;
            }
            if (this.data.compareTo(data) < 0) {
                if (next == null) {
                    rta[0] = true;
                    next = new Node(data, null);
                    return this; //SI MI ACTUAL ES MENOR AL QUE QUIERO PONER Y NO HAY SIGUIENTE INSERTO.
                }
                next = next.insert(data, rta); //LLAMO AL PROXIMO NODO PARA QUE VEA SI SE INSERTA.
                return this; //RETORNO CADA NODO ASI SE ENCADENA.
            }
            rta[0] = true;
            return new Node(data, this); //si mi nodo es mayor, inserto antes de este.
        }

        private Node remove(T data, boolean[] rta) {
            Node current = this;
            Node prev = null;
            if(data == null){
                rta[0] = false;
                return null;
            }
            if(data.compareTo(this.data) == 0){
                rta[0] = true;    //CASO QUE JUSTO ME PARE EN EL QUE QUIERO ELIMINAR.
                return this.next; //SI LO ENCONTRE, DEVUELVO EL PROXIMO ASI ENCADENO DIRECTO CON EL NEXT DEL ELIMINADO.
            }
            while(data.compareTo(current.data) > 0){
                prev = current;   //VOY AVANZANDO HASTA ENCONTRAR EL QUE ME SIRVE
                current = current.next;
            }
            if(data.compareTo(current.data) == 0){ //CUANDO LO ENCUENTRO CAMBIO EL NEXT DEL ANTERIOR, Y SALTEO EL CURREN
                prev.next = current.next;          // QUE ES EL VALOR A ELIMINAR.
                rta[0] = true;
            }
            return this;  //DEVUELVO EL NODO A ELIMINAR, OSEA EN EL QUE ESTOY PARAOD.
        }
    }

    public SortedLinkedListWithHeader() {
        header = new Header();
    }

    @Override
    public boolean insert(T data) {
        if(data == null){
            throw new IllegalArgumentException("Data is provided is null");
        }
        Node prev = null;
        Node current = header.first;
        int c;
        while(current != null && (c = data.compareTo(current.data)) >= 0) {
            if (c == 0) {
                return false;
            }
            prev = current;
            current = current.next;
        }
        Node newNode = new Node(data, current);
        if(current == header.first){
            header.first = newNode;
        }
        else {
            prev.next = newNode;
        }
        if(current == header.last){
            header.last = newNode;
        }
        header.size++;
        return true;
    }

    public boolean insert2(T data) {
        if (data == null)
            throw new IllegalArgumentException("data cannot be null");

        boolean[] rta = new boolean[1];
        header.first = insertRec(data, header.first, rta);
        if(rta[0]){
            header.size++;
        }
        return rta[0];
    }

    public Node insertRec(T data, Node current, boolean[] rta) {
        if (current != null && current.data.compareTo(data) == 0) {
            System.err.println(String.format("Insertion failed. %s repeated", data));
            rta[0] = false;
            return current;
        }
        if (current != null && current.data.compareTo(data) < 0) {
            current.next = insertRec(data, current.next, rta);
            return current;
        }
        rta[0] = true;
        return new Node(data, current);
    }

    public boolean insert3(T data) {
        if (data == null)
            throw new IllegalArgumentException("data cannot be null");

        if (header.first == null) {
            header.first = new Node(data, null);
            return true;
        }

        boolean[] rta = {false};
        header.first = header.first.insert(data, rta);

        if(rta[0]){
            header.size++;
        }
        return rta[0];
    }

    @Override
    public boolean remove(T data) {
        Node prev = null;
        Node current = header.first;
        while (current != null && current.data.compareTo(data) < 0) {
            prev = current;
            current = current.next;
        }
        if (current != null && current.data.compareTo(data) == 0) {
            if (current == header.first)
                header.first = header.first.next;
            else
                prev.next = current.next;
            header.size--;
            return true;
        }
        return false;
    }

    // delete resuelto toodo en la clase SortedLinkedList, recursivo
    //	@Override
    public boolean remove2(T data) {
        if (data == null) {
            return false;
        }
        boolean[] ans = {false};
        header.first = removeRec(data, header.first, ans);
        if(ans[0]){
            header.size--;
        }
        return ans[0];
    }

    private Node removeRec(T data, Node current, boolean[] rta) {
        if (current == null) {
            return null;
        }
        int c;
        if ((c = data.compareTo(current.data)) == 0) {
            rta[0] = true;
            return current.next;
        }
        if (c > 0) {
            current.next = removeRec(data, current.next, rta);
        }
        return current;
    }

    // delete resuelto delegando al nodo
    public boolean remove3(T data) {
        if(header.first == null){
            return false;
        }
        boolean[] ans = {false};
        header.first = header.first.remove(data, ans);
        if(ans[0]){
            header.size--;
        }
        return ans[0];
    }

    @Override
    public boolean isEmpty() {
        return header.size == 0;
    }

    @Override
    public int size() {
        return header.size;
    }

    @Override
    public T getMin() {
        return header.first.data;
    }

    @Override
    public T getMax() {
        return header.last.data;
    }

    @Override
    public void dump() {
        Node current = header.first;

        while (current != null) {
            // avanzo
            System.out.println(current.data);
            current = current.next;
        }
    }

    @Override
    public boolean find(T data) {
        return getPos(data) != -1;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SortedLinkedListWithHeader<?>))
            return false;

        @SuppressWarnings("unchecked")
        SortedLinkedListWithHeader<T> auxi = (SortedLinkedListWithHeader<T>) other;

        Node current = header.first;
        Node currentOther = auxi.header.first;
        while (current != null && currentOther != null) {
            if (current.data.compareTo(currentOther.data) != 0)
                return false;

            // por ahora si, avanzo ambas
            current = current.next;
            currentOther = currentOther.next;
        }

        return current == null && currentOther == null;

    }

    // -1 si no lo encontro
    protected int getPos(T data) {
        Node current = header.first;
        int pos = 0;

        while (current != null) {
            if (current.data.compareTo(data) == 0)
                return pos;

            // avanzo
            current = current.next;
            pos++;
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new SortedLinkedListIterator();
    }

    private class SortedLinkedListIterator implements Iterator<T> {
        private Node current = header.first;
        private boolean canRemove;
        private Node prev = null;
        private Node antPrev = null;


        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T aux = current.data;
            canRemove = true;
            antPrev = prev;
            prev = current;
            current=current.next;
            return aux;

        }

        //SOLO SE PUEDE INVOCAR DSP DE UN NEXT;
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Debe llamar a next() antes de remove()");
            }
            if(prev == header.first){ //si quiero eliminar la root, el current es mi neyuva root.
                header.first=current;
            }
            if(antPrev != null) {
                antPrev.next = current;
            }
            header.size--;               // Actualiza el tamaño de la lista (si tienes un contador)
            canRemove = false;    // Evita múltiples llamadas a remove() sin next()
        }
    }

    public static void main(String[] args) {
        System.out.println("=== TEST SortedLinkedListWithHeader ===");

        // --------------------------------------------
        // Test 1: Inserción básica y ordenamiento
        // --------------------------------------------
        SortedLinkedListWithHeader<Integer> list = new SortedLinkedListWithHeader<>();
        System.out.println("\n--- Test 1: Insertar 3, 1, 2 ---");
        list.insert(3);
        list.insert(1);
        list.insert(2);
        list.dump();  // Debe imprimir: 1, 2, 3
        System.out.println("Size: " + list.size());  // Debe ser 3
        System.out.println("Min: " + list.getMin()); // 1
        System.out.println("Max: " + list.getMax()); // 3

        // --------------------------------------------
        // Test 2: Inserción de duplicados
        // --------------------------------------------
        System.out.println("\n--- Test 2: Insertar duplicado (2) ---");
        boolean inserted = list.insert(2);
        System.out.println("Insertado?: " + inserted);  // false
        list.dump();  // Lista sigue igual: 1, 2, 3

        // --------------------------------------------
        // Test 3: Eliminación
        // --------------------------------------------
        System.out.println("\n--- Test 3: Eliminar 2 ---");
        boolean removed = list.remove(2);
        System.out.println("Eliminado?: " + removed);  // true
        list.dump();  // 1, 3
        System.out.println("Size: " + list.size());  // 2

        // --------------------------------------------
        // Test 4: Eliminar primer y último elemento
        // --------------------------------------------
        System.out.println("\n--- Test 4: Eliminar primero (1) y último (3) ---");
        list.remove(1);
        list.dump();  // 3
        System.out.println("Min y Max deben ser 3: " + list.getMin() + ", " + list.getMax());
        list.remove(3);
        System.out.println("Lista vacía?: " + list.isEmpty());  // true
        System.out.println("Size: " + list.size());  // 0

        // --------------------------------------------
        // Test 5: Inserción después de vaciar
        // --------------------------------------------
        System.out.println("\n--- Test 5: Insertar en lista vacía (5) ---");
        list.insert(5);
        list.dump();  // 5
        System.out.println("Min y Max: " + list.getMin() + ", " + list.getMax());

        // --------------------------------------------
        // Test 6: Iterador y remove()
        // --------------------------------------------
        System.out.println("\n--- Test 6: Iterador y remove() ---");
        list.insert(10);
        list.insert(7);
        list.dump();  // 5, 7, 10

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int num = it.next();
            System.out.println("Elemento actual: " + num);
            if (num == 7) {
                it.remove();  // Elimina 7
                System.out.println("Eliminado 7");
            }
        }
        list.dump();  // 5, 10
        System.out.println("Size post-remove: " + list.size());  // 2

        // --------------------------------------------
        // Test 7: Casos límite del iterador
        // --------------------------------------------
        System.out.println("\n--- Test 7: Casos límite del iterador ---");
        try {
            it.remove();  // Debe lanzar IllegalStateException
        } catch (IllegalStateException e) {
            System.out.println("Excepción al llamar remove() sin next(): " + e.getMessage());
        }

        it = list.iterator();
        try {
            while (true) {
                it.next();  // Debe lanzar NoSuchElementException al final
            }
        } catch (NoSuchElementException e) {
            System.out.println("Excepción al llamar next() sin elementos: " + e.getMessage());
        }

        // --------------------------------------------
        // Test 8: Inserción de null (manejo de errores)
        // --------------------------------------------
        System.out.println("\n--- Test 8: Insertar null ---");
        try {
            list.insert(null);  // Debe lanzar IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción al insertar null: " + e.getMessage());
        }

        // --------------------------------------------
        // Test 9: Eliminar elemento inexistente
        // --------------------------------------------
        System.out.println("\n--- Test 9: Eliminar 99 (inexistente) ---");
        removed = list.remove(99);
        System.out.println("Eliminado?: " + removed);  // false

        // --------------------------------------------
        // Test 10: Equals
        // --------------------------------------------
        System.out.println("\n--- Test 10: Equals ---");
        SortedLinkedListWithHeader<Integer> otherList = new SortedLinkedListWithHeader<>();
        otherList.insert(5);
        otherList.insert(10);
        System.out.println("Lists iguales?: " + list.equals(otherList));  // true

        otherList.insert(15);
        System.out.println("Lists iguales después de insertar 15?: " + list.equals(otherList));  // false
    }

}
