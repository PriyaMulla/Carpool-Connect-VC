
# Domain Model
```plantuml
@startuml
hide circle
hide empty methods 

'classes

class PageOfListings{
}
class Listing{
Date created
Time of trip
Date of trip
Start Location
End Location
Comment
}

class Account{
Name
Phone number
Email
Username
Password
Picture
Dashboard
}

class Message {
Send message
Receive message
Read message
message
}

class MessageCenter{
}

class Review{
Rating
Comment
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
controller -> system : show account created


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

controller -> system : show listing created
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
database "Listing database" as ldatabase

'ACTIONS
user -> system : input date,start-location,OR end-location 
system -> controller : search page of listings
controller -> ldatabase : search page of listings


activate ldatabase

ldatabase -> ldatabase : validate inputs
activate ldatabase #CC5500

deactivate ldatabase
ldatabase -> controller
deactivate ldatabase

controller -> system : show page of listings

user -> system : filter-by date, start-location, and/or end-location
system -> controller : update page of listings
controller -> ldatabase :  update page of listings

activate ldatabase

ldatabase -> ldatabase : validate inputs
activate ldatabase #CC5500

deactivate ldatabase
ldatabase -> controller
deactivate ldatabase

controller -> system : show refined page of listings
@enduml


/'
account -->> profile **: pr = create(name,email_address)

participant "list : Listing" as listing
database "Account database" as adatabase
database "Listing database" as ldatabase
SINCE ITS MED RISK TRY TO PUT MOST ATTR AS WE CAN BUT FOR HIGH RISK, ALL ATTR ARE NECESSARY

For first iteration, think of it as building a project like pet trainer so instead of looking if any of the fields are empty after clicking sign up, it does it at every point cuz its more impossible to do at this stage of the process

from user -> CPA, user inputs raw data so Input account information is ok

a method called getProfile() could get the necessary parameters

If pr/or acc is successfully created, pr pings to CPA that account created

//CPA sends email ping to email server  // fist iteration just make sure the substring vassar.edu is there


Make :CPA -> Cpa view

make a controller

things like account creation, listing creation etc... are different seq models

in class diagram have controller class
method getProfile() etc
review() method
upcoming trip class?
-- Review cannot be an attribute cuz it has comment and rating(int)

'/