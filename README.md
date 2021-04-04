#welltok-assignment
 
Author: Gaurav Karki
Tech Stack: Java, PostGreSQL

Steps to run code:
1) Make changes in db.properties and also run "campaign_rates_script.sql" in your local db.
2) Open Terminal or CMD 
3) Change directory to the project folder "Welltok Assignment"
3) Run the following query: java --enable-preview -cp WelltokAssignment.jar App "22000" "EM|SMS"

Notes:
1) PostGreSQL to store and fetch campaign rates.
2) Implemented Factory Design pattern to easily add new Channels in future.
3) Created unit test cases in the test() function.
