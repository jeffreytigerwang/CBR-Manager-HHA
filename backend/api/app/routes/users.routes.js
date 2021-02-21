module.exports = app => {
    const users = require("../controllers/users.controller.js");
  
    var router = require("express").Router();
  
    // Create a new item
    router.post("/", users.create);
  
    // Retrieve all items
    router.get("/", users.findAll);
  
    // Retrieve a single item with id
    router.get("/:id", users.findOne);
  
    // Update a item with id
    router.put("/:id", users.update);
  
    // Delete a item with id
    router.delete("/:id", users.delete);
  
    // Delete all items
    router.delete("/", users.deleteAll);
  
    app.use('/api/users', router);
  };
  