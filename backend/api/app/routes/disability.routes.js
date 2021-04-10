module.exports = app => {
    const disability = require("../controllers/disability.controller.js");

    var router = require("express").Router();

    // Create a new item
    router.post("/", disability.create);

    // Retrieve all items
    router.get("/", disability.findAll);

    // Retrieve a single item with id
    router.get("/:id", disability.findOne);

    // Update a item with id
    router.put("/:id", disability.update);

    // Delete a item with id
    router.delete("/:id", disability.delete);

    // Delete all items
    router.delete("/", disability.deleteAll);

    app.use('/api/disability', router);
  };

