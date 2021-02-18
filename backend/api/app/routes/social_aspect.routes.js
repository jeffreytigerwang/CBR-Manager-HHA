module.exports = app => {
    const social_aspects = require("../controllers/social_aspect.controller.js");

    var router = require("express").Router();

    // Create a new item
    router.post("/", social_aspects.create);

    // Retrieve all items
    router.get("/", social_aspects.findAll);

    // Retrieve a single item with id
    router.get("/:id", social_aspects.findOne);

    // Update a item with id
    router.put("/:id", social_aspects.update);

    // Delete a item with id
    router.delete("/:id", social_aspects.delete);

    // Delete all items
    router.delete("/", social_aspects.deleteAll);

    app.use('/api/socialAspects', router);
  };

