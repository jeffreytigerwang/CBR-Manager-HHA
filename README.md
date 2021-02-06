## Instructions

Clone Repo

```
cd tut-nodejs-backend-api

npm install

node server.js
```

Go to http://localhost:8080/ and server will be running


## Set up MySQL Locally (for testing)

`mysql -u root -p` to log in

Create a new user for our app.

`ALTER USER '<name>'@'localhost' IDENTIFIED BY '<password>';`

add name and user name to be used with our server 

`SHOW DATABASES;` to see what is already installed

`CREATE DATABASE <name>` to create new database

`USE <name>` to make changes to database

The following example shows how to create a basic table:

```
CREATE TABLE cats
(
  id              INT unsigned NOT NULL AUTO_INCREMENT, # Unique ID for the record
  name            VARCHAR(150) NOT NULL,                # Name of the cat
  owner           VARCHAR(150) NOT NULL,                # Owner of the cat
  birth           DATE NOT NULL,                        # Birthday of the cat
  PRIMARY KEY     (id)                                  # Make the id the primary key
);
```

Other useful commands:

`SHOW TABLES;`

`DESCRIBE <table-name>;`

`QUIT;` 

for more information check out the docs:
https://dev.mysql.com/doc/mysql-getting-started/en/#mysql-getting-started-installing
