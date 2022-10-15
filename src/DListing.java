public class DListing extends AListing{

    //fields
    int dateCreated =0;
    int dateOfTrip =0;
    int timeOfTrip =0;
    String startLocation ="";
    String endLocation ="";
    int listingID =0;
    int seats =0;

    protected DListing(int date, int time, String start, String end, int listingID, int seats){
        this.dateOfTrip = date;
        this.timeOfTrip = time;
        this.startLocation = start;
        this.endLocation = end;
        this.listingID = listingID;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "[Driver] Listing: " +
                // "\n Created: " + dateCreated +
                "\n Date: " + dateOfTrip +
                "\n Time: " + timeOfTrip +
                "\n Start: " + startLocation +
                "\n End: " + endLocation +
                "\n Seats available: " + seats;
    }
}