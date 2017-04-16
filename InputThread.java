//Checked
//After reaching destination?

import java.util.Scanner;

public class InputThread extends Thread implements Runnable {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (controlTower.airPort.getHp() != 0 || controlTower.savedAircrafts.size() != 0) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("Control")) {
                String aircraftName = scanner.next();
                for (int i = 0; i < controlTower.aircrafts.size(); i++) {

                    if (aircraftName.equals(controlTower.aircrafts.get(i).getName())) {
                        controlTower.aircrafts.get(i).getDestination().setLat(scanner.nextDouble());
                        controlTower.aircrafts.get(i).getDestination().setLon(scanner.nextDouble());
                        controlTower.aircrafts.get(i).setBearing(controlTower.angle(controlTower.aircrafts.get(i).getCoordinate(),
                                                                                    controlTower.aircrafts.get(i).getDestination()));
                    }
                }
            } else if (input.equals("buy"))
                controlTower.createAntiAircraft(scanner.next(), scanner.nextDouble(), scanner.nextDouble());
        }
    }
}
