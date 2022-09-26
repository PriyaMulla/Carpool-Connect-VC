#Create a Listing

###Scope
* Listing creation system
###Level
* User goal

###Primary Actor
* Driver or Passenger
###Stakeholders and Interest

###Preconditions 
* The user should have an account created
* The user should be logged in
###Post-conditions
* A new listing has been created
* The new listing is saved in "My Listings" page
* The page of Listings is updated
###Main success scenario
1. System asks if the user is a Driver or a Passenger
2. User chooses their role
3. System asks to input desired place
4. System matches input with places from Map data
5. System shows the user options to help choose the accurate address
6. User clicks on the correct place
7. System asks to input the date and time
8. User inputs their desired date and time
9. System records date and time
10. System asks if the listing will be a recurrent event
11. User chooses an option
12. System records the option chosen
13. User clicks "Create"
14. System checks that all fields were correctly filled
15. System creates the listing and posts it to the Listings page
###Extensions
2a. Role of Driver was chosen:
1. System asks to input the number of seats in their car
2. User inputs the number of seats

4a. System is unable to find a match:
1. System outputs "No results"
2. User reenters location

8a. User inputs a date that has passed:
1. System outputs
###Special requirements

###Technology and data variations list

###Frequency of occurrence 

###Miscellaneous 