const db = require("../models");
const HealthAspects = db.health_aspects;
const Op = db.Sequelize.Op;

// Create and Save a new data
exports.create = (req, res) => {
  // Validate Request
  if (!req.body.risk_level) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create Item
  const healthAspect = {
    risk_level: req.body.risk_level,
    requirements: req.body.requirements,
    goal: req.body.goal,
    progress_ids: req.body.progress_ids

  };

  // Save item in database
  HealthAspects.create(healthAspect)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Error occured creating a healthAspect."
      });
    });

};

// Retrieve all data from the database.
exports.findAll = (req, res) => {

  HealthAspects.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving HealthAspects."
      });
    });
};

// Find a single healthAspect with an id
exports.findOne = (req, res) => {
  const id = req.params.id;

  HealthAspects.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving healthAspect with id=" + id
      });
    });
};

// Update a data item by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  HealthAspects.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "healthAspect was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update healthAspect with id=${id}. Maybe item was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating healthAspect with id=" + id
      });
    });
};

// Delete a data item with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  HealthAspects.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "healthAspect was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete healthAspect with id=${id}. Maybe item was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not healthAspect with id=" + id
      });
    });
};

// Delete all data from the database.
exports.deleteAll = (req, res) => {
  HealthAspects.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} HealthAspects were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all items."
      });
    });
};
