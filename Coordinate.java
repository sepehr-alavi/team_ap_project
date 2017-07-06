//Checked

public class Coordinate {
    private double lat;
    private double lon;
    private double x;
    private double y;

    //Getters and setters
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
        this.y = 800/180*(90 - lat);
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
        this.x = 1200/360*(180 + lon);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        this.lon = (1200/360 * x) - 180;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        this.lat = -1*(800/180*y) - 90;
    }
}
