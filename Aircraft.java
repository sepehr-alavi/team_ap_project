
public class Aircraft extends GeometricalObject {
    private double speed;
    private double enteringTime;
    private double bearing;
    private Coordinate destination=new Coordinate();


    //Getters and setters
    public double getSpeed() {
        return speed;
    }

    public Coordinate getDestination() {
        return destination;
    }

    public void setDestination(Coordinate destination) {
        this.destination = destination;
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

    //Methods
    public boolean checkArivval() {
        if (controlTower.distance(this.getCoordinate(), controlTower.airPort.getCoordinate()) <= 0.1)
            return true;

        else return false;
    }
}
