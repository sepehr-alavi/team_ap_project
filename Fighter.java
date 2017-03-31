
public class Fighter extends Aircraft{
    private int hp;

    //Constructor
    public Fighter(  String name, double lat, double lon, double speed, int enteringTime, int hp ){
        this.setName( name );
        this.setCoordinate( lat, lon );
        this.setSpeed( speed );
        this.setEnteringTime( enteringTime );
        this.hp = hp;
    }

    //Methods
    public void destroy(){
    }
}
