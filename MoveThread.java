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
                        , ControlTower.aircrafts.get(i).getSpeed() * 0.1);
                ControlTower.aircrafts.get(i).setCoordinate(finalposition.getLat(), finalposition.getLon());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Intrrupted");
            }

        }
    }
}
