module.exports = app => {
    const health_aspect = require("../controllers/health_aspect.controller.js");

    var router = require("express").Router();

    // Create a new item
    router.post("/", health_aspect.create);

    // Retrieve all items
    router.get("/", health_aspect.findAll);

    // Retrieve a single item with id
    router.get("/:id", health_aspect.findOne);

    // Update a item with id
    router.put("/:id", health_aspect.update);

    // Delete a item with id
    router.delete("/:id", health_aspect.delete);

    // Delete all items
    router.delete("/", health_aspect.deleteAll);

    app.use('/api/healthAspect', router);
  };

