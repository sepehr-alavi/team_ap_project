
public class Aircraft extends GeometricalObject{
    private double speed;
    private double enteringTime;

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

    //Methods
    public boolean checkArivval (){
        if( controlTower.distance( this.getCoordinate(), controlTower.airPort.getCoordinate() ) <= 0.1 )
            return true;

        else return false;
    }
}
