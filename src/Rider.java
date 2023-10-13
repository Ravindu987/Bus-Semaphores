public class Rider implements Runnable{

    private int id;

    public Rider(int id) {
        this.id = id;
        System.out.printf("Rider %d arrived\n", this.id);
    }

    private void boardBus(){
        Bus.board();
        System.out.printf("Passenger %d has boarded the bus\n", this.id);
    }

    public void run(){
        try {
            Main.multiplex.acquire();
            Main.mutex.acquire();
            Main.waitingRiders += 1;
            Main.mutex.release();

            Main.bus.acquire();
            Main.multiplex.release();

            //BoardBus
            boardBus();

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
