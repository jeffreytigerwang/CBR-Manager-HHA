## Instructions

Clone Repo

```
cd tut-nodejs-backend-api

npm install

```


## Set up MySQL Locally (for testing)

`mysql -u root -p` to log in

Create a new database for our app

`SHOW DATABASES;` to see what is already installed
`CREATE DATABASE cbr_data`

Create a new user for our app.

`CREATE USER '<name>'@'localhost' IDENTIFIED BY '<password>';`

We will use this user to access the database from our app. 
Now we will add privileges to the account, so that the user only has access to
our cbr_data database.

`GRANT ALL PRIVILEGES ON cbr_data . * TO '<name>'@'localhost';`

Note: cbr_data is the database we are giving privileges to, and * is the tables

`FLUSH PRIVILEGES;` and logout `quit;`

Try logging in with the new account

`mysql -u <name> -p` and type in password when prompted

`USE cbr_data` to make changes to database

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

## Give credencials to app

Open  app/config/db.config.js to see the following:

```
module.exports = {
  HOST: "localhost",
  USER: "<name>",
  PASSWORD: "<password>",
  DB: "cbr_data",
  dialect: "mysql",
  pool: {
    max: 5,
    min: 0,
    acquire: 30000,
    idle: 10000
  }
};
```

update name and password fields with credencials creates in mysql
Note: we can share what they are in discord


## Defining Models

create a model with naming convention *modelName.model.js* and add to *models* 
folder.

Use the following template to create new data:

```
module.exports = (sequelize, Sequelize) => {
  const ModelName = sequelize.define("modelName", {
    property_one: {
      type: Sequelize.STRING
    },
    property_two: {
      type: Sequelize.STRING
    },
    property_three: {
      type: Sequelize.BOOLEAN
    }
  });

  return ModelName;
};
```

Basically, replace modelName & ModelName with the name of a new class, and populate
with properties

## Initialize server

`node server.js`

Go to http://localhost:8080/ and server will be running
