# Instructions

To deploy the api:

First, clone the repo.

```
cd repo
npm install

npm run pm2
```


# Initial Setup

Clone Repo

```
cd tut-nodejs-backend-api

npm install

```


## Set up MySQL database on VM

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



## Creating new models

Basic idea of creating new models is:

1. copy *test_data.model.js* file in the *models* folder, and add properties

2. add `db.<model_name> = require("./<model_name>.js")(sequelize, Sequelize);`
to end of models/index.js file

3. copy *test_data.controller.js* file in the *controllers* folder, and update test_data varibles with your new class name.

4. copy *test_data.routes.js* file in the *routes* folder, and replace test_data with new class name.

5. add `require("./app/routes/<model_name>.routes")(app);` line to end of server.js file



## Testing

Use Postman

`snap install postman`


Follow the tutorial: https://bezkoder.com/node-js-express-sequelize-mysql/
Basically, start up your server and got to http://localhost:8080/api/test_data

- using GET at */api/test_data* will show data in database
- using POST at */api/test_data* will add data (in postman go to body > raw > json 
- using GET at */api/test_data/id* will show that one item
- using PUT at */api/test_data/id* will update that item, when using postman
- using DELETE at */api/test_data/id* will delete that item, when using postman
- using DELETE at */api/test_data* will delete all items, using postman
- using GET at */api/test_data/active* will show all active items (active is just a property)


## Docker Containers

- install Docker

`docker-compose up -d` to start mysql container
`docker ps` to list active docker containers
`docker stop <container id>` or `docker rm -f <container_id>` 

## Routes Available

`142.58.21.129/api/clients`
`142.58.21.129/api/disability`
`142.58.21.129/api/educationAspect`
`142.58.21.129/api/educationProgress`
`142.58.21.129/api/healthAspect`
`142.58.21.129/api/healthProgress`
`142.58.21.129/api/socialAspect`
`142.58.21.129/api/socialProgress`
`142.58.21.129/api/users`
`142.58.21.129/api/visits`
`142.58.21.129/api/clients`
`142.58.21.129/api/test_data`

client data can be queried with: `142.58.21.129/api/clients
?firstName=XXXX
&lastName=XXXX
&contactNumber=XXXX`

Visits, HealthProgress, SocialProgress & EducationProgress data can be queried with: 
`142.58.21.129/api/<data-type>?clientId=XXXX`

