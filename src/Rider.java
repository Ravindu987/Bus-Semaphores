public class Rider implements Runnable{

    private final int id;

    /**
     * Constructor for the Rider class.
     *
     * @param id Unique ID for the rider.
     */
    public Rider(int id) {
        this.id = id;
        System.out.printf(Main.getElapsedTime() + " - Rider %d arrived\n", this.id);
    }

    /**
     * Simulates the action of a rider boarding the bus.
     */
    private void boardBus(){
        Bus.board();
        System.out.printf(Main.getElapsedTime() + " - Passenger %d has boarded the bus\n", this.id);
    }

    /**
     * Overridden run method for the Rider thread.
     * It handles the logic of waiting for a bus and then boarding it.
     */
    public void run(){
        try {
            // Ensure that the number of riders does not exceed bus capacity.
            Main.multiplex.acquire();

            // Acquire mutex lock to safely modify the shared waitingRiders variable.
            Main.mutex.acquire();
            Main.waitingRiders += 1;
            Main.mutex.release();

            // Wait for a bus to arrive.
            Main.bus.acquire();
            Main.multiplex.release();

            // Simulate boarding the bus.
            boardBus();

            // Modify the waitingRiders count after boarding.
            Main.waitingRiders -= 1;
            if (Main.waitingRiders == 0){
                Main.allAboard.release();
            } else{
                Main.bus.release();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
