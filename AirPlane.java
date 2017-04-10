
public class AirPlane extends Aircraft{
    private int passengersCount;
    private double bearing;

    //Getter and setter
    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }

    public double getBearing() {
        return bearing;
    }

    public void setBearing(double bearing) {
        this.bearing = bearing;
    }

    //Constructor
    public AirPlane( String name, double lat, double lon, double speed, double enteringTime, int passengersCount ){
        this.setName( name );
        this.setCoordinate( lat, lon );
        this.setSpeed( speed );
        this.setEnteringTime( enteringTime );
        this.passengersCount = passengersCount;
    }

    //Methods
    public boolean checkTwoAirPlanesCollision( AirPlane airplane ){
        if( controlTower.distance( this.getCoordinate(), airplane.getCoordinate() ) <= 0.1 )
            return true;

        else return false;
    }

    public boolean checkArivval (){
        if( controlTower.distance( this.getCoordinate(), controlTower.airPort.getCoordinate() ) <= 0.1 )
            return true;

        else return false;
    }
}
