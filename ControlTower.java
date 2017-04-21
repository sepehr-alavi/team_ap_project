//Checked

//Used as main class

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControlTower {
    private static int money;
    private static AntiAircraft[] database = new AntiAircraft[6];
    static ArrayList<Aircraft> savedAircrafts = new ArrayList<Aircraft>();
    static ArrayList<AntiAircraft> antiAircrafts = new ArrayList<AntiAircraft>();
    static ArrayList<Aircraft> aircrafts = new ArrayList<Aircraft>();
    static AirPort airPort;
    static LandingStrip landingStrip;
    private static ExecutorService threadExecutor = Executors.newCachedThreadPool();
    static CreateAircraftThread[] create;
    private static MoveThread move = new MoveThread();
    private static InputThread getOrder = new InputThread();
    //Getter and setter
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    //Methods
    static void paymoney ( int passengers ){
        money += passengers *(10 + airPort.getLevel());
        airPort.setLevel( airPort.getLevel() + 1 );
    }

    static public void createAircrafts() {
        for (int i = 0; i < savedAircrafts.size(); i++) {
            create[i] = new CreateAircraftThread(savedAircrafts.get(i));
            threadExecutor.execute(create[i]);
        }
    }

    static void checkCollisions() {
        //check fighter
        for (int i = 0; i < aircrafts.size(); i++) {
            if (aircrafts.get(i) instanceof AirPlane == true) {
                AirPlane checkedAirPlane = (AirPlane) aircrafts.get(i);
                for (int j = i + 1; j < aircrafts.size(); j++) {
                    if (aircrafts.get(j) instanceof AirPlane == true) {
                        AirPlane checkedAirPlane2 = (AirPlane) aircrafts.get(j);
                        if (distance(checkedAirPlane.getCoordinate(), checkedAirPlane2.getCoordinate() ) <= 0.1 ) {
                            airPort.setHp(airPort.getHp() - 1);
                            System.out.println(checkedAirPlane.getName() + " and " + checkedAirPlane2.getName() + " crashed. Lives = " + airPort.getHp());
                            aircrafts.remove(aircrafts.get(i));
                            savedAircrafts.remove(aircrafts.get(i));
                            aircrafts.remove(aircrafts.get(j));
                            savedAircrafts.remove(aircrafts.get(j));
                        }
                    }
                }
            }
        }
    }

    static void checkArrivals() {
        for (int i = 0; i < aircrafts.size(); i++){
            if (aircrafts.get(i) instanceof AirPlane == true) {
                if( distance( aircrafts.get(i).getCoordinate(), landingStrip.getCoordinate() ) <= 0.1 ) {
                    AirPlane arrivedairplane = (AirPlane) aircrafts.get(i);
                    paymoney(arrivedairplane.getPassengersCount());
                    System.out.println(aircrafts.get(i).getName() + " landed. Money = " + money);
                    aircrafts.remove(aircrafts.get(i));
                }
            } else if (aircrafts.get(i) instanceof Fighter == true) {
                if (distance(aircrafts.get(i).getCoordinate(), airPort.getCoordinate()) <= 0.1) {
                    airPort.setHp(airPort.getHp() - 1);
                    aircrafts.remove(aircrafts.get(i));
                    savedAircrafts.remove(aircrafts.get(i));
                }
            }
        }
    }

    static double distance(Coordinate coordinate1, Coordinate coordinate2) {
        //radian to degree
        coordinate1.setLat(coordinate1.getLat() * (Math.PI / 180));
        coordinate2.setLat(coordinate2.getLat() * (Math.PI / 180));
        coordinate1.setLon(coordinate1.getLon() * (Math.PI / 180));
        coordinate2.setLon(coordinate2.getLon() * (Math.PI / 180));
        double distance = 6371 * Math.acos((Math.sin(coordinate1.getLat()) * Math.sin(coordinate2.getLat())) + (Math.cos(coordinate1.getLat()) * Math.cos(coordinate2.getLat()) * Math.cos(coordinate2.getLon() - coordinate1.getLon())));
        //degree to radian
        coordinate1.setLat(coordinate1.getLat() * (180 / Math.PI));
        coordinate2.setLat(coordinate2.getLat() * (180 / Math.PI));
        coordinate1.setLon(coordinate1.getLon() * (180 / Math.PI));
        coordinate2.setLon(coordinate2.getLon() * (180 / Math.PI));
        return distance;
    }

    static double angle(Coordinate coordinate1, Coordinate coordinate2) {
        //degree to radian
        coordinate1.setLat(coordinate1.getLat() * (Math.PI / 180));
        coordinate2.setLat(coordinate2.getLat() * (Math.PI / 180));
        coordinate1.setLon(coordinate1.getLon() * (Math.PI / 180));
        coordinate2.setLon(coordinate2.getLon() * (Math.PI / 180));
        double teta = Math.atan2(Math.sin(coordinate2.getLon() - coordinate1.getLon()) * Math.cos(coordinate2.getLat()), (Math.sin(coordinate1.getLat()) * Math.cos(coordinate2.getLat()) * Math.cos(coordinate2.getLon() - coordinate1.getLon())));
        //radian to degree
        coordinate1.setLat(coordinate1.getLat() * (180 / Math.PI));
        coordinate2.setLat(coordinate2.getLat() * (180 / Math.PI));
        coordinate1.setLon(coordinate1.getLon() * (180 / Math.PI));
        coordinate2.setLon(coordinate2.getLon() * (180 / Math.PI));
        return teta * (180 / Math.PI);
    }

    static Coordinate finalPoint(Coordinate coordinate1, double teta, double distance) {
        //radian to degree
        coordinate1.setLat(coordinate1.getLat() * (Math.PI / 180));
        coordinate1.setLon(coordinate1.getLon() * (Math.PI / 180));

        Coordinate coordinate2 = new Coordinate();
        coordinate2.setLat(Math.asin((Math.sin(coordinate1.getLat()) * Math.cos(distance / 6371)) + (Math.cos(coordinate1.getLat()) * Math.sin(distance / 6371) * Math.cos(teta))));
        coordinate2.setLon(coordinate1.getLon() + Math.atan2(Math.sin(teta) * Math.sin(distance / 6371) * Math.cos(coordinate1.getLat()), (Math.cos(distance / 6371)) - (Math.sin(coordinate1.getLat()) * Math.sin(coordinate2.getLat()))));

        coordinate1.setLat(coordinate1.getLat() * (180 / Math.PI));
        coordinate1.setLon(coordinate1.getLon() * (180 / Math.PI));

        coordinate2.setLat(coordinate2.getLat() * (180 / Math.PI));
        coordinate2.setLon(coordinate2.getLon() * (180 / Math.PI));
        return coordinate2;
    }

    public static void createAntiAircraft(String name, double lat, double lon) {
        for (int i = 0; i < 6; i++) {
            //Enaugh money
            if (database[i].getName().equals(name) && money >= database[i].getPrice()) {
                database[i].setCoordinate(lat, lon);
                antiAircrafts.add(database[i]);
                money -= database[i].getPrice();
                System.out.println(" Buying " + database[i].getName() + "was successful. " + "Money = " + money);
            }
            //Not enaugh money
            else if (database[i].getName().equals(name) && money < database[i].getPrice())
                System.out.println("Buying " + database[i].getName() + "wasn't successful.");
        }
    }

    //Main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Anti aircrafts database
        database[0] = new MachineGun("samavat", 1000, 4, 500);
        database[1] = new MachineGun("saeer", 2000, 11, 300);
        database[2] = new Cannon("shahab_sagheb", 5000, 12, 80, 30);
        database[3] = new Cannon("torm1", 10000, 12, 90, 15);
        database[4] = new Cannon("mersad", 15000, 40, 90, 30);
        database[5] = new Cannon("bavar373", 40000, 150, 90, 30);

        double lat_UpLeft = scanner.nextDouble();
        double lon_UpLeft = scanner.nextDouble();
        double lat_DownRight = scanner.nextDouble();
        double lon_DownRight = scanner.nextDouble();
        double latA = scanner.nextDouble();
        double lonA = scanner.nextDouble();

        landingStrip = new LandingStrip("LandingStrip_1", scanner.nextDouble(), scanner.nextDouble());

        money = scanner.nextInt();

        airPort = new AirPort("AirBase", latA, lonA, scanner.nextInt());

        int num = scanner.nextInt();
        create = new CreateAircraftThread[num];
        for (int i = 0; i < num; i++) {
            String name = scanner.next();
            double lat = scanner.nextDouble();
            double lon = scanner.nextDouble();
            double speed = scanner.nextDouble();
            double enteringTime = scanner.nextDouble();
            String type = scanner.next();
            int x = scanner.nextInt(); //hp or passengersCount
            if (type.equals("fighter")) {
                Fighter fighter = new Fighter(name, lat, lon, speed, enteringTime, x);
                fighter.setDestination(airPort.getCoordinate());
                fighter.setBearing(angle(fighter.getCoordinate(), fighter.getDestination()));
                savedAircrafts.add(fighter);
            } else if (type.equals("airliner")) {
                AirPlane airPlane = new AirPlane(name, lat, lon, speed, enteringTime, x);
                airPlane.setDestination(airPort.getCoordinate());
                airPlane.setBearing(angle(airPlane.getCoordinate(), airPlane.getDestination()));
                savedAircrafts.add(airPlane);//
            }
        }

        //Start
        createAircrafts();
        threadExecutor.execute(move);
        threadExecutor.execute(getOrder);
        while (savedAircrafts.size() > 0) {
            checkCollisions();
            checkArrivals();


        }

    }
}
