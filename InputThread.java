import java.util.Scanner;

/**
 * Created by Bahar on 4/13/2017.
 */
public class InputThread extends Thread implements Runnable {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (controlTower.airPort.getHp() != 0 || controlTower.savedAircrafts.size() != 0) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("Control")) {
                String aircraftName = scanner.next();
                Coordinate destination = new Coordinate();
                destination.setLat(scanner.nextDouble());
                destination.setLon(scanner.nextDouble());
                for (int i = 0; i < controlTower.aircrafts.size(); i++) {
                    if (aircraftName.equals(controlTower.aircrafts.get(i).getName())) {
                        controlTower.aircrafts.get(i).setBearing(controlTower.angle(controlTower.aircrafts.get(i).getCoordinate(), destination));
                    }
                }
            } else if (input.equals("buy"))
                controlTower.createAntiAircraft(scanner.next(), scanner.nextDouble(), scanner.nextDouble());
        }
    }
}
