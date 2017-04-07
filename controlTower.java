
//Used as main class.
import java.util.Scanner;
public class controlTower {
    private int money;

    //Getter and setter
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double lat_UpLeft = scanner.nextDouble();
        double lon_UpLeft = scanner.nextDouble();
        double lat_DownRight = scanner.nextDouble();
        double lon_DownRight = scanner.nextDouble();
        double latA = scanner.nextDouble();
        double lonA = scanner.nextDouble();
        LandingStrip landingStrip = new LandingStrip("LandingStrip_1",scanner.nextDouble(),scanner.nextDouble());
        double money = scanner.nextDouble();
        AirPort airPort = new AirPort("AirBase",latA,lonA,scanner.nextInt());
        int num = scanner.nextInt();
        for (int i = 0; i < num ; i++) {
            String name = scanner.next();
            double lat = scanner.nextDouble();
            double lon = scanner.nextDouble();
            double speed = scanner.nextDouble();
            double enteringTime = scanner.nextDouble();
            String type = scanner.next();
            int x = scanner.nextInt(); //hp or passengersCount
            if (type.equals("fighter")) {
                Fighter fighter = new Fighter(name, lat, lon, speed, enteringTime, x);
            }
            else if (type.equals("airline")) {
                AirPlane airPlane = new AirPlane (name, lat, lon, speed, enteringTime, x);
            }
        }

    }
    //Methods
}
