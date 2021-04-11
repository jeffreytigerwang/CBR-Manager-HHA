module.exports = app => {
  const visits = require("../controllers/visits.controller.js");

  var router = require("express").Router();

  // Create a new item
  router.post("/", visits.create);

  // Retrieve all items
  router.get("/", visits.findAll);

  // Retrieve a single item with id
  router.get("/:id", visits.findOne);

  // Update a item with id
  router.put("/:id", visits.update);

  // Delete a item with id
  router.delete("/:id", visits.delete);

  // Delete all items
  router.delete("/", visits.deleteAll);

  app.use('/api/visits', router);
};
