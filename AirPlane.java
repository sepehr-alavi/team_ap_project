//Checked

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;

public class AirPlane extends Aircraft {
    private int passengersCount;
    Image image = new Image("Media\\Airliner.png");
    ImageView ax = new ImageView(image);
    LineTo destination;
    int x, y;

    //Getter and setter
    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }


    //Constructor
    public AirPlane( double x, double y, double speed, int passengersCount) {
        this.setCoordinate(x, y);
        this.setSpeed(speed);
        this.passengersCount = passengersCount;
        ax.setFitWidth(85);
        ax.setFitHeight(65);
        setBearing(Math.atan(((this.getCoordinate().getY() - this.y) / (this.getCoordinate().getX() - this.x)))* (180 / Math.PI));
        ax.setRotate(180 + getBearing());
        if( this.getCoordinate().getX() > 600 )
            ax.setRotate(ax.getRotate());
    }

    //Methods
}
