#Create a Listing

###Scope
* Account management system
###Level
* User goal
###Primary actor
* User
###Stakeholders and interest
* User: Wants an easily readable system to create an account and easily gain access to the application
###Preconditions
* User needs to have a Vassar email address
###Postconditions
* User has created an account
* A new account is stored in the database
* User gained the ability to interact with the app either by:
  1. Creating a trip 
  2. Accepting driver offers
###Main success scenario
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
###Extensions
6a. System can’t find the substring “@vassar.edu”:
1. System outputs the error message “Enter a valid email address” 
2. User repeats step 6
3. 
10a. If user chose the role “Driver”:
1. System asks user if they have a car 
2. User responds yes or no

14a. If user inputs a username that don’t meet the requirements:
1. System outputs the error message “Enter a valid username” 
2. Repeat step 14

16a. User inputs a password that don’t meet the requirements
1. System outputs the error message “Enter a valid password” 
2. Repeat step 16

18a. User inputs a string that does not match their string for their password:
1. System outputs the error message “Does not match password” 
2. User correctly inputs their password

20a. User left sections blank:
1. System highlights blank sections and outputs required error messages 
2. User is required to repeat specific steps

21a. System finds the email address already registered to another account:
1. System outputs “An account already exists with the current email address” 
2. System prompts user to login/signup page

###Special requirements
* System displays creation page in a form-like format
* Date, time and phone number formatting
* Text is easily readable

###Technology and data variations list
###Frequency of occurrence
###Miscellaneous
* Confirming email helps increase security and reliability of
