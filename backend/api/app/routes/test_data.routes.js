module.exports = app => {
  const test_data = require("../controllers/test_data.controller.js");

  var router = require("express").Router();

  // Create a new item
  router.post("/", test_data.create);

  // Retrieve all items
  router.get("/", test_data.findAll);

  // Retrieve all active items
  router.get("/active", test_data.findAllActive);

  // Retrieve a single item with id
  router.get("/:id", test_data.findOne);

  // Update a item with id
  router.put("/:id", test_data.update);

  // Delete a item with id
  router.delete("/:id", test_data.delete);

  // Delete all items
  router.delete("/", test_data.deleteAll);

  app.use('/api/test_data', router);
};
