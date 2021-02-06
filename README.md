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

`QUIT` When you are done




for more information check out the docs:
https://dev.mysql.com/doc/mysql-getting-started/en/#mysql-getting-started-installing
