# hbService, stalled in development 

By: Richard Mau, June 2017

The purpose of this web service is to bridge the gap between mulitple data sources in the identification of patients throughout research studies.
This was a learning experience for myself in RESTful Java Web Services, so the structure and function are basic. 
It includes the basic CRUD functionality, which was tested exclusively on the Postman web client. 

The current state of the project does not include the UPMC plugins to utilize the EMPI tools through the ESB. The ESB tools can be found in the main src directory path as ESB.zip. Included is a EMPITest.java which runs a sample test of the ESB tools using mock data.   

## Getting Started
The project was built in Eclipse so the instructions below are based on the Eclipse IDE and the tests were done in Postman.

### Prerequisties
* Apache Tomcat 8.5
* Postman

## Steps
1) Once you have the repository downloaded, import the project into Eclipse
2) Set up the server module in Eclipse
3) Make sure Tomcat is running
4) Run as > server
5) Test on Postman

### Author remarks
The emphasis is on new to web services so the implementation is admittedly naive.

## Contact
For follow-on explanation, you can reach me at:
richmau90@gmail.com
