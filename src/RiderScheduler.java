import java.util.Random;

public class RiderScheduler implements Runnable{

    private static final Random riderArrivalRandom = new Random();
    private static int riderCounter = 1;
    @Override
    public void run() {

        while (true){
            long delay = Main.calculateExponentialDelay(1000, riderArrivalRandom);

            Thread rider = new Thread(new Rider(riderCounter));
            rider.start();

            riderCounter += 1;

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
