package ar.edu.itba.eda;

import static ar.edu.itba.eda.Soundex.similarity;
import static ar.edu.itba.eda.Soundex.soundex;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Soundex bro = new Soundex("treshold");
        System.out.println(bro);


        double s1= Soundex.similarity("threshold" , "hold");
        System.out.println(s1);
        double s2= new Soundex("phone").similarity( "foun");


        System.out.println(s2);

//        String s= "treshold"       ;
//        String s= "brooklin";
//        String s2= "bruqleen";
//        String s3= "brooclean";
//        String s4= "bluclean";
//        String s5= "clean";
//
//
//
//        System.out.println("Original: " + s + " Encoding: " + soundex(s));
//        System.out.println("Original: " + s2 + " Encoding: " + soundex(s2));
//        System.out.println("Original: " + s3 + " Encoding: " + soundex(s3));
//        System.out.println("Original: " + s4 + " Encoding: " + soundex(s4));
//        System.out.println("Original: " + s5 + " Encoding: " + soundex(s5));
//
//        System.out.println( s + " vs " + s2 + "= " + similarity(s,s2));
//        System.out.println( s + " vs " + s3 + "= " + similarity(s,s3));
//        System.out.println( s + " vs " + s4 + "= " + similarity(s,s4));
//        System.out.println( s + " vs " + s5 + "= " + similarity(s,s5));
    }
}

