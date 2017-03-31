
public class Cannon extends AntiAircraft implements AntiAircraftFunction{
    private double missChance;
    private double fireRate;

    //Constructor
    public Cannon( String name, double lat, double lon, int price, double missChance , double fireRate ) {
        this.setName(name);
        this.setCoordinate(lat, lon);
        this.setPrice( price );
        this.missChance = missChance;
        this.fireRate = fireRate;
    }

    //Methods
    public void shoot( Fighter fighter ){
    }
}
