import com.sun.deploy.net.cookie.DeployCookieSelector;

public class LandingStrip extends IdentifiableObject{
    private Coordinate begginingTreshold;
    private Coordinate endTreshold;

    public Coordinate getBegginingTreshold() {
        return begginingTreshold;
    }

    public void setBegginingTreshold( double lat, double lon ) {
        this.begginingTreshold.setLat( lat );
        this.begginingTreshold.setLon( lon );
    }

    public Coordinate getEndTreshold() {
        return endTreshold;
    }

    public void setEndTreshold( double lat, double lon ) {
        this.begginingTreshold.setLat( lat );
        this.begginingTreshold.setLon( lon );
    }
}
