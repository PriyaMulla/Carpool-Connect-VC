#Create a Listing

###Scope
* Listing Management system

###Level
* User goal

###Primary Actor
* Driver or Passenger

###Stakeholders and Interest
* Driver: Wants an easily readable system with easy, accurate Listing creation. Wants to easily post their trips on the page of Listings
* Passenger: Wants an easily readable system with easy, accurate Listing creation. Wants to easily post the times they want to take a specified trip

###Preconditions 
* The user should have an account created
* The user should be logged in

###Postconditions
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
2. User repeats steps 3-4

8a. User inputs a date that has passed:
1. System outputs the error message "Date has passed"
2. User inputs a new date

8b. User inputs a time that has passed for current day:
1. System outputs the error message "Time has passed"
2. User inputs a new time

10a. User chose Yes:
1. System prompts a subpage to choose the days desired
2. User inputs their desired days

14a. User's role was not chosen:
1. System highlights Role section and outputs an error message
2. User repeats step 2

14b. User left a section(s) blank:
1. System highlights the empty sections
2. User enters the required information

14c. User did not input an accurate location:
1. System highlights Location section and outputs an error message
2. User repeats steps 3-5

###Special requirements
* System displays Creation page in a form-like format
* Date and time formatting
* Text is easily readable

###Technology and data variations list

###Frequency of occurrence 
This use case will occur everytime a user wants to create new listing

###Miscellaneous 