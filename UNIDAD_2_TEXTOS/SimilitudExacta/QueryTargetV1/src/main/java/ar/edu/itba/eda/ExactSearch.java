package ar.edu.itba.eda;


public class ExactSearch {
    public static void main(String[] args) {
        char[] target = "abracadabra".toCharArray();
        char[] query = "ra".toCharArray();
        System.out.println(ExactSearch.indexOf(query, target)); // 2

        target = "abracadabra".toCharArray();
        query = "abra".toCharArray();
        System.out.println(ExactSearch.indexOf(query, target)); // 0

        target = "abracadabra".toCharArray();
        query = "aba".toCharArray();
        System.out.println(ExactSearch.indexOf(query, target)); // -1

        target = "ab".toCharArray();
        query = "aba".toCharArray();
        System.out.println(ExactSearch.indexOf(query, target)); // -1

        target = "xa".toCharArray();
        query = "aba".toCharArray();
        System.out.println(ExactSearch.indexOf(query, target)); // -1

        target = "abracadabras".toCharArray();
        query = "abras".toCharArray();
        System.out.println(ExactSearch.indexOf(query, target)); // 7
    }

    public static int indexOf(char[] query, char[] target) {
        if(query.length > target.length){
            return -1;
        }
        int counter = 0;
        for (int i = 0; i < target.length; i++, counter = 0) {
            if (target[i] == query[0]) {
                counter++;
                for (int j = i + 1, k = 1; j < target.length && k < query.length; j++, k++) {
                    if (target[j] == query[k]) {
                        counter++;
                    }
                }
            }
            if (counter == query.length) {
                return i;
            }
        }
        return -1;
    }
}


