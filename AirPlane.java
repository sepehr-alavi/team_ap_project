//Checked

public class AirPlane extends Aircraft {
    private int passengersCount;


    //Getter and setter
    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }


    //Constructor
    public AirPlane(String name, double lat, double lon, double speed, double enteringTime, int passengersCount) {
        this.setName(name);
        this.setCoordinate(lat, lon);
        this.setSpeed(speed);
        this.setEnteringTime(enteringTime);
        this.passengersCount = passengersCount;
    }

    //Methods
}
