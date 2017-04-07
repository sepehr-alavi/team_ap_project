
public class Fighter extends Aircraft{
    private int hp;

    //Getter and setter
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    //Constructor
    public Fighter(  String name, double lat, double lon, double speed, double enteringTime, int hp ){
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
