const db = require("../models");
const SocialAspects = db.social_aspects;
const Op = db.Sequelize.Op;

// Create and Save a new data
exports.create = (req, res) => {
  // Validate Request
  if (!req.body.riskLevel) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create Item
  const socialAspect = {
    riskLevel: req.body.riskLevel,
    requirements: req.body.requirements,
    goal: req.body.goal,
    clientId: req.body.clientId
  };

  // Save item in database
  SocialAspects.create(socialAspect)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Error occured creating a socialAspect."
      });
    });

};

// Retrieve all data from the database.
exports.findAll = (req, res) => {

  SocialAspects.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving SocialAspects."
      });
    });
};

// Find a single socialAspect with an id
exports.findOne = (req, res) => {
  const id = req.params.id;

  SocialAspects.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving socialAspect with id=" + id
      });
    });
};

// Update a data item by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  SocialAspects.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "socialAspect was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update socialAspect with id=${id}. Maybe item was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating socialAspect with id=" + id
      });
    });
};

// Delete a data item with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  SocialAspects.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "socialAspect was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete socialAspect with id=${id}. Maybe item was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not socialAspect with id=" + id
      });
    });
};

// Delete all data from the database.
exports.deleteAll = (req, res) => {
  SocialAspects.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} SocialAspects were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all items."
      });
    });
};
