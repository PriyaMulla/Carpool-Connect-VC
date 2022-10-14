import java.util.*;

import static java.lang.Integer.parseInt;

public class View {

    static Controller c = new Controller();

    public static void main(String[] args){
        //Account info

        System.out.println("CREATE ACCOUNT");
        Scanner scan = new Scanner(System.in);

        //username
        System.out.println("Enter a username (6 characters+):");
        String user = scan.nextLine();
        while (!c.isValidUsername(user)){
            System.out.println("ERROR not valid username");
            System.out.println("Enter a username that is 6 characters or more:");
            user = scan.nextLine();
        }

        //password
        System.out.println("Enter a password (6 characters+, first character upper case, at least 1 special character (/,*,@,#,$,&):");
        String password = scan.nextLine();
        while (!c.isValidPassword(password)){
            System.out.println("ERROR not valid password");
            System.out.println("Enter a password that is longer than 5 characters, first character is upper case, at least one special character (/,*,@,#,$,&):");
            password = scan.nextLine();
        }

        //name
        System.out.println("Enter your name");
        String name = scan.nextLine();
        while (name.equals("")){
            System.out.println("ERROR not valid name");
            System.out.println("Enter a name:");
            name = scan.nextLine();
        }

        //email
        System.out.println("Enter an email address");
        String email = scan.nextLine();
        while (!c.isValidEmail(email)){
            System.out.println("ERROR not valid email");
            System.out.println("Enter a vassar email address:");
            email = scan.nextLine();
        }
        //Account creation
        Account acc = c.createAccount(user, password, name, email);
        System.out.println(acc.toString());

        /////////////////////

        //listing info
        System.out.println("\n CREATE LISTING");

        //date
        boolean error = true;
        int date = 0;
        System.out.println("Enter a date (10-14-2022 should be written 10142022):");
        String dates = scan.nextLine();
        //String dates = "";
        while (error && !c.isValidDate(dates)) {
            try {
                System.out.println("Enter a date (10-14-2022 should be written 10142022):");
                dates = scan.nextLine();
                date = parseInt(dates);
                error = false;
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }

        //time
        error = true;
        int time =0;
        System.out.println("Enter a time (1:30 pm should be written 1330):");
        String times = scan.nextLine();
        while (error && !c.isValidTime(times)) {
            try {
                System.out.println("Enter a time (1:30 pm should be written 1330):");
                times = scan.nextLine();
                time = parseInt(times);
                error = false;
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }

        //start
        System.out.println("Enter a starting location:");
        String start = scan.nextLine();
        while (start.equals("")){
            System.out.println("ERROR not valid location");
            System.out.println("Enter a starting location:");
            start = scan.nextLine();
        }

        //end
        System.out.println("Enter an ending location:");
        String end = scan.nextLine();
        while (end.equals("")){
            System.out.println("ERROR not valid location");
            System.out.println("Enter a ending location:");
            end = scan.nextLine();
        }


        //listing creation
        System.out.println("Enter P if passenger, or D if driver");
        String role = scan.nextLine();
        while (!role.equals("P") && !role.equals("D")){
            System.out.println("ERROR enter P or D");
            role = scan.nextLine();
        }


        //passenger
        if (role.equals("P")){

            //seats
            error = true;
            int seat =0;
            while (error) {
                try {
                    System.out.println("How many seats do you need?");
                    String seats = scan.nextLine();
                    seat = parseInt(seats);
                    error = false;
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
            }

            //make new listing
            System.out.println("Your Listing:");
            AListing newListing = c.createListing(date, time, start, end, 1, seat, 0);
            System.out.println(newListing.toString());
        }
        //driver
        else {

            //seats
            error = true;
            int seat =0;
            while (error) {
                try {
                    System.out.println("How many seats do you have?");
                    String seats = scan.nextLine();
                    seat = parseInt(seats);
                    error = false;
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
            }

            //make new listing
            System.out.println("Your Listing:");
            AListing newListing = c.createListing(date, time, start, end, 1, seat, 1);
            System.out.println(newListing.toString());
        }

        //print PageOfListings
        System.out.println("\n Page of Listings:");
        System.out.println(c.PageOfListings.toString());

    }
}
