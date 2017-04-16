//Checked

public class CreateAircraftThread extends Thread implements Runnable {
    Aircraft aircraft = new Aircraft();

    public CreateAircraftThread(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (aircraft.getEnteringTime() * 1000));
        } catch (InterruptedException e) {
            System.out.println("Intrrupted");
        }
        controlTower.aircrafts.add(aircraft);

    }
}
