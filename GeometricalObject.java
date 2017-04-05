
public class GeometricalObject extends IdentifiableObject{
    private Coordinate coordinate;

    //Setters and getters
    public void setCoordinate( double lat, double lon ) {
        this.coordinate.setLat( lat );
        this.coordinate.setLon( lon );
    }
}