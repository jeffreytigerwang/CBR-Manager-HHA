const db = require("../models");
const EducationProgress = db.education_progress;
const Op = db.Sequelize.Op;

// Create and Save a new data
exports.create = (req, res) => {
  // Validate Request
  if (!req.body.helpProvided) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create Item
  const educationProgress = {
    helpProvided: req.body.helpProvided,
    goalOutcome: req.body.goalOutcome,
    conclusion: req.body.conclusion,
    clientId: req.body.clientId,
    visitId: req.body.visitId
  };

  // Save item in database
  EducationProgress.create(educationProgress)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Error occured creating a educationProgress."
      });
    });

};

// Retrieve all data from the database.
exports.findAll = (req, res) => {

  EducationProgress.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving educationProgress."
      });
    });
};

// Find a single educationProgress with an id
exports.findOne = (req, res) => {
  const id = req.params.id;

  EducationProgress.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving educationProgress with id=" + id
      });
    });
};

// Update a data item by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  EducationProgress.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "educationProgress was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update educationProgress with id=${id}. Maybe item was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating educationProgress with id=" + id
      });
    });
};

// Delete a data item with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  EducationProgress.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "educationProgress was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete educationProgress with id=${id}. Maybe item was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not educationProgress with id=" + id
      });
    });
};

// Delete all data from the database.
exports.deleteAll = (req, res) => {
  EducationProgress.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} educationProgress were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all items."
      });
    });
};
