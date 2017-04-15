
public class MachineGun extends AntiAircraft implements AntiAircraftFunction{
    private double damage;

    //Getter and setter
    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    //Constructor
    public MachineGun( String name, int price, double range, double damage ) {
        this.setName(name);
        this.setPrice( price );
        this.setRange( range );
        this.damage = damage;
    }

    //Methods
    public void shoot( Fighter fighter ){

    }
}
