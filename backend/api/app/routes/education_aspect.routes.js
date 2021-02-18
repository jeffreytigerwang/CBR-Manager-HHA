module.exports = app => {
    const education_aspects = require("../controllers/education_aspect.controller.js");

    var router = require("express").Router();

    // Create a new item
    router.post("/", education_aspects.create);

    // Retrieve all items
    router.get("/", education_aspects.findAll);

    // Retrieve a single item with id
    router.get("/:id", education_aspects.findOne);

    // Update a item with id
    router.put("/:id", education_aspects.update);

    // Delete a item with id
    router.delete("/:id", education_aspects.delete);

    // Delete all items
    router.delete("/", education_aspects.deleteAll);

    app.use('/api/educationAspects', router);
  };

