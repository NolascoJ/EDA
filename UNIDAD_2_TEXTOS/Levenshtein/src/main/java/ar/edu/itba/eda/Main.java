package ar.edu.itba.eda;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int dist= levenshtein("big data", "bigdaa");
        int dist2= effLev("big data", "bigdaa");
        System.out.println(dist);
        System.out.println(dist2);
        System.out.println(normalizeLevenshtein("big data", "bigdaa"));


        }


        //TIME , SPACE : O(N*M)
    public static int levenshtein(String str1 , String str2){
        int [][] mapping = new int[str1.length()+1][str2.length()+1];
        char[] s1=str1.toCharArray();
        char[] s2=str2.toCharArray();

        for(int i=0 ; i<=str1.length() ; i++){
            mapping[i][0] = i;
        }
        for(int j=0 ; j<=str2.length() ; j++){
            mapping[0][j] = j;
        }

        for(int i = 1 ; i<=str1.length() ; i++){
            for(int j=1 ; j<=str2.length() ; j++){
                mapping[i][j] = minimum(
                        mapping[i][j-1]+1, //insercion
                        mapping[i-1][j]+1, //eliminacion
                        diagonalCheck(s1[i-1],s2[j-1],mapping[i-1][j-1])); //sustitucion
            }
        }
        return mapping[str1.length()][str2.length()];
    }

    public static double normalizeLevenshtein(String str1 , String str2){
        int lv = levenshtein(str1,str2);
        double max = Math.max(str1.length(),str2.length());

        return 1-( lv /max);
    }

    public static int diagonalCheck(char top , char left , int diag){
        return diag + (top == left ? 0 : 1);
    }

    public static int minimum(int left,int top , int diag){
        return Math.min(left, Math.min(top,diag));
    }


    // TIME = O(N*M)
    //SPACE = O(min(N,M))
    public static int effLev(String str1 , String str2){
        int len1=str1.length();
        int len2=str2.length();

        if(len1 > len2){
            return effLev(str2,str1);
        }

        int [] prevRow = new int[len1+1];
        int [] currRow = new int[len1+1];

        for(int i=0 ; i<=len1 ; i++){
            prevRow[i]=i;
        }

        for(int i=1 ; i<=len2 ; i++){
            currRow[0]=i;
            for(int j=1 ; j<=len1 ; j++){
                currRow[j] = minimum(currRow[j-1] +1,
                                        prevRow[j] +1,
                        diagonalCheck(str1.charAt(j-1),str2.charAt(i-1),prevRow[j-1]));
            }
            int [] temp = prevRow;
            prevRow=currRow;  //SINO AMBOS APUNTARIAN A LA MISMA REFERENCIA
            currRow=temp ;
        }
        return prevRow[len1];
    }


}



