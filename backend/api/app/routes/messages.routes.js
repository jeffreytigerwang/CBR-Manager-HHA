module.exports = app => {
    const messages = require("../controllers/messages.controller.js");

    var router = require("express").Router();

    // Create a new item
    router.post("/", messages.create);

    // Retrieve all items
    router.get("/", messages.findAll);

    // Retrieve a single item with id
    router.get("/:id", messages.findOne);

    // Update a item with id
    router.put("/:id", messages.update);

    // Delete a item with id
    router.delete("/:id", messages.delete);

    // Delete all items
    router.delete("/", messages.deleteAll);

    app.use('/api/messages', router);
  };

