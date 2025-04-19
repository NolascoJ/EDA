package ar.edu.itba.eda;

import java.util.*;

public class QGgram {
    private TreeMap<String, Integer> substr = new TreeMap<>();
    private int n;

    public QGgram(int n) {
        this.n = n;
    }

    public void printTokens(String str) {
        mapStr(str);
        for (String key : substr.keySet()) {
            System.out.println("%s , %d".formatted(key, substr.get(key)));
        }
    }

    public void mapStr(String str) {
        if (str.isEmpty()) {
            return;
        }

        for (int i = 0; i < str.length() + n - 1; i++) {
            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < n; j++) {
                int pos = i + j;
                if (pos < n - 1) {
                    gram.append("#");
                } else if (pos >= str.length() + n - 1) {
                    gram.append("#");
                } else if (pos - (n - 1) >= str.length()) {
                    gram.append("#");
                } else {

                    gram.append(str.charAt(pos - (n - 1)));
                }
            }

            String gramStr = gram.toString();
            substr.put(gramStr, substr.getOrDefault(gramStr, 0) + 1);
        }
    }
}