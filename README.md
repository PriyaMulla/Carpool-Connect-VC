# Read Me
## Iteration 1
In this prototype a user is able to create an account and a listing. At the moment, there can only be one user and one listing, but that listing does get saved to an array. The account takes in a username, password, email, and name–all which have simplified restrictions. For example, password requires an upper case letter at the beginning, a special character, and to be longer than 5 characters. Listings take in a time, date, start and end location, how many seats, and if you’re the driver or passenger. The time, date only accept a specific type of int and start and end location accept any type of (non-empty) string for now.

To run the prototype enter the View class and run its main() method. Then the program will give instructions on how to create an account and listing. 

## Iteration 2
In this prototype a user is able to create an account which is then added to a Collection of Accounts. Once this is done they have an option of adding a listing or filtering. They can create and add a listing to Page of listings (at the moment there is something buggy with oncreate for listings, but we will fix that at the start of next iteration). We have started work on the dashboard, but not fully implemented it yet, so a user can not yet see their created listing or other's. For filtering listings, during testing, we found an issue when the filterListings method tries to add a listing that matches the criteria to a new page of listings. It technically works if there are no matches to the given criteria and returns a null page of listings. Because of this the filter tests in both test and androidtest are unfinished. One of the first things we will do in iteration 3 is fix this issue and finish the tests. 

