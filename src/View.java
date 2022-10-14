import java.util.*;

import static java.lang.Integer.parseInt;

public class View {

    static Controller c = new Controller();

    public static void main(String[] args){
        //Account info

        System.out.println("CREATE ACCOUNT");

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a username:");
        String user = scan.nextLine();

        while (c.isValidString(user)){
            System.out.println("ERROR not valid username");
            System.out.println("Enter a username:");
            user = scan.nextLine();
        }

        System.out.println("Enter a password:");
        String password = scan.nextLine();

        while (c.isValidString(password)){
            System.out.println("ERROR not valid password");
            System.out.println("Enter a password:");
            password = scan.nextLine();
        }

        System.out.println("Enter your name");
        String name = scan.nextLine();

        System.out.println("Enter an email address");
        String email = scan.nextLine();

        while (c.isValidString(email)){
            System.out.println("ERROR not valid email");
            System.out.println("Enter an email address:");
            email = scan.nextLine();
        }
        //Account creation
        Account acc = new Account(user, password, name, email);

        //listing creation
        System.out.println("CREATE LISTING");

        System.out.println("Enter a date:");
        String dates =  scan.nextLine();
        int date = parseInt(dates);

        System.out.println("Enter a time:");
        String times = scan.nextLine();
        int time = parseInt(times);


        System.out.println("Enter a starting location:");
        String start = scan.nextLine();

        System.out.println("Enter an ending location:");
        String end = scan.nextLine();

        System.out.println("If a driver enter how many seats your car has, if passenger enter how many seats you need");
        String seats = scan.nextLine();
        int seat = parseInt(seats);


        System.out.println("Enter P if passenger, or D if driver");
        String role = scan.nextLine();
        while (role.equals("P") | role.equals("D")){
            System.out.println("ERROR enter P or D");
            role = scan.nextLine();
        }
        if (role.equals("P")){
            DListing driverListing = new DListing(date, time, start, end, 1, seat);
        }
        else if (role.equals("D")){
            PListing driverListing = new PListing(date, time, start, end, 1, seat);
        }
    }
}
