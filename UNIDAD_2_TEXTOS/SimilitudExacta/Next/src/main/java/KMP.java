
public class KMP {

    private static int[] nextComputation1(char[] query) {
        int[] next = new int[query.length];

        int border = 0;  // Length of the current border

        int rec = 1;
        while (rec < query.length) {
            if (query[rec] != query[border]) {
                if (border != 0)
                    border = next[border - 1];
                else
                    next[rec++] = 0;
            } else {
                border++;
                next[rec] = border;
                rec++;
            }
        }
        return next;
    }


    private static int[] nextComputation2(char[] query) {
        int[] next = new int[query.length];
        next[0] = 0;     // Always. There's no proper border.
        int border = 0;  // Length of the current border
        for (int rec = 1; rec < query.length; rec++) {
            while ((border > 0) && (query[border] != query[rec]))
                border = next[border - 1];     // Improving previous computation
            if (query[border] == query[rec])
                border++;
            // else border = 0;  // redundant
            next[rec] = border;
        }
        return next;
    }

    public static int indexOf(char[] query, char[] target) {
        if (query.length > target.length) {
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

    public static void main(String[] args) {
        String query, text;
        int pos;

        // debe dar 3
        query = "ABXABU";
        text = "ABXABXABUF";

        pos = KMP.indexOf(query.toCharArray(), text.toCharArray());
        System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));


        // debe dar 5
        query = "ABAB";
        text = "SABASABABA";
        pos = KMP.indexOf(query.toCharArray(), text.toCharArray());
        System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));


        // debe dar 0
        query = "ABAB";
        text = "ABABYYYY";
        pos = KMP.indexOf(query.toCharArray(), text.toCharArray());
        System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));


        // debe dar -1
        query = "ABAB";
        text = "ABAYYYA";
        pos = KMP.indexOf(query.toCharArray(), text.toCharArray());
        System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));
    }
}


