
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;


/* (INDEX OF QUE DEVUELVE LA PRIMERA APARCION)
 O(N*M) TEMPORAL
 O(1) ESPACIAL
 NAIVE
public static int indexOf(char[] query, char[] target)
{
    int idxTarget= 0;
    int idxQuery 0;
    while(idxTarget < target.length && idxQuery < query.length) {
       if (query[idxQuery] == target[idxTarget]) {
        idxQuery++;
        idxTarget++;
        if (idxQuery == query.length)
        return idxTarget-idxQuery;
        }
       else {
        idxTarget= idxTarget - idxQuery + 1; //esto es equivalente a sumarle unicamente 1 al idx target;
        idxQuery = 0;         //no encontre reinicializo el contador.
        }
        }
        return -1;
        }
 */

public class KMP {

    //O(N) ESPACIAL Y TEMPORAL

    /*
    NEXT: Armo un array de la longitud del Query. voy iterando por las subcadenas de la query(osea i++)
    y en estas subcadenas me fijo cual es el prefijo que es sufijo en ese substring (propios)y tomo la
    longitud del mas largo. NEXT[0] =0 En NEXT[i] va a estar la longitud del prefijo y
    sufijo mas largo del substring de longitud i+1 de la query.
    */


    private static int[] nextComputation1(char[] query) {
        int[] next = new int[query.length];

        int border=0;  // Length of the current border (LONGITUD PREFIJO Y SUFIJO IGUALES MAS LARGO)

        int rec=1;
        while(rec < query.length){
            if(query[rec]!=query[border]){
                if(border!=0)
                    border=next[border-1];
                else
                    next[rec++]=0;
            }
            else{
                border++;
                next[rec]=border;
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


    //O(N + M) // (EL M ES DEL LPS, EL N DE RECORRER EL ARRAY)
    //O(M) ESPACIAL (ARRAY LPS)

    public static int indexOf( char[] query, char[] target) {
        int[] lps = nextComputation1(query);
        int i = 0, j = 0;
        while (i < target.length) {
            if (query[j] == target[i]) {
                j++;
                i++;
            } else {
                if (j != 0) {
                    j = lps[j-1];
                } else {
                    i++;
                }
            }

            if (j == query.length) {
                return i - j;
            }
        }

        return -1;

    }


    public static void main(String[] args) {




        String query, text;
        int pos;

        // debe dar 3
        query= "ABXABU";
        text= "ABXABXABUF";

        pos= indexOf(query.toCharArray(), text.toCharArray());
        System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));


        // debe dar 5
        query= "ABAB";
        text= "SABASABABA";
        pos= indexOf(query.toCharArray(), text.toCharArray());
        System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));


        // debe dar 0
        query= "ABAB";
        text= "ABABYYYY";
        pos= indexOf(query.toCharArray(), text.toCharArray());
        System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));


        // debe dar -1
        query= "ABAB";
        text= "ABAYYYA";
        pos= indexOf(query.toCharArray(), text.toCharArray());
        System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));
    }
}