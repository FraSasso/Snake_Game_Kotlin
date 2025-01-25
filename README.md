# Kotlin_Snake_Game

This is a Kotlin-based web application that implements a simple game login system and player score tracking. 
It uses Spark Framework for the server-side routing, interacting with a MySQL database for storing and retrieving player data and scores, with Handlebars templates rendering the front-end. 
The application supports features like user authentication, game score management, and displaying the top results.


# File Overview

  Main.kt --> Sets up the core application, including routes and database connectivity.
    KEY FEATURES:
    Routes:
      * /         (GET) --> Displays login page.
      * /         (POST) --> Authenticates user & displays user scores.
      * /saveGame (POST) --> Saves game performance data.
      * /logout   (GET) --> Logs out user & invalidates the session.
    Handles user session validation and renders Login.hbs and Game.hbs pages.

  PlayerInfo.kt --> Manages user information and authentication in relation to the database.
    KEY FEATURES:
    User Attributes:
      * fullName --> The player's full name.
      * id --> Unique identifier for the player.
      * userPw --> Player's password.
      * username (PRIMARY KEY) --> Login name of the player.
    
  Score.kt --> Handles game score data storage and retrieval.
    KEY FEATURES: 
      * saveScore --> Inserts player scores into the database.
      * orderedTopScores --> Retrieves the top 5 scores for the user.

  SecureTemplateViewRoute.kt --> Implements a secure routing interface for templates.
    KEY FEATURES:
      * Implements error handling, rendering an Error.hbs template in case of exceptions.


# Compilation

  * Set Up the Database by creating the required tables in MySQL
    ↘︎
      CREATE TABLE user (
      id INT AUTO_INCREMENT PRIMARY KEY,
      fullname VARCHAR(255) NOT NULL,
      login VARCHAR(50) NOT NULL UNIQUE,
      password VARCHAR(255) NOT NULL
      );
    ↘︎
      CREATE TABLE games (
          id INT AUTO_INCREMENT PRIMARY KEY,
          apples INT NOT NULL,
          time DOUBLE NOT NULL,
          ownerid INT NOT NULL,
          FOREIGN KEY (ownerid) REFERENCES user(id)
      );

    * Configure Database Credentials --> DriverManager.getConnection("jdbc:mysql://localhost/gamedb", "root", "root")

# Run the Application
  Use IntelliJ IDEA or a terminal to execute the Main.kt file
  Access it by opening a browser and going to http://localhost:4567/
  
    

  
  
  
