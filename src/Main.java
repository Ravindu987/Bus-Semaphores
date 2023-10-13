import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    public static int busCapacity = 50;
    public static Semaphore mutex = new Semaphore(1);
    public static Semaphore multiplex = new Semaphore(busCapacity);
    public static Semaphore bus = new Semaphore(0);
    public static Semaphore allAboard = new Semaphore(0);
    public static int riders = 0;
    public static void main(String[] args) {

        Thread busScheduler = new Thread(new BusScheduler());
        Thread riderScheduler = new Thread(new RiderScheduler());

        busScheduler.start();
        riderScheduler.start();
    }

    public static long calculateExponentialDelay(double mean, Random random) {
        // Use the mean value to calculate the exponential delay
        return (long) (-mean * Math.log(1 - random.nextDouble()));
    }
}