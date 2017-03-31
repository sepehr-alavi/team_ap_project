
public class LandingStrip extends IdentifiableObject{
    private Coordinate begginingTreshold;
    private Coordinate endTreshold;

    //Constructor
    public LandingStrip( String name, double begginingTresholdLat, double begginingTresholdLon, double endTresholdLat, double endTresholdLon ){
        this.begginingTreshold.setLat( begginingTresholdLat );
        this.begginingTreshold.setLon( begginingTresholdLon );
        this.endTreshold.setLat( endTresholdLat );
        this.endTreshold.setLon( endTresholdLon );
    }
}
