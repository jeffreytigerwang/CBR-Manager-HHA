module.exports = app => {
    const health_progress = require("../controllers/health_progress.controller.js");

    var router = require("express").Router();

    // Create a new item
    router.post("/", health_progress.create);

    // Retrieve all items
    router.get("/", health_progress.findAll);

    // Retrieve a single item with id
    router.get("/:id", health_progress.findOne);

    // Update a item with id
    router.put("/:id", health_progress.update);

    // Delete a item with id
    router.delete("/:id", health_progress.delete);

    // Delete all items
    router.delete("/", health_progress.deleteAll);

    app.use('/api/healthProgress', router);
  };

