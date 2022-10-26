
# Domain Model
```plantuml
@startuml
hide circle
hide empty methods 

'classes

class PageOfListings{
}
class Listing{
dateCreated
timeOfTrip
dateOfTrip
startLocation
endLocation
comment
}

class Account{
name
phoneNumber
email
username
password
picture
dashboard
}

class Message {
dateSent
timeSent
}

class MessageCenter{
}

class Review{
rating
comment
}

class CollectionOfAccounts{
}
' associations
PageOfListings "1  " -left- "\t\t\t\t*" Listing : Contained-in\t\t
MessageCenter "1  " -left- "*" Message: \tContained in\t\t
Account "1" -up- "\t\t\t1" MessageCenter : \tHad-by\t\t
Account "1" -up- "1        " PageOfListings : \tSearches-through\t\t
Account "1" -right- "*" Listing : Manages\t\t
Account "1" -up-- "*" Listing : Participates-in\t\t
Account "1    " -up- "*" Message : Managed-by\t\t
Account "1 " -- "*" Review : \tContains\t\t
CollectionOfAccounts "1" -right- "1..*" Account : \tContains\t\t
@enduml
```

# Sequence Diagrams

## Account creation
```plantuml
@startuml
hide footbox

'ACTORS
actor User as user
participant "CPA view" as system
control "MainController" as controller
participant "acc : Account" as account



user -> system : input username,password,email address and name 
system -> controller : create account
controller -> account : create account

activate account

account -> account : validate inputs
activate account #FFBBBB

deactivate account

account -> controller : acc1 = create(username,password,emailAdress,name)
deactivate account
controller -> system : send account created
system -> user : show account created


@enduml
```

## Listing creation
```plantuml
@startuml
hide footbox

'ACTORS
actor "User" as user
participant "CPA view" as system
control "MainController" as controller
participant "list : Listing" as listing

'ACTIONS
user -> system : input date,time,start-location,end-location and comment
system -> controller : create listing
controller -> listing : create listing

activate listing

listing -> listing : validate inputs
activate listing #CC5500

deactivate listing
listing -> controller : listy = create(date,time,startLoc,endLoc,comment)
deactivate listing

controller -> system : send listing created
system -> user : show listing created
@enduml
```

## Search listings
```plantuml
@startuml
hide footbox

'ACTORS
actor "User" as user
participant "CPA view" as system
control "MainController" as controller
database "PageOfListings" as ldatabase

'ACTIONS
user -> system : input date,start-location,AND/OR end-location 
system -> controller : search page of listings
controller -> ldatabase : search page of listings


activate ldatabase

ldatabase -> ldatabase : validate inputs
activate ldatabase #CC5500

deactivate ldatabase
ldatabase -> controller : updates an array with results of Listings
deactivate ldatabase

controller -> system : updates array containing the listings
system -> user : show result of listings

@enduml
```

# Class Diagram

```plantuml
@startuml

class Account{
-name : string
-username : string
-email : string
-password : string
--
public void Account(string username,string password,string email,string name)
}

abstract AListing{
+int dateCreated
+int dateOfTrip
+int timeOfTrip
+string startLocation
+string endLocation
-int Id
--
public void Listing(int date,int time,string startLocation,string endLocation,int id)
}
class DriverListing{
int seatsAvailable
--
public String toString()
}
class PassengerListing{
int seatsRequired
--
public String toString()
}
class Controller{
List<Account>PageOfListings --> "\n(1...*)\nListing\n {List}\n\n\n" AListing : \t\t\t CollectionOfAccounts 
--
public boolean isValidString()
public void createAccount()
public void createListing()
public <List> SearchListing()
public <List> FilterListing()
public static void main(String[] args)
}
class PageOfListings{

}
class View{
 
public static void main(String[] args)
}
together {
class Account
class PageOfListings
}
'Association
View --[hidden] Controller
Controller -[hidden] PageOfListings
Controller --[hidden] Account
PageOfListings --> "\n(1...*)\nListing\n {List}\n" AListing : \t\t\t
Account --> "\n(1...*)\nListing\n {List}\n" AListing : \t
AListing <|... DriverListing
AListing <|... PassengerListing
Account -[hidden] PageOfListings
@enduml
```

[//]: # (account -->> profile **: pr = createname,email_address)

[//]: # (participant "list : Listing" as listing)

[//]: # (database "Account database" as adatabase)

[//]: # (database "Listing database" as ldatabase)

[//]: # (SINCE ITS MED RISK TRY TO PUT MOST ATTR AS WE CAN BUT FOR HIGH RISK, ALL ATTR ARE NECESSARY)

[//]: # (For first iteration, think of it as building a project like pet trainer so instead of looking if any of the fields are empty after clicking sign up, it does it at every point cuz its more impossible to do at this stage of the process)

[//]: # (from user -> CPA, user inputs raw data so Input account information is ok)

[//]: # (a method called getProfile&#40;&#41; could get the necessary parameters)

[//]: # (If pr/or acc is successfully created, pr pings to CPA that account created)

[//]: # (//CPA sends email ping to email server  // fist iteration just make sure the substring vassar.edu is there)

[//]: # (Make :CPA -> Cpa view)

[//]: # (make a controller)

[//]: # (things like account creation, listing creation etc... are different seq models)

[//]: # (in class diagram have controller class)

[//]: # (method getProfile&#40;&#41; etc)

[//]: # (review&#40;&#41; method)

[//]: # (upcoming trip class?)

[//]: # (-- Review cannot be an attribute cuz it has comment and rating&#40;int&#41;)