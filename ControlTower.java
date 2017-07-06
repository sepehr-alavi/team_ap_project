//Checked

//Used as main class

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControlTower extends Application{
    String current = "padafandi";
    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setTitle("padafandi");
        primaryStage.setScene(scene);
        primaryStage.setHeight(800);
        primaryStage.setWidth(1200);
        Random random = new Random();

        //Check arrivals
        Timeline checkTimeline = new Timeline();
        KeyFrame checkKeyframe = new KeyFrame(Duration.millis(50), event -> {
            //    System.out.println(distance(airPort.getCoordinate().getX(), airPort.getCoordinate().getY(), fighters.get(0).ax.getLayoutX(), fighters.get(0).ax.getLayoutY()));
//            for (int i = 0; i <fighters.size() ; i++) {
//                if (distance( airport.getX(), airport.getY(), fighters.get(i).getX(), fighters.get(i).getY()) < 20 ){
//                    airport.setHp(airport.getHp()-1);
//                    root.getChildren().remove(fighters.get(i).ax);
//                    System.out.println("hi");
//                    fighters.remove(i);
//                    //game over check she
//                }
//            }
//            for (int i = 0; i <airLiners.size() ; i++) {
//                if (distance( airport.getX(), airport.getY(), airLiners.get(i).getX(), airLiners.get(i).getY()) < 20 ){
//                    paymoney(airLiners.get(i).getPassengerCount());
//                    root.getChildren().remove(airLiners.get(i).ax);
//                    airLiners.remove(i);
//                }
//            }
        });

        checkTimeline.getKeyFrames().add(checkKeyframe);
        checkTimeline.setCycleCount(Timeline.INDEFINITE);
        checkTimeline.play();

        //Create airLiners and fighters
        Timeline createTimeline = new Timeline();
        KeyFrame createKeyFrame = new KeyFrame(Duration.millis(3000 + random.nextInt(2000)), event -> {
            if (random.nextBoolean() == true) { //Left or right
                Fighter fighter = new Fighter(1200 * random.nextInt(2), random.nextInt(801), 100 + random.nextInt(100), 1500 + random.nextInt(1500));
                fighters.add(fighter);
                AirPlane airLiner = new AirPlane(1200 * random.nextInt(2), random.nextInt(801),100 + random.nextInt(100), 100 + random.nextInt(400));
                airLiners.add(airLiner);
            } else { //Top or bottom
                Fighter fighter = new Fighter(random.nextInt(1201),800 * random.nextInt(2), 100 + random.nextInt(100), 1500 + random.nextInt(1500));
                fighters.add(fighter);
                AirPlane airLiner = new AirPlane(random.nextInt(1201), 800 * random.nextInt(2),100 + random.nextInt(100), 100 + random.nextInt(400));
                airLiners.add(airLiner);
            }
            Fighter movingFighter = fighters.get(fighters.size() - 1);
            AirPlane movingAirLiner = airLiners.get(airLiners.size() - 1);

            if (current.equals("padafandi"))
                root.getChildren().add(movingFighter.ax);
            else
                root.getChildren().add(movingAirLiner.ax);

            MoveTo moveToFighter = new MoveTo(movingFighter.getCoordinate().getX(), movingFighter.getCoordinate().getY());
            movingFighter.path.getElements().addAll(moveToFighter, movingFighter.lineTo);

//            MoveTo moveToAirLiner = new MoveTo(movingAirLiner.getCoordinate().getX(), movingAirLiner.getCoordinate().getY());
//            movingAirLiner.path.getElements().addAll(moveToAirLiner, movingAirLiner.lineTo);

            PathTransition pathTransitionFighter = new PathTransition(Duration.millis(20000),
                    movingFighter.path, movingFighter.ax);

            PathTransition pathTransitionAirliner = new PathTransition(Duration.millis(20000),
                    movingAirLiner.path, movingAirLiner.ax);
            pathTransitionFighter.play();
            pathTransitionAirliner.play();
        });
        createTimeline.getKeyFrames().add(createKeyFrame);
        createTimeline.setCycleCount(Timeline.INDEFINITE);
        createTimeline.play();


        scene.setOnKeyPressed(event -> {
            if (event.getText().equals("m")) {
                if (current.equals("padafandi")) {
                    primaryStage.setTitle("furudgahi");
                    for (int i = 0; i < fighters.size(); i++)
                        root.getChildren().remove(fighters.get(i).ax);

                    for (int i = 0; i < airLiners.size(); i++)
                        root.getChildren().add(airLiners.get(i).ax);
                    current = "furudgahi";
                } else if (current.equals("furudgahi")) {
                    primaryStage.setTitle("padafandi");
                    for (int i = 0; i < airLiners.size(); i++)
                        root.getChildren().remove(airLiners.get(i).ax);

                    for (int i = 0; i < fighters.size(); i++)
                        root.getChildren().add(fighters.get(i).ax);
                    current = "padafandi";
                }
            }
        });
        primaryStage.show();
    }

    private static int money;
    private static AntiAircraft[] database = new AntiAircraft[6];
    static ArrayList<Aircraft> savedAircrafts = new ArrayList<Aircraft>();
    static ArrayList<AntiAircraft> antiAircrafts = new ArrayList<AntiAircraft>();
    static ArrayList<Aircraft> aircrafts = new ArrayList<Aircraft>();
    static ArrayList<Fighter> fighters = new ArrayList<Fighter>();
    static ArrayList<AirPlane> airLiners = new ArrayList<AirPlane>();
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

//        double lat_UpLeft = scanner.nextDouble();
//        double lon_UpLeft = scanner.nextDouble();
//        double lat_DownRight = scanner.nextDouble();
//        double lon_DownRight = scanner.nextDouble();
//        double latA = scanner.nextDouble();
//        double lonA = scanner.nextDouble();

//        landingStrip = new LandingStrip("LandingStrip_1", scanner.nextDouble(), scanner.nextDouble());
//
//        money = scanner.nextInt();
//
//        airPort = new AirPort("AirBase", latA, lonA, scanner.nextInt());

//        int num = scanner.nextInt();
//        create = new CreateAircraftThread[num];
//        for (int i = 0; i < num; i++) {
//            String name = scanner.next();
//            double lat = scanner.nextDouble();
//            double lon = scanner.nextDouble();
//            double speed = scanner.nextDouble();
//            double enteringTime = scanner.nextDouble();
//            String type = scanner.next();
//            int x = scanner.nextInt(); //hp or passengersCount
//            if (type.equals("fighter")) {
//                Fighter fighter = new Fighter(name, lat, lon, speed, enteringTime, x);
//                fighter.setDestination(airPort.getCoordinate());
//                fighter.setBearing(angle(fighter.getCoordinate(), fighter.getDestination()));
//                savedAircrafts.add(fighter);
//            } else if (type.equals("airliner")) {
//                AirPlane airPlane = new AirPlane(name, lat, lon, speed, enteringTime, x);
//                airPlane.setDestination(airPort.getCoordinate());
//                airPlane.setBearing(angle(airPlane.getCoordinate(), airPlane.getDestination()));
//                savedAircrafts.add(airPlane);//
//            }
//        }

        //Start
//        createAircrafts();
//        threadExecutor.execute(move);
//        threadExecutor.execute(getOrder);
//        while (savedAircrafts.size() > 0) {
//            checkCollisions();
//            checkArrivals();
//
//
//        }
        launch(args);

    }
}
