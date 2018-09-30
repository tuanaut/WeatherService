Weather Service Coding Challenge

Goal: 
Provide a HTTP get request with a zipcode in the path parameter to return a wind resource
Wind resource retrieved from openweathermap.org API

Requirements:
•	Resource is in JSON format
•	Cache the resource for 15 minutes 
•	Provide a CLI to clear the cache
•	Ensure cache is thread safe
•	Wind data should include speed and direction

How to Run:
1.	Clone repo
2.	mvn clean install
3.	java -jar target/weather-0.0.1-SNAPSHOT.jar
4.	On another terminal window:
curl localhost:8080/api/v1/wind/{zipcode}

Example run:

curl localhost:8080/api/v1/wind/89148
{"deg":310,"speed":2.6}

CLI to clear cache: curl localhost:8080/evict

      
 

