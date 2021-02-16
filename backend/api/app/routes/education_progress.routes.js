module.exports = app => {
    const education_progress = require("../controllers/education_progress.controller.js");
  
    var router = require("express").Router();
  
    // Create a new item
    router.post("/", education_progress.create);
  
    // Retrieve all items
    router.get("/", education_progress.findAll);
  
    // Retrieve a single item with id
    router.get("/:id", education_progress.findOne);
  
    // Update a item with id
    router.put("/:id", education_progress.update);
  
    // Delete a item with id
    router.delete("/:id", education_progress.delete);
  
    // Delete all items
    router.delete("/", education_progress.deleteAll);
  
    app.use('/api/education_progress', router);
  };
  