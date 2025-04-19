public class Main {
    public static void main(String[] args) {
        testIntegerOperations();
        testStringOperations();
        testEmptyIndex();
        testEdgeCases();
        testCustomType();
        testInitializeWithNull();
        testArrayExpansion();

        System.out.println("¡Todas las pruebas completadas!");
    }

    public static void testIntegerOperations() {
        System.out.println("\n=== Probando operaciones con Integer ===");
        IndexGenerics<Integer> index = new IndexGenerics<>();

        // Test initialize y search
        Integer[] nums = {5, 2, 8, 1, 3};
        index.initialize(nums);
        System.out.println("Buscar 5 (true): " + index.search(5));
        System.out.println("Buscar 2 (true): " + index.search(2));
        System.out.println("Buscar 10 (false): " + index.search(10));

        // Test insert
        index.insert(4);
        System.out.println("Insertar 4 y buscar (true): " + index.search(4));
        index.insert(8); // duplicado
        System.out.println("Ocurrencias de 8 (2): " + index.occurrences(8));

        // Test delete
        index.delete(2);
        System.out.println("Eliminar 2 y buscar (false): " + index.search(2));

        // Test occurrences
        System.out.println("Ocurrencias de 5 (1): " + index.occurrences(5));
        index.insert(5);
        System.out.println("Insertar otro 5 y ocurrencias (2): " + index.occurrences(5));

        // Test range
        Integer[] range = index.range(3, 5, true, true);
        System.out.print("Rango [3-5] inclusive: ");
        printArray(range);
    }

    public static void testStringOperations() {
        System.out.println("\n=== Probando operaciones con String ===");
        IndexGenerics<String> index = new IndexGenerics<>();

        String[] words = {"banana", "apple", "orange", "grape"};
        index.initialize(words);
        System.out.println("Buscar 'apple' (true): " + index.search("apple"));
        System.out.println("Buscar 'pear' (false): " + index.search("pear"));

        index.insert("pear");
        System.out.println("Insertar 'pear' y buscar (true): " + index.search("pear"));

        index.delete("grape");
        System.out.println("Eliminar 'grape' y buscar (false): " + index.search("grape"));

        index.insert("apple");
        System.out.println("Insertar otro 'apple' y ocurrencias (2): " + index.occurrences("apple"));

        String[] range = index.range("apple", "orange", true, true);
        System.out.print("Rango ['apple'-'orange'] inclusive: ");
        printArray(range);
    }

    public static void testEmptyIndex() {
        System.out.println("\n=== Probando índice vacío ===");
        IndexGenerics<Integer> index = new IndexGenerics<>();

        System.out.println("Buscar en vacío (false): " + index.search(1));
        System.out.println("Ocurrencias en vacío (0): " + index.occurrences(1));
        System.out.print("Rango en vacío ([]): ");
        printArray(index.range(1, 5, true, true));

        index.insert(10);
        System.out.println("Insertar en vacío y buscar (true): " + index.search(10));
    }

    public static void testEdgeCases() {
        System.out.println("\n=== Probando casos límite ===");
        IndexGenerics<Integer> index = new IndexGenerics<>();
        Integer[] nums = {1, 3, 3, 3, 5, 7};
        index.initialize(nums);

        System.out.println("Ocurrencias de 3 (3): " + index.occurrences(3));

        System.out.print("Rango [3-5] inclusive: ");
        printArray(index.range(3, 5, true, true));
        System.out.print("Rango ]3-5] inclusive: ");
        printArray(index.range(3, 5, false, true));
        System.out.print("Rango [3-5[ exclusive: ");
        printArray(index.range(3, 5, true, false));

        index.delete(10);
        System.out.println("Eliminar elemento no existente, total elementos (6): " +
                (index.occurrences(1) + index.occurrences(3) + index.occurrences(5) + index.occurrences(7)));
    }

    public static void testCustomType() {
        System.out.println("\n=== Probando tipo personalizado ===");
        class Person implements Comparable<Person> {
            String name;
            int age;

            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public int compareTo(Person other) {
                return this.name.compareTo(other.name);
            }

            @Override
            public String toString() {
                return name + "(" + age + ")";
            }
        }

        IndexGenerics<Person> index = new IndexGenerics<>();
        Person[] people = {
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 20)
        };
        index.initialize(people);

        System.out.println("Buscar a Bob (true): " + index.search(new Person("Bob", 0)));

        index.insert(new Person("David", 40));
        System.out.println("Insertar a David y buscar (true): " + index.search(new Person("David", 0)));

        index.delete(new Person("Alice", 0));
        System.out.println("Eliminar a Alice y buscar (false): " + index.search(new Person("Alice", 0)));

        System.out.print("Todos los elementos: ");
        Person[] all = index.range(new Person("A", 0), new Person("Z", 0), true, true);
        printArray(all);
    }

    public static void testInitializeWithNull() {
        System.out.println("\n=== Probando inicialización con null ===");
        IndexGenerics<Integer> index = new IndexGenerics<>();
        try {
            index.initialize(null);
            System.out.println("ERROR: No lanzó excepción");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: Lanzó excepción como se esperaba: " + e.getMessage());
        }
    }

    public static void testArrayExpansion() {
        System.out.println("\n=== Probando expansión del array ===");
        IndexGenerics<Integer> index = new IndexGenerics<>();
        for (int i = 0; i < 25; i++) {
            index.insert(i);
        }
        System.out.println("Insertados 25 elementos, buscando algunos:");
        System.out.println("Buscar 0 (true): " + index.search(0));
        System.out.println("Buscar 10 (true): " + index.search(10));
        System.out.println("Buscar 24 (true): " + index.search(24));
    }

    // Método auxiliar para imprimir arrays
    public static <T> void printArray(T[] array) {
        if (array == null || array.length == 0) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}