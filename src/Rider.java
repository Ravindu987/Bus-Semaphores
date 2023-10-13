public class Rider implements Runnable{

    int id;

    public Rider(int id) {
        this.id = id;
    }

    public void boardBus(){
        System.out.println("Passenger %d has boarded the bus", this.id);
    }

    public void run(){
        try {
            Main.multiplex.acquire();
            Main.mutex.acquire();
            Main.riders += 1;
            Main.mutex.release();

            Main.bus.wait();
            Main.multiplex.release();

            //BoardBus
            boardBus();

            Main.riders -= 1;
            if (Main.riders == 0){
                Main.allAboard.release();
            } else{
                Main.bus.release();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
