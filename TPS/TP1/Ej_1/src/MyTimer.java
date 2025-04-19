import java.util.concurrent.TimeUnit;

public class MyTimer {

    private long start,end;

    MyTimer(){
        this.start = System.currentTimeMillis();
    }

    MyTimer(long ms){
        this.start = ms;
    }

    public void stop(){
        this.end = System.currentTimeMillis();
    }

    public void stop(long ms){
        this.end = ms + start;
        if(end < this.start){
            throw new RuntimeException("Stop time is lower than start time");
        }
    }




    @Override
    public String toString() {

        long tiempo = end - start;

        if(tiempo < 0){
            throw new RuntimeException("Stop time is greater than start time");
        }

        long dias = TimeUnit.MILLISECONDS.toDays(tiempo);
        long horas = TimeUnit.MILLISECONDS.toHours(tiempo) % 24;
        long minutos = TimeUnit.MILLISECONDS.toMinutes(tiempo) % 60;
        long segundos = TimeUnit.MILLISECONDS.toSeconds(tiempo) % 60;
        long resto = TimeUnit.MILLISECONDS.toMillis(tiempo) % 1000;


        return String.format("(%d ms) %d dia(s) %d hora(s) %d minuto(s) %d,%d segundo(s)",
                tiempo, dias, horas, minutos, segundos , resto);
    }
}
