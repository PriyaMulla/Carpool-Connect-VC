import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;

public class Controller{
    List<AListing> PageOfListings = new ArrayList<>();
    //Account creation
    public boolean isValidUsername(String input) {
        return input.length() > 5;
    }
    public boolean isValidPassword(String input){
        return (input.length() > 5) && (Character.isUpperCase(input.charAt(0))) & ((input.contains("!"))|(input.contains("_"))|(input.contains("/")) | (input.contains("*")) | (input.contains("@")) | (input.contains("#")) | (input.contains("$")) | (input.contains("&")));
    }
    public boolean isValidEmail(String input){
        return ((input.contains("@vassar.edu")) && (input.length() > 11));
    }
    public boolean isValidDate(int input){
        return input >= 99999999;
    }
    public boolean isValidTime(int input){
        return input >= 9999;
    }
    public Account createAccount(String username, String password,String name,String email){
        Account acc = new Account(username,password,name,email);
        return acc;
    }

    public AListing createListing(int date, int time, String start, String end, int listingID,int seats, int who){
        //An attribute would be the current time and maybe date
        //turn the string of a date into actual time and then compare
        AListing listy;
        if(who == 0) {
            listy = new PListing(date, time, start, end,listingID,seats);
        } else{
            listy = new DListing(date,time,start,end,listingID,seats);
        }
        PageOfListings.add(listy);
        return listy;
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
