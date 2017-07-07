//Checked

//Used as main class

import com.sun.javafx.scene.paint.GradientUtils;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControlTower extends Application{
    String current = "padafandi";
    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root);
        Text text1 = new Text(1220,30,"Money:       " + Integer.toString(getMoney()));
        Text text2 = new Text(1220, 60, "hp:              " + Integer.toString(airPort.getHp()));
        Text text3 = new Text(1220,100, Integer.toString(database[0].getPrice()));
        Text text4 = new Text(1220,200, Integer.toString(database[1].getPrice()));
        Text text5 = new Text(1220,300, Integer.toString(database[2].getPrice()));
        Text text6 = new Text(1220,400, Integer.toString(database[3].getPrice()));
        Text text7 = new Text(1220,500, Integer.toString(database[4].getPrice()));
        Text text8 = new Text(1220,600, Integer.toString(database[5].getPrice()));
        text1.setFill(Color.WHITE);
        text2.setFill(Color.WHITE);
        text3.setFill(Color.WHITE);
        text4.setFill(Color.WHITE);
        text5.setFill(Color.WHITE);
        text6.setFill(Color.WHITE);
        text7.setFill(Color.WHITE);
        text8.setFill(Color.WHITE);


        Timeline checkMouseClickTimeline = new Timeline();
        KeyFrame checkMouseClickKeyFrame= new KeyFrame(Duration.millis(10),event -> {
            for (int i = 0; i <6 ; i++) {
                final int index = i;
                boolean selected = true;
                antiAircrafts.add(database[i]);
                database[i].ax.setOnMouseClicked(event1 -> {
                    System.out.println("clicked");
                    scene.setOnMouseClicked(event2-> {
                        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
                        if (mousePoint.getX() - 270 < 1200 && current.equals("padafandi")) {
                            ImageView selectedAntiAircraft = new ImageView(database[index].image);
                            selectedAntiAircraft.setFitHeight(75);
                            selectedAntiAircraft.setFitWidth(75);
                            selectedAntiAircraft.setX(mousePoint.getX() - 300);
                            selectedAntiAircraft.setY(mousePoint.getY() - 145);
                            root.getChildren().add(selectedAntiAircraft);
                        }

                    });
                });
            }
        });
        checkMouseClickTimeline.getKeyFrames().add(checkMouseClickKeyFrame);
        checkMouseClickTimeline.setCycleCount(Timeline.INDEFINITE);
        checkMouseClickTimeline.play();


        root.getChildren().add(text1);
        root.getChildren().add(text2);
        root.getChildren().addAll(text3 , text4 , text5 , text6 , text7 , text8);
        root.getChildren().addAll(database[0].ax , database[1].ax , database[2].ax , database[3].ax , database[4].ax , database[5].ax);

        primaryStage.setTitle("padafandi");

        primaryStage.setScene(scene);
        scene.setFill(Color.GRAY);
        primaryStage.setHeight(800);
        primaryStage.setWidth(1400);

        Random random = new Random();

        Image padafandiImg = new Image("Media\\Padafandi.png");
        Image furudgahiImg = new Image("Media\\Furudgahi.png");

        ImageView backgroundImgView = new ImageView(padafandiImg);
        backgroundImgView.setFitWidth(1200);
        backgroundImgView.setFitHeight(800);
        root.getChildren().add(backgroundImgView);
        root.getChildren().get(root.getChildren().size()-1).toBack();


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
        KeyFrame createKeyFrame = new KeyFrame(Duration.millis(5000 + random.nextInt(3000)), event -> {
            if (random.nextBoolean() == true) { //Left or right
                Fighter fighter = new Fighter(1135 * random.nextInt(2), random.nextInt(801), 100 + random.nextInt(100), 1500 + random.nextInt(1500));
                fighters.add(fighter);
                AirPlane airLiner = new AirPlane(1115 * random.nextInt(2), random.nextInt(801),100 + random.nextInt(100), 100 + random.nextInt(400));
                if( airLiner.getCoordinate().getX() == 0 )
                    //airLiner.setBearing(random );
                airLiners.add(airLiner);
            } else { //Top or bottom
                Fighter fighter = new Fighter(random.nextInt(1135),800 * random.nextInt(2), 100 + random.nextInt(100), 1500 + random.nextInt(1500));
                fighters.add(fighter);
                AirPlane airLiner = new AirPlane(random.nextInt(1115), 800 * random.nextInt(2),100 + random.nextInt(100), 100 + random.nextInt(400));
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

//                    for(int i=0; i< antiAircrafts.size(); i++)
//                        root.getChildren().remove(antiAircrafts.get(i).ax);

                    backgroundImgView.setImage(furudgahiImg);
                    current = "furudgahi";
                } else if (current.equals("furudgahi")) {
                    primaryStage.setTitle("padafandi");
                    for (int i = 0; i < airLiners.size(); i++)
                        root.getChildren().remove(airLiners.get(i).ax);

                    for (int i = 0; i < fighters.size(); i++)
                        root.getChildren().add(fighters.get(i).ax);

//                    for(int i=0; i< antiAircrafts.size(); i++)
//                        root.getChildren().add(antiAircrafts.get(i).ax);

                    backgroundImgView.setImage(padafandiImg);
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
        System.out.println(teta * (180 / Math.PI));
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
        airPort = new AirPort("Air Base", 0, 0, 3);

        //Anti aircrafts database
        database[0] = new MachineGun("samavat", 1000, 4, 500);
        database[0].image = new Image("Media\\Samavat.png");

        database[1] = new MachineGun("saeer", 2000, 11, 300);
        database[1].image = new Image("Media\\Saeer.png");

        database[2] = new Cannon("shahab_sagheb", 5000, 12, 80, 30);
        database[2].image = new Image("Media\\Shahab_sagheb.png");

        database[3] = new Cannon("torm1", 10000, 12, 90, 15);
        database[3].image = new Image("Media\\Torm1.png");

        database[4] = new Cannon("mersad", 15000, 40, 90, 30);
        database[4].image = new Image("Media\\Mersad.png");

        database[5] = new Cannon("bavar373", 40000, 150, 90, 30);
        database[5].image = new Image("Media\\Bavar373.png");

        for( int i = 0; i<6; i++ ){
            database[i].ax = new ImageView(database[i].image);
            database[i].ax.setFitHeight(75);
            database[i].ax.setFitWidth(75);
            database[i].ax.setX(1250);
            database[i].ax.setY(100*i + 90);
        }

        launch(args);

    }
}
