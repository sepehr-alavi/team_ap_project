
//Used as main class
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
public class controlTower {
    private static int money;
    private static AntiAircraft[] database = new AntiAircraft[6];
    private static ArrayList<Aircraft> aircrafts= new ArrayList<Aircraft>();
    private static ArrayList<AntiAircraft> antiAircrafts = new ArrayList<AntiAircraft>();

    //Getter and setter
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    //Methods
    /*static double TowPointDistance (Coordinate coordinate1 , Coordinate coordinate2){
        //coordinate1.setLat( coordinate1.getLat() * ( Math.PI / 180 ) );
        //coordinate2.lat = coordinate2.lat * (Math.PI / 180);
        //coordinate1.lon = coordinate1.lon * (Math.PI / 180);
        //coordinate2.lon = coordinate2.lon * (Math.PI / 180);
        //double distance = 6371 * Math.acos( ( Math.sin( coordinate1.lat ) * Math.sin( coordinate2.lat ) ) + ( Math.cos( coordinate1.lat ) * Math.cos( coordinate2.lat ) * Math.cos( coordinate2.lon - coordinate1.lon )) );
        //return distance * (180 / Math.PI);
    }

    static double Angle (Coordinate coordinate1, Coordinate coordinate2) {
        //coordinate1.lat = coordinate1.lat * (Math.PI / 180);
        //coordinate2.lat = coordinate2.lat * (Math.PI / 180);
        //coordinate1.lon = coordinate1.lon * (Math.PI / 180);
        //coordinate2.lon = coordinate2.lon * (Math.PI / 180);
        //double teta = Math.atan2( Math.sin( coordinate2.lon - coordinate1.lon ) * Math.cos( coordinate2.lat ) , ( Math.sin( coordinate1.lat ) * Math.cos( coordinate2.lat ) * Math.cos( coordinate2.lon - coordinate1.lon)));
        //return teta * (180 / Math.PI);
    }

    static Coordinate FinalPoint (Coordinate coordinate1 ,  double teta , double distance){
        coordinate1.lat = coordinate1.lat * (Math.PI / 180);
        coordinate1.lon = coordinate1.lon * (Math.PI / 180);
        Coordinate coordinate2 = new Coordinate();
        coordinate2.lat = Math.asin(( Math.sin( coordinate1.lat ) * Math.cos( distance/6371 ) ) + ( Math.cos(coordinate1.lat) * Math.sin( distance/6371 ) * Math.cos( teta )));
        coordinate2.lon = coordinate1.lon + Math.atan2( Math.sin( teta ) * Math.sin( distance/6371 ) * Math.cos(coordinate1.lat) , ( Math.cos( distance/6371 ) ) - ( Math.sin( coordinate1.lat ) * Math.sin( coordinate1.lat )));
        coordinate2.lat *= 180 / Math.PI;
        coordinate2.lon *= 180 / Math.PI;
        return coordinate2;
    }*/

    public static void createAntiAircraft( String name, double lat, double lon ){
        for(int i = 0; i < 6; i++ ){
            //Enaugh money
            if( database[i].getName().equals( name ) && money >= database[i].getPrice() ) {
                database[i].setCoordinate(lat, lon);
                antiAircrafts.add(database[i]);
                money -= database[i].getPrice();
                System.out.println(" Buying " + database[i].getName() + "was successful. " + "Money = " + money);
            }
            //Not enaugh money
            else if( database[i].getName().equals( name ) && money < database[i].getPrice() )
                System.out.println( "Buying " + database[i].getName() + "wasn't successful." );
        }
    }

    //Main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Anti aircrafts database
        database[0] = new MachineGun( "samavat", 1000, 4, 500);
        database[1] = new MachineGun( "saeer", 2000, 11, 300);
        database[2] = new Cannon( "shahab_sagheb", 5000, 12, 80, 30);
        database[3] = new Cannon( "torm1", 10000, 12, 90, 15);
        database[4] = new Cannon( "mersad", 15000, 40, 90, 30);
        database[5] = new Cannon( "bavar373", 40000, 150, 90, 30);

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
                aircrafts.add(fighter);
            }
            else if (type.equals("airline")) {
                AirPlane airPlane = new AirPlane (name, lat, lon, speed, enteringTime, x);
                aircrafts.add(airPlane);
            }
        }

        while( aircrafts.size() > 0  ){
            for( int i = 0; i < aircrafts.size(); i++ ){

            }
        }

        String s = scanner.next(); //buy or control
        if( s.equals( "buy" ) ) {
            createAntiAircraft(scanner.next(), scanner.nextDouble(), scanner.nextDouble());
        }

    }
}
