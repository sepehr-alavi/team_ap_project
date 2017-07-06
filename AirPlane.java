//Checked

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AirPlane extends Aircraft {
    private int passengersCount;
    Circle ax = new Circle(5, Color.RED);

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
       // this.setEnteringTime(enteringTime);
        this.passengersCount = passengersCount;
    }

    //Methods
}
