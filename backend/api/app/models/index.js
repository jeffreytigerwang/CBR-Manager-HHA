const dbConfig = require("../config/db.config.js");

// Sequelize is the library
// sequalize is the instance
const Sequelize = require("sequelize");
const sequelize = new Sequelize(dbConfig.DB, dbConfig.USER, dbConfig.PASSWORD, {
  host: dbConfig.HOST,
  dialect: dbConfig.dialect,
  operatorsAliases: false,

  pool: {
    max: dbConfig.pool.max,
    min: dbConfig.pool.min,
    acquire: dbConfig.pool.acquire,
    idle: dbConfig.pool.idle
  }
});

// test database with this
/*
async function test_db() {
  try {
    await sequelize.authenticate();
    console.log('Connection established with database');
  } catch (error) {
      console.error('Unable to connect with database', error);
  }
}

test_db();
*/

const db = {};

db.Sequelize = Sequelize;
db.sequelize = sequelize;

db.test_data = require("./test_data.model.js")(sequelize, Sequelize);
db.clients = require("./clients.model.js")(sequelize, Sequelize);

module.exports = db;

