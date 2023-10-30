import java.util.Random;

public class RiderScheduler implements Runnable{

    private static final Random riderArrivalRandom = new Random();
    private int riderCounter = 1;

    /**
     * Overridden run method to simulate the continuous arrival of riders.
     */
    @Override
    public void run() {

        while (true){

            Thread rider = new Thread(new Rider(riderCounter));
            rider.start();

            riderCounter += 1;

            long delay = Main.calculateExponentialDelay(Main.riderIntervalMean, riderArrivalRandom);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
