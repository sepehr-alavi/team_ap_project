
public class AirPort extends GeometricalObject {
    private int hp;
    private int level = 0;

    //Getter and setter
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    //Constructor
    public AirPort(String name, double lat, double lon, int hp) {
        this.setName(name);
        this.setCoordinate(600 ,400);
        this.hp = hp;
    }

}
