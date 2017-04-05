
public class AirPort extends GeometricalObject{
    private int hp;

    //Getter and setter
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    //Constructor
    public AirPort( String name, double lat, double lon, int hp ){
        this.setName( name );
        this.setCoordinate( lat, lon );
        this.hp = hp;
    }

    //Methods
    public void takeDamage(){
    }

    public void destroy(){
    }

}
