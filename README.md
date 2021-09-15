# [Assignment Development plan][1]

* Initial maven project generated from IntelliJ IDEA
* Read the file from a parameter in a performatic way
* Parse JSON into java object
* Create logic to match started and finished by ID and add flag
* Add hsqlDB to persist information
* Add Junit and Create a manual to run the application


What I would do if I had more time:
* Add more JUNIT 
* I would persist the list of logs once it gets higher than 1000 to not keep much information in memory. 

How to run:
* Clone from git
* Install Maven, and Java 11.
* Download the maven dependencies for the project by running "mvn clean compile assembly:single"
* Inside the target folder you will have a jar file that will allow you do use the fallowing command line:
   java -jar CreditSuisseAssignment-1.0.jar THE_PATH_LOGS