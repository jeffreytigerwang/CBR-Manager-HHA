module.exports = app => {
    const referrals = require("../controllers/referral.controller.js");

    var router = require("express").Router();

    // Create a new item
    router.post("/", referrals.create);

    // Retrieve all items
    router.get("/", referrals.findAll);

    // Retrieve a single item with id
    router.get("/:id", referrals.findOne);

    // Update a item with id
    router.put("/:id", referrals.update);

    // Delete a item with id
    router.delete("/:id", referrals.delete);

    // Delete all items
    router.delete("/", referrals.deleteAll);

    app.use('/api/referrals', router);
  };

