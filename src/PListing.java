public class PListing extends AListing{

    //fields
    int dateCreated = 0;
    int dateOfTrip = 0;
    int timeOfTrip = 0;
    String startLocation = "";
    String endLocation = "";
    private int listingID = 0;
    int seatsRequired = 0;

    //constructor
    private PListing(int date, int time, String start, String end,int listingID,int seatsRequired){
        this.dateOfTrip = date;
        this.timeOfTrip = time;
        this.startLocation = start;
        this.endLocation = end;
        this.listingID = listingID;
    }
    public int getListingID() {
        return listingID;
    }

    @Override
    public String toString() {
        return "PListing{" +
                "dateCreated=" + dateCreated +
                ", dateOfTrip=" + dateOfTrip +
                ", timeOfTrip=" + timeOfTrip +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", listingID=" + listingID +
                ", seats=" + seatsRequired +
                '}';
    }
}

