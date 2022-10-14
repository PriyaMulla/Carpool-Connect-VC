import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.*;

//Change toString to follow the format that we like
public class Controller{
//What if they press enter? for view
    //Account creation
    public boolean isValidUsername(String input) {
        return input.length() > 5;
    }
    public boolean isValidPassword(String input){
        return (input.length() > 5) & (Character.isUpperCase(input.charAt(0))) & ((input.contains("/")) | (input.contains("*")) | (input.contains("@")) | (input.contains("#")) | (input.contains("$")) | (input.contains("&")));
    }
    public boolean isValidEmail(String input){
        return ((input.contains("@vassar.edu")) & (input.length() > 11));
    }

    //Listing creation?
    public Account createAccount(String username, String password,String name,String email){
        Account acc = new Account(username,password,name,email);
        return acc;
    }

    public void createListing(int date, int time, String start, String end){
        //An attribute would be the current time and maybe date
        //turn the string of a date into actual time and then compare
        //should Listing listy = new Listing(etc) be put here
        //And createListing() be put in the view class

        AListing listy;
        new PListing(date,time,start,end);
    }
    HashMap<Integer, Object> hash_map = new HashMap<Integer, Object>();
//Method to add a Listing to PageOfListings
    public void addToList(Object listing){
        hash_map.put(listing.get(listingID));
    }
    public List<AListing> searchListings(){
        return new LinkedList<AListing>();
    }

    public List<AListing> filterListings(){
        return new LinkedList<AListing>();
    }

    public static void main(String[] args){

    }
}
