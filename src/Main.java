import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

    static final int busCapacity = 50;
    static final int busIntervalMean = 10000;  // mean time between bus arrivals
    static final int riderIntervalMean = 1000; // mean time between rider arrivals
    static Semaphore mutex = new Semaphore(1); // mutex for mutual exclusion
    static Semaphore multiplex = new Semaphore(busCapacity); // semaphore to limit riders based on bus capacity
    static Semaphore bus = new Semaphore(0);  // semaphore to signal the arrival of a bus
    static Semaphore allAboard = new Semaphore(0); // semaphore to indicate all riders have boarded
    static int waitingRiders = 0; // count of riders currently waiting

    // to store the start time
    static long startTime;

    public static void main(String[] args) {

        // Initialize the start time when the program begins
        startTime = System.nanoTime();

        Thread busScheduler = new Thread(new BusScheduler());
        Thread riderScheduler = new Thread(new RiderScheduler());

        busScheduler.start();
        riderScheduler.start();

    }


    /**
     * Calculates the exponential delay for bus and rider arrival.
     *
     * @param mean   The mean interval time.
     * @param random Random instance to generate random values.
     * @return The calculated delay.
     */
    static long calculateExponentialDelay(double mean, Random random) {
        // Use the mean value to calculate the exponential delay
        return (long) (-mean * Math.log(1 - random.nextDouble()));
    }


    /**
     * Utility method to calculate the elapsed time since the program started.
     * @return Formatted elapsed time as "mm:ss:SSS:nnnnnnnnn".
     */
    static String getElapsedTime() {
        long elapsedNanos = System.nanoTime() - startTime;
        long minutes = TimeUnit.NANOSECONDS.toMinutes(elapsedNanos);
        long seconds = TimeUnit.NANOSECONDS.toSeconds(elapsedNanos) -
                       TimeUnit.MINUTES.toSeconds(minutes);
        long milliseconds = TimeUnit.NANOSECONDS.toMillis(elapsedNanos) % 1000;
        long nanoseconds = elapsedNanos % 1_000_000;
        return String.format("%02d:%02d:%03d:%09d", minutes, seconds, milliseconds, nanoseconds);
    }
}