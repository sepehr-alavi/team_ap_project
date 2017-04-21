//Checked
//After reaching destination?

import java.util.Scanner;

public class InputThread extends Thread implements Runnable {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (ControlTower.airPort.getHp() != 0 || ControlTower.savedAircrafts.size() != 0) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("Control")) {
                String aircraftName = scanner.next();
                for (int i = 0; i < ControlTower.aircrafts.size(); i++) {

                    if (aircraftName.equals(ControlTower.aircrafts.get(i).getName())) {
                        ControlTower.aircrafts.get(i).getDestination().setLat(scanner.nextDouble());
                        ControlTower.aircrafts.get(i).getDestination().setLon(scanner.nextDouble());
                        ControlTower.aircrafts.get(i).setBearing(ControlTower.angle(ControlTower.aircrafts.get(i).getCoordinate(),
                                                                                    ControlTower.aircrafts.get(i).getDestination()));
                    }
                }
            } else if (input.equals("buy"))
                ControlTower.createAntiAircraft(scanner.next(), scanner.nextDouble(), scanner.nextDouble());
        }
    }
}
