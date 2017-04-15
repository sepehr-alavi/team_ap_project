/**
 * Created by Bahar on 4/13/2017.
 */
public class MoveThread extends Thread implements Runnable {
    @Override
    public void run() {
        while (controlTower.airPort.getHp() != 0 || controlTower.savedAircrafts.size() != 0) {
            for (int i = 0; i < controlTower.aircrafts.size(); i++) {
                Coordinate finalposition = controlTower.finalPoint(controlTower.aircrafts.get(i).getCoordinate()
                        , controlTower.aircrafts.get(i).getBearing()
                        , controlTower.aircrafts.get(i).getSpeed() * 0.1);
                controlTower.aircrafts.get(i).setCoordinate(finalposition.getLat(), finalposition.getLon());
                controlTower.aircrafts.get(i).setBearing(controlTower.angle(controlTower.aircrafts.get(i).getCoordinate(),controlTower.airPort.getCoordinate()));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Intrrupted");
            }

        }
    }
}
