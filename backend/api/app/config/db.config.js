module.exports = {
  HOST: "localhost",
  USER: "cbr-admin",
  PASSWORD: "marsbars",
  DB: "cbr_data",
  dialect: "mysql",
  pool: {
    max: 5,
    min: 0,
    acquire: 30000,
    idle: 10000
  }
};

