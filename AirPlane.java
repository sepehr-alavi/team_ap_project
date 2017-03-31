
public class AirPlane extends Aircraft{
    private int passengersCount;

    //Constructor
    public AirPlane( String name, double lat, double lon, double speed, int enteringTime, int passengersCount ){
        this.setName( name );
        this.setCoordinate( lat, lon );
        this.setSpeed( speed );
        this.setEnteringTime( enteringTime );
        this.passengersCount = passengersCount;
    }
}
