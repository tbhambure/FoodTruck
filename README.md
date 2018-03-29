# FoodTrucks
Simple command-line program that will print out a list of open food trucks.

## Steps to run the program
1. Clone the repository
2. Setup maven (if using for the first time)
   1. https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
3. Execute the below command in terminal from the parent folder
    1. sh show-open-food-trucks.sh

If we were to implement this as a web application, we would do any/all of the below steps to make it robust, scalable and highly available.
1. Create separate layers e.g. using MVC pattern
1. Add ability to horizontally scale application servers and database to handle increase in traffic
1. Add caching layer so our service is resilient even if http://data.sfgov.org service is down
1. Handle different timezones - the source of food-trucks could in the future be read for multiple locations
