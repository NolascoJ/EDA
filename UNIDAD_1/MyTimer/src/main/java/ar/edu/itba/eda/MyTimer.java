package ar.edu.itba.eda;

import java.util.concurrent.TimeUnit;

public class MyTimer {
    private long start;
    private long end;

    MyTimer() {
        this.start = System.currentTimeMillis();
    }

    MyTimer(long ms) {
        this.start = ms;
    }

    public void stop() {
        this.end = System.currentTimeMillis();
    }

    public void stop(long ms) {
        this.end = ms + this.start;
        if (this.end < this.start) {
            throw new RuntimeException("Stop time is lower than start time");
        }
    }

    public String toString() {
        long tiempo = this.end - this.start;
        if (tiempo < 0L) {
            throw new RuntimeException("Stop time is greater than start time");
        } else {
            long dias = TimeUnit.MILLISECONDS.toDays(tiempo);
            long horas = TimeUnit.MILLISECONDS.toHours(tiempo) % 24L;
            long minutos = TimeUnit.MILLISECONDS.toMinutes(tiempo) % 60L;
            long segundos = TimeUnit.MILLISECONDS.toSeconds(tiempo) % 60L;
            long resto = TimeUnit.MILLISECONDS.toMillis(tiempo) % 1000L;
            return String.format("(%d ms) %d dia(s) %d hora(s) %d minuto(s) %d,%d segundo(s)", tiempo, dias, horas, minutos, segundos, resto);
        }
    }
}