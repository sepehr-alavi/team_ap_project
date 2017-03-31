
public class MachineGun extends AntiAircraft implements AntiAircraftFunction{
    private double damage;

    //Constructor
    public MachineGun( String name, double lat, double lon, int price, double damage ) {
        this.setName(name);
        this.setCoordinate(lat, lon);
        this.setPrice( price );
        this.damage = damage;
    }

    //Methods
    public void shoot( Fighter fighter ){
    }
}
