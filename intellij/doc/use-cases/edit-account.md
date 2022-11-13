# Edit Account

### Scope
* Account management system

### Level
* User goal

### Primary actor
* User

### Stakeholders and interest
* User: Wants an easily readable system to create an account and easily gain access to the application

### Preconditions
* User should have an account

### Postconditions
* User edited their account
* A new account is stored in the database
* User gained the ability to interact with the app either by:
    1. Creating a trip
    2. Accepting driver offers

### Main success scenario
1. System searches up user's account information
2. System brings up user's account information
3. System makes the phone number, password, confirm password, picture and username sections available to edit
4. System provides a delete section
5. User makes wanted edits
6. System checks that fields are correct and not empty
7. System submits changes to user's account

### Extensions
1a. No prior data exists:
1. System goes to <ins>Create an account</ins> for account creation

4a. User clicks delete:
1. System outputs "Are you sure you want to delete the account? All information will be deleted"
2. User clicks accept
3. System returns user to login/signup screen

5a. User inputs a username that does not meet the requirements:
5. System outputs the error message “Enter a valid username”
6. User enters a valid username

5b. User inputs a password that does not meet the requirements:
1. System outputs the error message “Enter a valid password”
2. User enters a valid password

5c. User inputs a string in “Confirm password” that does not match the password inputted:
1. System outputs the error message “Does not match password”
2. User correctly inputs their password

6a. User left sections blank:
1. System highlights blank sections and outputs required error messages
2. User is required to repeat specific steps
### Special requirements
* System displays creation page in a form-like format
* Date, time and phone number formatting
* Text is easily readable

### Technology and data variations list

### Frequency of occurrence
This use case is will be used whenever the user wants to edit or delete their account

### Miscellaneous