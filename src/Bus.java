import java.util.Random;

public class Bus implements Runnable{

    int capacity;
    static int load;

    public Bus(int capacity, int load) {
        this.capacity = capacity;
        this.load = load;
        System.out.println("Bus arrived");
    }

    public static void board(){
        load += 1;
    }

    public void depart(){
        System.out.printf("Bus departed with %d passengers\n", this.load);
    }

    @Override
    public void run() {
        try {

            Main.mutex.acquire();
            if (Main.riders > 0){
                Main.bus.release();
                Main.allAboard.acquire();
            }
            Main.mutex.release();

            //Depart
            depart();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
