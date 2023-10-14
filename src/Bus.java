public class Bus implements Runnable{

    private static int capacity;
    private static int load;

    /**
     * Constructor for the Bus class.
     * Initializes the bus with the given capacity and current load.
     *
     * @param capacity The total capacity of the bus.
     * @param load     The current number of passengers on the bus.
     */
    public Bus(int capacity, int load) {
        this.capacity = capacity;
        this.load = load;
        System.out.println(Main.getElapsedTime() + " - Bus arrived");
    }

    /**
     * Method to increment the load of the bus when a rider boards.
     */
    public static void board(){
        load += 1;
    }

    /**
     * Method to simulate the departure of the bus.
     */
    private void depart(){
        System.out.printf(Main.getElapsedTime() + " - Bus departed with %d passengers\n", this.load);
    }

    /**
     * Overridden run method for the Bus thread.
     * It handles the logic of allowing riders to board the bus and then departing.
     */
    @Override
    public void run() {
        try {
            // Acquire mutex lock to ensure that only one bus accesses the shared waitingRiders variable at a time.
            Main.mutex.acquire();
            if (Main.waitingRiders > 0){
                // If there are waiting riders, release the bus semaphore to let them board.
                Main.bus.release();
                // Wait until all the riders have boarded.
                Main.allAboard.acquire();
            }
            Main.mutex.release();            // Release the mutex lock.
            depart();            // Simulate bus departure.

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
