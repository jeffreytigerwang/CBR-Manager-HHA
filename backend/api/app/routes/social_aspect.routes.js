module.exports = app => {
    const social_aspect = require("../controllers/social_aspect.controller.js");

    var router = require("express").Router();

    // Create a new item
    router.post("/", social_aspect.create);

    // Retrieve all items
    router.get("/", social_aspect.findAll);

    // Retrieve a single item with id
    router.get("/:id", social_aspect.findOne);

    // Update a item with id
    router.put("/:id", social_aspect.update);

    // Delete a item with id
    router.delete("/:id", social_aspect.delete);

    // Delete all items
    router.delete("/", social_aspect.deleteAll);

    app.use('/api/socialAspect', router);
  };

