# Create a Listing

### Scope
* Listings Management system

### Level
* User goal

### Primary Actor
* User

### Stakeholders and Interest
* User: Wants an easily readable system with easy, accurate way to search and filter listings. Wants to easily find certain listings

### Preconditions
* The user should have an account created
* The user should be logged in
* There should be at least one listing in the list

### Postconditions
* A new page with the resulting listings created
* The page of Listings is updated

### Main success scenario
1. System accesses filter fragment
2. System asks if the listings should be from Drivers or Passengers
3. User chooses their role
4. Systems asks to input number of seats
5. User enters a number as a filter 
6. System asks to input a date
7. User enters a date as a filter
8. System asks to input a time
9. User enters a time as a filter
10. System extracts time as a filter
11. System asks to input start place
12. User enters desired start location as a filter
13. System asks to input end place
14. User enters desired end location as a filter
15. User clicks "Apply"
16. System extracts number and correctly validates
17. System extracts the date and correctly validates
18. System extracts location and correctly validates
19. System extracts location and correctly validates
20. System passes the filter method
21. System creates filtered page and displays it to the user

### Extensions
3a. Role was not chosen:
1. System accepts no selection
2. System will display both roles

5a. Number of seats was not inputted:
1. System accepts no input
2. System will not have restrictions on seats

7a. A date was not inputted:
1. System accepts no input
2. System will not have restrictions on the date

9a. Time was not inputted:
1. System accepts no input
2. System will not have restrictions on time

11a. Start location was not inputted:
1. System accepts no input
2. System will not have restrictions on the start location

13a. End location was not inputted:
1. System accepts no input
2. System will not have restrictions on the end location


### Special requirements
* System displays Creation page in a form-like format
* Date and time formatting
* Text is easily readable

### Technology and data variations list

### Frequency of occurrence
This use case will occur everytime a user wants to filter a list of listings

### Miscellaneous 