import java.util.LinkedList;
import java.util.List;

public class Controller{
    String string = "Java";
    String substring = "va";

//System.out.println(string.contains(substring));
    //methods
    public boolean hasSpecialChar(){
//*/@#$&
    }

    public boolean isValidUsername(String input) {
        if(input.length() > 5){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isValidPassword(String input){
        if(input.length() > 5)
    }
    public boolean isValidEmail(String input){

    }
     public boolean isValidString(String input){
        if(input.equals(input.compareTo("user"))){
            if((input.length() > 5) & (Character.isUpperCase(input.charAt(0))) & ((input.contains("/")) |(input.contains("*")) | (input.contains("@")) | (input.contains("#")) | (input.contains("$")) | (input.contains("&")))){
                return true;
            }
            else {
                return false;
            }

        } else if(input.equals(input.compareTo("email"))){

        }

        return false;
    }

    public void createAccount(){
        return;
    }

    public void createListing(){
        return;
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
