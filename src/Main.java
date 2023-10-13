import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    static final int busCapacity = 50;
    static Semaphore mutex = new Semaphore(1);
    static Semaphore multiplex = new Semaphore(busCapacity);
    static Semaphore bus = new Semaphore(0);
    static Semaphore allAboard = new Semaphore(0);
    static int waitingRiders = 0;
    public static void main(String[] args) {

        Thread busScheduler = new Thread(new BusScheduler());
        Thread riderScheduler = new Thread(new RiderScheduler());

        busScheduler.start();
        riderScheduler.start();

    }

    static long calculateExponentialDelay(double mean, Random random) {
        // Use the mean value to calculate the exponential delay
        return (long) (-mean * Math.log(1 - random.nextDouble()));
    }
}