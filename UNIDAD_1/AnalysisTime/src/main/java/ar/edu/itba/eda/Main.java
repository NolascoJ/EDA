package ar.edu.itba.eda;

public class Main {
    public static void main(String[] args) {
        MyTimer myCrono = new MyTimer(10);
        myCrono.stop(10 + 93623040);
        System.out.println(myCrono);
    }
}
