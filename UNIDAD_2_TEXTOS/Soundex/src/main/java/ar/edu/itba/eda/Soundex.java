package ar.edu.itba.eda;

public class Soundex {

    private String soundx;
    private String original;

    private static char [] abc = {'0' , '1' , '2' , '3' , '0' , '1' , '2' , '0' , '0' , '2' , '2' , '4' , '5' , '5' , '0' , '1' , '2' ,
            '6' , '2' , '3' , '0' , '1' , '0' , '2' , '0' , '2'};


    Soundex(String s){
        original=s;
        soundx = soundex(s);
    }

    @Override
    public String toString(){
        return "%s".formatted(soundx);
    }

    public static String soundex(String s){
        String s2 = s.toUpperCase();
        char [] IN = s2.toCharArray();
        char [] OUT = { '0' , '0' , '0' , '0'};
        OUT[0]=IN[0];
        int count=1;
        char current , last = mapChar(IN[0]);
        for(int i=1 ; i<IN.length && count<4 ; i++ , current=last){
            current = mapChar(IN[i]);
            if(current != '0' && current != last){
                OUT[count++] = current;
            }
        }
        if(count < 4){
            for(; count<4 ; count++){
                OUT[count] = '0';
            }
        }
        return new String(OUT);
    }


    public static char mapChar(char c){
        return abc[c - 'A'];
    }

    public double similarity(String s){
        return similarity(this.original,s);
    }

    public static double similarity(String s,String s1){
        String sSoundex= soundex(s);
        String s2Soundex = soundex(s1);

        double ans=0;

        for(int i=0 ; i<4 ; i++){
            if( sSoundex.charAt(i) == s2Soundex.charAt(i)) {
                ans+=0.25;
            }
        }
        return ans;
    }

}
