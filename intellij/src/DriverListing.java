public class DriverListing extends AListing{



    protected DriverListing(int date, int time, String start, String end, int listingID, int seats){
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
