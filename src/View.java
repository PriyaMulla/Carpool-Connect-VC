import java.util.Scanner;

public class View {


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
