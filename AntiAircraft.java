//Checked

import java.util.ArrayList;

abstract public class AntiAircraft extends GeometricalObject{
    private int price;
    private double range;
    static ArrayList<Fighter> fighterInRange = new ArrayList<Fighter>();

    //Getter and setter
    public int getPrice() {

        return price;
    }

    public void setPrice(int price) {

        this.price = price;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    abstract public void shoot( Fighter fighter );
}
