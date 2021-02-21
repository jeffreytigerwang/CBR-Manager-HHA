module.exports = app => {
  const clients = require("../controllers/clients.controller.js");

  var router = require("express").Router();

  // Create a new item
  router.post("/", clients.create);

  // Retrieve all items
  router.get("/", clients.findAll);

  // Retrieve a single item with id
  router.get("/:id", clients.findOne);

  // Update a item with id
  router.put("/:id", clients.update);

  // Delete a item with id
  router.delete("/:id", clients.delete);

  // Delete all items
  router.delete("/", clients.deleteAll);

  app.use('/api/clients', router);
};
