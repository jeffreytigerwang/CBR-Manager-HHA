module.exports = app => {
    const tutorials = require("../controllers/tutorials.controller.js");

    var router = require("express").Router();

    // Create a new item
    router.post("/", tutorials.create);

    // Retrieve all items
    router.get("/", tutorials.findAll);

    // Retrieve a single item with id
    router.get("/:id", tutorials.findOne);

    // Update a item with id
    router.put("/:id", tutorials.update);

    // Delete a item with id
    router.delete("/:id", tutorials.delete);

    // Delete all items
    router.delete("/", tutorials.deleteAll);

    app.use('/api/tutorials', router);
  };

