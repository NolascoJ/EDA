
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexWithDuplicatesTest {
    @Test
    void testCase1() {
        IndexWithDuplicates index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 3, 5, 7});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 6, 8});
        index1.merge(index2);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
        assertArrayEquals(expected, index1.getIndexedData());
    }

    @Test
    void testCase2() {
        IndexWithDuplicates index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 1, 3, 5, 7});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 4, 6, 8});
        index1.merge(index2);
        int[] expected = {1, 1,2,3,4,4,5,6,7,8};
        assertArrayEquals(expected, index1.getIndexedData());
    }

    @Test
    void testCase3() {
        IndexWithDuplicates index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 3, 5});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 6, 8, 10});
        index1.merge(index2);
        int[] expected =  {1, 2, 3, 4, 5, 6, 8, 10};
        assertArrayEquals(expected, index1.getIndexedData());

    }

}