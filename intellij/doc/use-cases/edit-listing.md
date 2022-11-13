# Edit Account

### Scope
* Listing management system

### Level
* User goal

### Primary actor
* User

### Stakeholders and interest
* User: Wants an easily readable system to edit an account and easily gain access to the application

### Preconditions
* User should have an account
* User should have a listing

### Postconditions
* User edited their listing

### Main success scenario
1. System searches up user's listing information
2. System brings up user's listing information
3. System makes the seats,date,time, start location and end location sections available to edit
4. System provides a delete section
5. User makes wanted edits
6. System checks that fields are correct and not empty
7. System submits changes to user's listing

### Extensions
1a. No prior data exists:
1. System goes to <ins>Create a listing</ins> for listing creation

4a. User clicks delete:
1. System outputs "Are you sure you want to delete this listing? All information will be deleted"
2. User clicks accept
3. System returns user to dashboard fragment

5a. User inputs a seats that does not meet the requirements:
5. System outputs the error message “Enter a valid number of seats”
6. User enters a valid number of seats

5b. User inputs a date that does not meet the requirements:
1. System outputs the error message “Enter a valid date”
2. User enters a valid date

5c. User inputs a location that does not match the password inputted:
1. System outputs the error message “Enter a valid location”
2. User correctly inputs their location

6a. User left sections blank:
1. System outputs required error messages
2. User is required to repeat specific steps

### Special requirements
* System displays edit listing page in a form-like format
* Date and Time formatting
* Text is easily readable

### Technology and data variations list

### Frequency of occurrence
This use case is will be used whenever the user wants to edit or delete their listing

### Miscellaneous