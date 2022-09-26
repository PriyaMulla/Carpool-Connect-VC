#Use cases brief

##Create an account
1. Click sign up button
2. System sends user to Sign up page
3. System asks for Name and Last Name
4. User enters their Name and Last Name
5. System asks for an email address with an ending of "@vassar.edu"
6. User enters email address 
7. System asks for a phone number
8. User enters their phone number
9. System asks to choose a role (Driver or Passenger) 
10. User chooses their role
11. System gives an option to add a picture
12. User adds their desired picture
13. System asks to input a username
14. User inputs their username 
15. System asks to input a password
16. User inputs a password with the correct format
17. System asks to confirm their password
18. User retypes their password
19. User clicks the sign-up button
20. System checks that all fields are correct and not empty
21. System sends a confirmation email to user's email
22. System creates the account and sends to database

###Switch Roles
1. System brings up a box with the options : Driver and Passenger
2. User chooses their desired role and click accepts 
3. System updates profile to reflect chosen role

###Edit account
1. System brings up the edit page with possible edits that can be made
2. User edits profile as they wish and clicks accept
3. System checks that fields are correct and not empty
4. System submits changes to user's account

###Delete account
1. User scrolls down to "Delete" section on the edit page
2. User clicks "Delete account" button
3. System outputs "Are you sure you want to delete the account? All information will be deleted"
4. User clicks accept
5. System returns user to login/signup screen
6. System deletes account from database

##Log in
1. System asks for a username
2. User inputs their username
3. System asks for a password 
4. User inputs their password
5. User clicks the "log in" button
6. System checks if the pair (Username,password) is in the database
7. System loads up their account and sends user to the home screen

##Log out
1. User clicks the "Log-out" button
2. System outputs "Are you sure you want to log out?"
3. User clicks "Yes"
4. System returns user to login/signup screen

##Create a Listing
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

##Write a Review
1. System asks if the individual was a driver or passenger
2. User selects the role
3. System asks user to input how many stars they give
4. User selects the stars
5. System asks to input a comment about the individual
6. User writes their comment and clicks accept
7. System uploads review to the target's profile at the corresponding section

##Search for a Listing 
1. System prompts a Search bar 
2. User inputs desired place 
3. System matches input with places from Map data 
4. System shows the user options to help choose the accurate address
5. User clicks on the correct place
6. System shows results of listings matching the place inputted
7. User clicks the "filter" button
8. System opens up the "filter" subpage that shows time, range, and driver/passenger option
9. User inputs desired options and clicks accept
10. System updates the results to reflect the user's filter options

##Remove a Listing
1. System brings up an overview of the listing 
2. User clicks on the "edit" button
3. System brings up the editable page
4. User clicks the "Delete listing" button
5. System outputs "Are you sure you want to delete this listing?"
6. User clicks "Yes" button 
7. System removes the listing from the Listings page

##Hide a Listing
1. System brings up an overview of the listing
2. User clicks the "hide" button
3. System makes the listing unavailable in the Listings page
4. System stores the listing

##Show a Listing
1. User clicks on a hidden listing
2. System brings up an overview of the listing
3. User clicks the "show" button
4. System makes the listing available in the Listings page

##Edit a Listing
1. System brings up an overview of the listing 
2. User clicks the "Edit" button
3. System brings up the edit page
4. User edits time and place and clicks Accept
5. System updates the listing

##Accept a Listing
1. System brings up an overview of the listing
2. User clicks the "Interested" button
3. System shows individual's contact information

##Save user account to Database
1. System detects a new account was made
2. System sends new account information to database
3. Assigns account a userId?