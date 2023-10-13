import java.util.Random;

public class BusScheduler implements Runnable{

    private static final Random busArrivalRandom = new Random();
    @Override
    public void run() {

        while(true) {

            Thread bus = new Thread(new Bus(Main.busCapacity, 0));
            bus.start();

            long delay = Main.calculateExponentialDelay(Main.busIntervalMean, busArrivalRandom);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
