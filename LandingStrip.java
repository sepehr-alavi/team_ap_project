
public class LandingStrip extends IdentifiableObject{
    private Coordinate begginingTreshold;
    private Coordinate endTreshold;

    //Getters and setters
    public Coordinate getBegginingTreshold() {
        return begginingTreshold;
    }

    public void setBegginingTreshold(Coordinate begginingTreshold) {
        this.begginingTreshold = begginingTreshold;
    }

    public Coordinate getEndTreshold() {
        return endTreshold;
    }

    public void setEndTreshold(Coordinate endTreshold) {
        this.endTreshold = endTreshold;
    }

    //Constructor
    public LandingStrip( String name, double begginingTresholdLat, double begginingTresholdLon, double endTresholdLat, double endTresholdLon ){
        this.begginingTreshold.setLat( begginingTresholdLat );
        this.begginingTreshold.setLon( begginingTresholdLon );
        this.endTreshold.setLat( endTresholdLat );
        this.endTreshold.setLon( endTresholdLon );
    }
}
