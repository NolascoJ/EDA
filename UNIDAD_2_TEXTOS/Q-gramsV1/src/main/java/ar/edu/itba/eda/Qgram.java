package ar.edu.itba.eda;

import java.util.*;

public class Qgram {

    private int q;

    public Qgram(int q){
        this.q = q;
    }

    // TIME : O(N*Q) //iteras N*Q veces , con N=
    //SPACE : O(N) //el mapita.

    public Map<String,Integer> generateQGrams(String text) {
        Map<String, Integer> qGrams = new HashMap<>();
        text = "#".repeat(q - 1) + text + "#".repeat(q - 1); //LE AGREGO LOS # DE ANTEMANO
        for (int i = 0; i <= text.length() - q; i++) {
            qGrams.merge(text.substring(i, i + q), 1, Integer::sum); //FGFUARDA CADA QGRAMA EN EL MAPA. SI NO ESTABA PONE CONTADOR 1.
        }                                                                  //SI YA ESTABA LE SUMA 1 A LA CANTIDAD DE APARICIONES
        return qGrams;
    }

    public void printTokens(String text) {
        Map<String,Integer> qGrams = generateQGrams(text);
        for (String qGram : qGrams.keySet()) {
            for (int i = 0; i < qGrams.get(qGram); i++) {
                System.out.println(qGram);
            }
        }
    }

    public double similarity(String q1, String q2) {
        q1 = q1.toLowerCase();
        q2 = q2.toLowerCase();
        Map<String, Integer> qGrams1 = generateQGrams(q1);
        Map<String, Integer> qGrams2 = generateQGrams(q2);
        double length = getLength(qGrams1) + getLength(qGrams2);
        int remove = 0;
        double count = 0;
        Iterator<Map.Entry<String, Integer>> iterator = qGrams1.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String qgram = entry.getKey();
            if (qGrams2.containsKey(qgram) && qGrams2.get(qgram) > 0) {
                remove++;
                if (entry.getValue() > 1) {
                    qGrams1.merge(qgram, 1, (oldValue, value) -> oldValue - 1);
                    qGrams2.merge(qgram, 1, (oldValue, value) -> oldValue - 1);
                } else {
                    iterator.remove();
                    qGrams2.remove(qgram);
                }
            }
        }

        System.out.println("total length= "+length);
        System.out.println("no comun= "+ (length-2*remove));

        return (length - (length - 2*remove)) / length;
    }
    public double getLength(Map<String,Integer> qGrams) {
        double lentgh = 0;
        for (String qgram : qGrams.keySet()) {
            lentgh += qGrams.get(qgram);
        }
        return lentgh;
    }

    public static void main(String[] args) {
        Qgram g = new Qgram(2);
        g.printTokens("salesal");
        System.out.println();
        g.printTokens("alale");
        System.out.println();

        double sim = g.similarity("salesal", "alale");
        System.out.println(sim);

        // #salesal# largo 8
        // #alale# largo 7
        //{#s, sa, al, le, es, sa, al, l#} largo 8
        // {#a, al, la, al, le, e#} largo 6
        // comun = {al, le} largo 2
        // no comun = 8-2 + 6-2 = 10
        // (8+6-(8+6-4))/14 = 4/14 = 2/7 = 0.2857142857142857

        //EL CALUCLO ES:
        /*
            CANTIDAD DE QGRAMAS S1 = a
            CANTIDAD DE QGRAMAS S2 = b
            CANTIDAD DE QGRAMAS EN COMUN = c
            CANTIDAD DE QGRAMAS DISTINTOS = (a-c) + (b-c) = d;

            SIMILARIDAD = (A+B - d ) / (a+b)
         */

    }

}