public class Main {

    public static void main(String[] args) {
        IndexService myIndex = new IndexWithDuplicates();
        System.out.println(myIndex.occurrences(10)); // se obtiene 0
        myIndex.delete(10); // ignora
        System.out.println(myIndex.search(10)); // se obtiene false
        myIndex.insert(80); // almacena [80]
        myIndex.insert(20); // almacena [20, 80]
        myIndex.insert(80); // almacena [20, 80, 80]
        try {
            myIndex.initialize(new int[]{100, 50, 30, 50, 80, 100, 100, 30});
        } catch (Exception e) {
        }

// el índice posee [30, 30, 50, 50, 80, 100, 100, 100]
        System.out.println("aaa");
        System.out.println(myIndex.search(20)); // se obtiene false
        System.out.println(myIndex.search(80)); // se obtiene true
        System.out.println(myIndex.occurrences(50)); // se obtiene 2
        System.out.println(myIndex.occurrences(80)); // se obtiene 1
        System.out.println(myIndex.occurrences(30)); // se obtiene 2
        System.out.println(myIndex.occurrences(100)); // se obtiene 3
        myIndex.delete(50);
        System.out.println(myIndex.occurrences(50)); // se obtiene 1

        System.out.println("\nTesting range():");

        // Test 1: Full inclusive range (30 to 100)
        System.out.println("\nTest 1: [30, 100], inclusive");
        printArray(myIndex.range(30, 100, true, true));
        // Expected: [30, 30, 50, 80, 100, 100, 100]

        // Test 2: Exclusive bounds (30 to 100)
        System.out.println("\nTest 2: (30, 100), exclusive");
        printArray(myIndex.range(30, 100, false, false));
        // Expected: [50, 80]

        // Test 3: Left inclusive, right exclusive [30, 100)
        System.out.println("\nTest 3: [30, 100), leftIncl, rightExcl");
        printArray(myIndex.range(30, 100, true, false));
        // Expected: [30, 30, 50, 80]

        // Test 4: Left exclusive, right inclusive (30, 100]
        System.out.println("\nTest 4: (30, 100], leftExcl, rightIncl");
        printArray(myIndex.range(30, 100, false, true));
        // Expected: [50, 80, 100, 100, 100]

        // Test 5: Range outside array bounds
        System.out.println("\nTest 5: [10, 20] (no matches)");
        printArray(myIndex.range(10, 20, true, true));
        // Expected: []

        // Test 6: Single-element range [50, 50], inclusive
        System.out.println("\nTest 6: [50, 50], inclusive");
        printArray(myIndex.range(50, 50, true, true));
        // Expected: [50]

        // Test 7: Single-element range (50, 50), exclusive (empty)
        System.out.println("\nTest 7: (50, 50), exclusive");
        printArray(myIndex.range(50, 50, false, false));
        // Expected: []

        // Test 8: Range with no elements between bounds [80, 100), leftIncl, rightExcl
        System.out.println("\nTest 8: [80, 100), leftIncl, rightExcl");
        printArray(myIndex.range(80, 100, true, false));
        // Expected: [80]
    }

    // Helper method to print arrays
    private static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}


