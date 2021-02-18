module.exports = app => {
    const education_aspect = require("../controllers/education_aspect.controller.js");

    var router = require("express").Router();

    // Create a new item
    router.post("/", education_aspect.create);

    // Retrieve all items
    router.get("/", education_aspect.findAll);

    // Retrieve a single item with id
    router.get("/:id", education_aspect.findOne);

    // Update a item with id
    router.put("/:id", education_aspect.update);

    // Delete a item with id
    router.delete("/:id", education_aspect.delete);

    // Delete all items
    router.delete("/", education_aspect.deleteAll);

    app.use('/api/educationAspect', router);
  };

