# SpeerNotes

SpeerNotes is a backend service I created for Speer assessment test.

## Description

SpeerNotes is build using Java with SpringBoot framework. It exposes endpoint to signup and login to our note app. I used SpringSecurity to manage authentication using JWT.
MongoDb is used to store user as well as notes.

### Technical Requirements
* Implement a RESTful API using a framework of your choice (e.g. Express, DRF, Spring).
* Use a database of your choice to store the data (preferably MongoDB or PostgreSQL).
* Use any authentication protocol and implement a simple rate limiting and request throttling to handle high traffic.
* Implement search functionality to enable users to search for notes based on keywords. ( preferably text indexing for high performance )
* Write unit tests and integration tests your API endpoints using a testing framework of your choice.
####API Endpoints
Authentication Endpoints
* POST /api/auth/signup: create a new user account.
* POST /api/auth/login: log in to an existing user account and receive an access token.
Note Endpoints
* GET /api/notes: get a list of all notes for the authenticated user.
* GET /api/notes/ get a note by ID for the authenticated user.
* POST /api/notes: create a new note for the authenticated user.
* PUT /api/notes/ update an existing note by ID for the authenticated user.
* DELETE /api/notes/ delete a note by ID for the authenticated user.
* POST /api/notes/:id/share: share a note with another user for the authenticated user.
* GET /api/search?q=:query: search for notes based on keywords for the authenticated user.

## Getting Started

### Frameworks & Tools :
* SpringBoot -> SpringBoot provides MVC with default configuration which enables to create rest service easily.
* MongoAtlas -> Provides a cloud NoSql database which can be accessed easily anywhere.
* Spring Security -> To create and manage Authentication manager and provider. To intercept calls to go though authentication. To apply filter for authentication.
* JJWT -> To create and parse JWT token.
* Resilience4j -> To limit requests to prevent server from crashing. 
* Junit, Mockito, Diffblue(IntelliJ Plugin) -> to generate automated test cases.

### Dependencies

* JDK 17+
* Maven

### Installing

* Build the maven package as a jar file. All the dependencies will be directly download from Maven repo.

### Executing program

* Deploy the jar package and call the rest endpoints.
```
code blocks for commands
```
