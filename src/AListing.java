public abstract class AListing {

    //fields
    int dateCreated;
    int dateOfTrip;
    int timeOfTrip;
    String startLocation;
    String endLocation;
    int listingID;
    
    //constructor
    public AListing(int date, int time, String start, String end, int listingID){
        this.dateOfTrip = date;
        this.timeOfTrip = time;
        this.startLocation = start;
        this.endLocation = end;
        this.listingID = listingID;
    }

    protected AListing(){}


    @Override
    public String toString() {
        return "AListing{" +
                "dateCreated=" + dateCreated +
                ", dateOfTrip=" + dateOfTrip +
                ", timeOfTrip=" + timeOfTrip +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", listingID=" + listingID +
                '}';
    }
}
