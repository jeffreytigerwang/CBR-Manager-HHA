const db = require("../models");
const Visits = db.visits; //TODO: add db.visits to models/index.js
const Op = db.Sequelize.Op;

// Create and Save a visit
exports.create = (req, res) => {
  // Validate Request
  if (!req.body.date) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create a visit
  const visit = {
    purpose: req.body.purpose,
    lifeAspect: req.body.lifeAspect,
    date: req.body.date,
    workerName: req.body.workerName,
    locationGps: req.body.locationGps,
    locationZone: req.body.locationZone,
    locationNumber: req.body.locationNumber,
    clientId: req.body.clientId
  };

  // Save item in database
  Visits.create(visit)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Error occured creating a visit."
      });
    });

};

// Retrieve visits from the database.
exports.findAll = (req, res) => {

  Visits.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving visits."
      });
    });
};

// Find a single client with an id
exports.findOne = (req, res) => {
  const id = req.params.id;

  Visits.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving visit with id=" + id
      });
    });
};

// Update a visit by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  Visits.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "visit was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update visit with id=${id}. Maybe item was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating visit with id=" + id
      });
    });
};

// Delete a visit with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  Visits.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "visit was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete visit with id=${id}. Maybe item was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not visit with id=" + id
      });
    });
};

// Delete all visits from the database.
exports.deleteAll = (req, res) => {
  Visits.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} visits were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all visits."
      });
    });
};
