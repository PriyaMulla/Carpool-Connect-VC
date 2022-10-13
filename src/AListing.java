public abstract class AListing {

    //fields
    int dateCreated = 0;
    int dateOfTrip = 0;
    int timeOfTrip = 0;
    String startLocation = "";
    String endLocation = "";
    int listingID = 0;
    
    //constructor
    private AListing(int date, int time, String start, String end, int listingID){
        this.dateOfTrip = date;
        this.timeOfTrip = time;
        this.startLocation = start;
        this.endLocation = end;
        this.listingID = listingID;
    }


    @Override
    public String toString() {
        return "Listing{" +
                "dateCreated=" + dateCreated +
                ", dateOfTrip=" + dateOfTrip +
                ", timeOfTrip=" + timeOfTrip +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", listingID=" + listingID +
                '}';
    }
}
