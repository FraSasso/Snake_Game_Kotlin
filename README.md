# Snake_Game

This is a Kotlin-based web application that implements a simple game login system and player score tracking. 
It uses Spark Framework for the server-side routing, interacting with a MySQL database for storing and retrieving player data and scores, with Handlebars templates rendering the front-end. 
The application supports features like user authentication, game score management, and displaying the top results.


# File Overview

- Main.kt --> Sets up the core application, including routes and database connectivity.
- PlayerInfo.kt --> Manages user information and authentication in relation to the database.
- Score.kt --> Handles game score data storage and retrieval.
- SecureTemplateViewRoute.kt --> Implements a secure routing interface for templates.


# Compilation

  - Set Up the Database by creating the 'user' and 'games' tables in MySQL
  - Configure Database Credentials --> DriverManager.getConnection("jdbc:mysql://localhost/gamedb", "root", "root")

# Run the Application
  - Use IntelliJ IDEA or a terminal to execute the Main.kt file
  - Access it by opening a browser and going to http://localhost:4567/
  
    

  
  
  
