public class PassengerListing extends AListing{

    //fields
    int dateCreated = 0;
    int dateOfTrip = 0;
    int timeOfTrip = 0;
    String startLocation = "";
    String endLocation = "";
    private int listingID = 0;
    int seatsRequired = 0;

    //constructor
    protected PassengerListing(int date, int time, String start, String end,int listingID,int seats){
        this.dateOfTrip = date;
        this.timeOfTrip = time;
        this.startLocation = start;
        this.endLocation = end;
        this.listingID = listingID;
        this.seatsRequired = seats;
    }
    public int getListingID() {
        return listingID;
    }

    @Override
    public String toString() {
        return "[Passenger] Listing: " +
               // "\n Created: " + dateCreated +
                "\n Date: " + dateOfTrip +
                "\n Time: " + timeOfTrip +
                "\n Start: " + startLocation +
                "\n End: " + endLocation +
                "\n Seats needed: " + seatsRequired;
    }
}

