
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProximityIndexTests {


    @Test
    void testCase1(){
        ProximityIndex test = new ProximityIndex();
        String elements[] = {"Ana" , "Carlos" , "Juan" , "Yolanda"};
        test.initialize(elements);
        String expected = test.search("Carlos",2);
        assertEquals("Yolanda",expected);
    }
    @Test
    void testCase2(){
        ProximityIndex test = new ProximityIndex();
        String elements[] = {"Ana" , "Carlos" , "Juan" , "Yolanda"};
        test.initialize(elements);
        String expected = test.search("Carlos",0);
        assertEquals("Carlos",expected);
    }
    @Test
    void testCase3(){
        ProximityIndex test = new ProximityIndex();
        String elements[] = {"Ana" , "Carlos" , "Juan" , "Yolanda"};
        test.initialize(elements);
        String expected = test.search("Carlos",3);
        assertEquals("Ana",expected);
    }
    @Test
    void testCase4(){
        ProximityIndex test = new ProximityIndex();
        String elements[] = {"Ana" , "Carlos" , "Juan" , "Yolanda"};
        test.initialize(elements);
        String expected = test.search("Ana",14);
        assertEquals("Juan",expected);
    }
    @Test
    void testCase5(){
        ProximityIndex test = new ProximityIndex();
        String elements[] = {"Ana" , "Carlos" , "Juan" , "Yolanda"};
        test.initialize(elements);
        String expected = test.search("Ana",-2);
        assertEquals("Juan",expected);
    }

    @Test
    void testCase6(){
        ProximityIndex test = new ProximityIndex();
        String elements[] = {"Ana" , "Carlos" , "Juan" , "Yolanda"};
        test.initialize(elements);
        String expected = test.search("Ana",-17);
        assertEquals("Yolanda",expected);
    }

    @Test
    void testCase7(){
        ProximityIndex test = new ProximityIndex();
        String elements[] = {"Ana" , "Carlos" , "Juan" , "Yolanda"};
        test.initialize(elements);
        String expected = test.search("Juan",-4);
        assertEquals("Juan",expected);
    }

    @Test
    void testCase8(){
        ProximityIndex test = new ProximityIndex();
        String elements[] = {"Ana" , "Carlos" , "Juan" , "Yolanda"};
        test.initialize(elements);
        String expected = test.search("XXX",-2);
        assertEquals(null,expected);
    }




}