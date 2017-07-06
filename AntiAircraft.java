//Checked

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

abstract public class AntiAircraft extends GeometricalObject{
    private int price;
    private double range;
    static ArrayList<Fighter> fighterInRange = new ArrayList<Fighter>();
    Image image ;
    ImageView ax ;

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
