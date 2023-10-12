public class Bus implements Runnable{

    int capacity;

    public Bus(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void run() {
        try {
            Main.mutex.acquire();
            if (Main.riders > 0){
                Main.bus.release();
                Main.allAboard.wait();
            }
            Main.mutex.release();

            //Depart

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
