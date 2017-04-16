//Checked

public class MachineGun extends AntiAircraft implements AntiAircraftFunction{
    private int damage;

    //Getter and setter
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    //Constructor
    public MachineGun( String name, int price, double range, int damage ) {
        this.setName(name);
        this.setPrice( price );
        this.setRange( range );
        this.damage = damage;
    }

    //Methods
    public void shoot( Fighter fighter ){
        fighter.setHp( fighter.getHp() - this.damage );
        if ( fighter.getHp() <= 0 ){
            this.fighterInRange.remove(fighter);
            controlTower.savedAircrafts.remove(fighter);
            controlTower.savedAircrafts.remove(fighter);
        }
    }
}
