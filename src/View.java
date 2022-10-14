import java.util.*;

import static java.lang.Integer.parseInt;

public class View {

    static Controller c = new Controller();

    public static void main(String[] args){
        //Account info

        System.out.println("CREATE ACCOUNT");

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a username (6 characters+):");
        String user = scan.nextLine();

        while (!c.isValidUsername(user)){
            System.out.println("ERROR not valid username");
            System.out.println("Enter a username that is 6 characters or more:");
            user = scan.nextLine();
        }

        System.out.println("Enter a password (6 characters+, first character upper case, at least 1 special character (/,*,@,#,$,&):");
        String password = scan.nextLine();

        while (!c.isValidPassword(password)){
            System.out.println("ERROR not valid password");
            System.out.println("Enter a password that is longer than 5 characters, first character is upper case, at least one special character (/,*,@,#,$,&):");
            password = scan.nextLine();
        }

        System.out.println("Enter your name");
        String name = scan.nextLine();

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

        //listing creation
        System.out.println("CREATE LISTING");

        boolean error = true;
        int date = 0;
        while (error) {
            try {
                System.out.println("Enter a date (10-14-22 should be written 101422):");
                String dates = scan.nextLine();
                date = parseInt(dates);
                error = false;
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }

        error = true;
        int time =0;
        while (error) {
            try {
                System.out.println("Enter a time (1:30 pm should be written 1330):");
                String times = scan.nextLine();
                time = parseInt(times);
                error = false;
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }


        System.out.println("Enter a starting location:");
        String start = scan.nextLine();

        System.out.println("Enter an ending location:");
        String end = scan.nextLine();

        System.out.println("If a driver enter how many seats your car has, if passenger enter how many seats you need");
        String seats = scan.nextLine();
        int seat = parseInt(seats);


        System.out.println("Enter P if passenger, or D if driver");
        String role = scan.nextLine();
        while (!role.equals("P") && !role.equals("D")){
            System.out.println("ERROR enter P or D");
            role = scan.nextLine();
        }
        if (role.equals("P")){
            AListing newListing = c.createListing(date, time, start, end, 1, seat, 0);
            System.out.println(newListing.toString());
        }
        else if (role.equals("D")){
            AListing newListing = c.createListing(date, time, start, end, 1, seat, 1);
            System.out.println(newListing.toString());
        }

    }
}
