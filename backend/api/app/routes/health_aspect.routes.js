module.exports = app => {
    const health_aspects = require("../controllers/health_aspect.controller.js");

    var router = require("express").Router();

    // Create a new item
    router.post("/", health_aspects.create);

    // Retrieve all items
    router.get("/", health_aspects.findAll);

    // Retrieve a single item with id
    router.get("/:id", health_aspects.findOne);

    // Update a item with id
    router.put("/:id", health_aspects.update);

    // Delete a item with id
    router.delete("/:id", health_aspects.delete);

    // Delete all items
    router.delete("/", health_aspects.deleteAll);

    app.use('/api/healthAspects', router);
  };

