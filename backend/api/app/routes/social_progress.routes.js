module.exports = app => {
    const social_progress = require("../controllers/social_progress.controller.js");
  
    var router = require("express").Router();
  
    // Create a new item
    router.post("/", social_progress.create);
  
    // Retrieve all items
    router.get("/", social_progress.findAll);
  
    // Retrieve a single item with id
    router.get("/:id", social_progress.findOne);
  
    // Update a item with id
    router.put("/:id", social_progress.update);
  
    // Delete a item with id
    router.delete("/:id", social_progress.delete);
  
    // Delete all items
    router.delete("/", social_progress.deleteAll);
  
    app.use('/api/social_progress', router);
  };
  