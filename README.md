# DEMICON-Tech-Task

## Description

A connector to the Randomuser API service with the following
specifications:
 - Java standalone application (Connector) created with Spring boot
 - Connector periodically (JobTask) get data from https://randomuser.me/api and save
into internal database
   - Documentation of API - https://randomuser.me/documentation
 - GET controller to get users 
 - In case of an unsuccessful synchronization attempt, the Connector return
data from the last successful synchronization
 - Connector is configured with simple spring.properties
   - url - API end point
   - userSize - max of users count to get from API
   - period - period of synchronization with API in second 
 - Embedded DB H2 used for storage
 - UI application in ReactJS to view data from backend with the
following features:
   - ComboBox with all Countries
   - Button to reload data from backend
   - List of users (name, gender, email) from selected in ComboBox country

 ## How to Use

### Step 1: Download to local repository using Git
 - You must have git installed
 - git clone https://github.com/JorgenVasshaug/DEMICON-Tech-Task

### Step 2: Start BackEnd and FrontEnd

#### Backend
 - You can either 
   - import code into a project into your favourite IDE, and run the application from there 
   - execute using maven:
     - Go into root of project/git folder
     - Type
       - mvn spring-boot:run
     - Requires Maven?
   - execute using jvm?

#### FrontEnd
 - You need npm installed
 - Execute using npm
     - Go into root of project/git folder
     - Type
         - npm start

### Step 3: Try it out
 - Open a web browser and go to:
   - http://localhost:3000/
 
BackEnd is listening on http://localhost:8080/


<!--
- optionals: 
  What was your motivation? Why did you build this project?
  What problem does the project solve? Or, what it does?
  Why you used specific technologies? If your project has a lot of many features, list them here.
  Mention some of the challenges you faced and features you hope to implement in the future.
  Mention anything that you think you are proud of building or having in that project
  What did you learn in the process?
  What’s next for the project?
  Mention languages, frameworks, databases, etc.
  Provide deploy links or any other required links
-->