public class Rider implements Runnable{

    public void run(){
        try {
            Main.multiplex.acquire();
            Main.mutex.acquire();
            Main.riders += 1;
            Main.mutex.release();

            Main.bus.wait();
            Main.multiplex.release();

            //BoardBus

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
