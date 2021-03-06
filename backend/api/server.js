// Code modified from https://bezkoder.com/node-js-express-sequelize-mysql/

const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");

const app = express();

var corsOptions = {
  origin: "http://localhost:8081"
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
require("./app/routes/visits.routes")(app);

require("./app/routes/users.routes")(app);
require("./app/routes/education_aspect.routes")(app);
require("./app/routes/education_progress.routes")(app);
require("./app/routes/health_aspect.routes")(app);
require("./app/routes/health_progress.routes")(app);
require("./app/routes/social_aspect.routes")(app);
require("./app/routes/social_progress.routes")(app);
require("./app/routes/disability.routes")(app);
require("./app/routes/tutorials.routes")(app);
require("./app/routes/referral.routes")(app);
require("./app/routes/messages.routes")(app);



// set port, listen for requests
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});

