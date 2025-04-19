//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

        MyTimer timer= new MyTimer();

        Thread.sleep(5000);

        timer.stop();

//        timer.stop(93623040);

        System.out.println(timer);

        }
    }

