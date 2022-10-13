import java.util.LinkedList;
import java.util.Scanner;

public class Controller {

    //methods
    public boolean isValidString(){
        return false;
    }

    public void createAccount(){
        return;
    }

    public void createListing(){
        return;
    }

    public LinkedList<Listing> searchListings(){
        return new LinkedList<Listing>();
    }

    public LinkedList<Listing> filterListings(){
        return new LinkedList<Listing>();
    }

    public static void main(String[] args){
        System.out.println("Hello and welcome to account creation");

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a username:");
        String user = scan.nextLine();

        System.out.println("Enter a password:");
        String password = scan.nextLine();

        System.out.println("Enter your name");
        String name = scan.nextLine();

        System.out.println("Enter an email address");
        String email = scan.nextLine();
    }
}
