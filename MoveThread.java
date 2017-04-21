/**
 * Created by Bahar on 4/13/2017.
 */
public class MoveThread extends Thread implements Runnable {
    @Override
    public void run() {
        while (ControlTower.airPort.getHp() != 0 || ControlTower.savedAircrafts.size() != 0) {
            for (int i = 0; i < ControlTower.aircrafts.size(); i++) {
                Coordinate finalposition = ControlTower.finalPoint(ControlTower.aircrafts.get(i).getCoordinate()
                        , ControlTower.aircrafts.get(i).getBearing()
                        , ControlTower.aircrafts.get(i).getSpeed() * 2);
                ControlTower.aircrafts.get(i).setCoordinate(finalposition.getLat(), finalposition.getLon());
                System.out.println( ControlTower.aircrafts.get(i).getCoordinate().getLat() + " " + ControlTower.aircrafts.get(i).getCoordinate().getLon() );
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Intrrupted");
            }

        }
    }
}
