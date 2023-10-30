import java.util.Random;

public class BusScheduler implements Runnable{

    private static final Random busArrivalRandom = new Random();

    /**
     * Overridden run method to simulate the continuous arrival of buses.
     */
    @Override
    public void run() {

        while(true) {

            long delay = Main.calculateExponentialDelay(Main.busIntervalMean, busArrivalRandom);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Thread bus = new Thread(new Bus(Main.busCapacity, 0));
            bus.start();
        }
    }
}
