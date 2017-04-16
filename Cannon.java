//Checked

import java.util.Random;

public class Cannon extends AntiAircraft implements AntiAircraftFunction{
    private double missChance;
    private double fireRate;

    //Getters and setters
    public double getMissChance() {
        return missChance;
    }

    public void setMissChance(double missChance) {
        this.missChance = missChance;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }

    //Constructor
    public Cannon( String name, int price, double range, double missChance , double fireRate ) {
        this.setName(name);
        this.setPrice( price );
        this.setRange( range );
        this.missChance = missChance;
        this.fireRate = fireRate;
    }

    //Methods
    public void shoot( Fighter fighter ){
        Random r = new Random();
        int persentage = r.nextInt(100) + 1;
        if ( persentage <= this.getMissChance()){
            this.fighterInRange.remove(fighter);
            controlTower.savedAircrafts.remove(fighter);
            controlTower.savedAircrafts.remove(fighter);
        }
    }
}
