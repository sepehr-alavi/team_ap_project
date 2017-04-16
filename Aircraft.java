//Checked

public class Aircraft extends GeometricalObject {
    private double speed;
    private double enteringTime;
    private double bearing;
    private Coordinate destination=new Coordinate();


    //Getters and setters
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getEnteringTime() {
        return enteringTime;
    }

    public void setEnteringTime(double enteringTime) {
        this.enteringTime = enteringTime;
    }

    public double getBearing() {
        return bearing;
    }

    public void setBearing(double bearing) {
        this.bearing = bearing;
    }

    public Coordinate getDestination() {
        return destination;
    }

    public void setDestination(Coordinate destination) {
        this.destination = destination;
    }
}
