//Checked

public class GeometricalObject extends IdentifiableObject {
    private Coordinate coordinate = new Coordinate();

    //Setters and getters
    public void setCoordinate(double x, double y) {

        this.coordinate.setX(x);
        this.coordinate.setY(y);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

}
