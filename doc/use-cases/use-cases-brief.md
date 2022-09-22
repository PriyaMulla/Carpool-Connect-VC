#Use cases brief

##Carpooling- Passenger POV

1. A student needs a ride going to a specific location
2. They select the role of "Passenger"
3. They input their location and time
4. Select the desired driver and receives driver's contact info
5. The driver and passenger agree to each other's conditions
6. The student clicks confirm and reserves a spot
7. The student changes the listing to "resolved"

### Passenger v. Passenger match
1. Student 1 finds that Student 2 going to the similar locations and time
2. Student 1 receives Student 2's contact info and messages him
3. They agree to travel together through an alternate solution
4. They both change the states of their listings to "resolved"

booking a ride

                            special req - Android studio


set up future trip?
removing a listing 
        Driver POV- would it send a notification to passengers
editing a listing 
accepting the trip
        making a group
Searching listings
save accounts to database (assigned userid)

Preconditions - Car, Vassar student/faculty?

##Carpooling- Driver POV
1. A student wants to drive to a specific location
2. They select the role of "Driver"
3. They make a listing with the desired location, the desired time and how many people they are willing to take
4. The Driver gets notified about a potential passenger through contact info
5. The Driver messages the Passenger and agree to each other's conditions
6. Their listing updates with the necessary steps

##Creating an account
1. Click sign up button
2. Ask for Name and Last Name
3. Ask for email address (isolate vassar users)
4. Ask for phone number
5. Ask to choose a role (Driver or Passenger) <--- Extension
6. Add a picture
7. Set a username
8. Set & confirm a password
9. Click sign up
10. Send a confirmation email?


###Switching Roles
1. Click on profile
2. Click on Switch role
3. Click on desired role <-- Extension depending on what you click and checks if new driver
4. Click accept

###Editing account
1. Go to profile
2. Click edit profile
3. Edit possible options
4. click accept

###Delete account
1. Go to edit profile
2. Scroll to "Delete" section
3. Click "Delete account" button
4. Outputs "Are you sure you want to delete the account? All information will be deleted"
5. click accept
6. Returns user to login/signup screen

##Logging in
1. Ask for your username
2. Ask for your password <-- Extension (if forgot password then what)
3. Click log in

##Logging out
1. Go to menu 
2. Scroll to bottom
3. Click Log out
4. Outputs "Are you sure you want to log out?"
5. Click "Yes"

##Creating Listings
(differ for Driver and Passenger)

<-- Extension what happens if it's a future trip that was scheduled or trips other than current day
LAST - uploads to page of Listings
##Searching Listings
## Removing Listings
<-- Extension If Listing had passengers assigned to it
##Saving user accounts to Database
1. System detects new account
2. Pushes new account to database
3. Assigns account a userId