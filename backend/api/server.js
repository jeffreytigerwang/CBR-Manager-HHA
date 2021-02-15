// Code modified from https://bezkoder.com/node-js-express-sequelize-mysql/

const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");

const app = express();

var corsOptions = {
  origin: "http://localhost:8080"
};

app.use(cors(corsOptions));

// parse requests of content-type - application/json
app.use(bodyParser.json());

// parse requests of content-type - application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));

// sync with database
const db = require("./app/models");

// turn below on in production
//db.sequelize.sync();
// use below for dev
db.sequelize.sync({ force: true }).then(() => {
  console.log("dropped table and re-sync db");
});

// simple route
app.get("/", (req, res) => {
  res.json({ message: "Welcome to the CBR Application." });
});

require("./app/routes/test_data.routes")(app);
require("./app/routes/clients.routes")(app);

// set port, listen for requests
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});

