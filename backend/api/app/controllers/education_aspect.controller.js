const db = require("../models");
const EducationAspect = db.education_aspect;
const Op = db.Sequelize.Op;

// Create and Save a new data
exports.create = (req, res) => {
  // Validate Request
  if (!req.body.clientId) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create Item
  const educationAspect = {
    rateEducation: req.body.rateEducation,
    describeEducation: req.body.describeEducation,
    setGoalForEducation: req.body.setGoalForEducation,
    clientId: req.body.clientId
  };

  // Save item in database
  EducationAspect.create(educationAspect)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Error occured creating a educationAspect."
      });
    });

};

// Retrieve all data from the database.
exports.findAll = (req, res) => {

  EducationAspect.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving educationAspects."
      });
    });
};

// Find a single educationAspect with an id
exports.findOne = (req, res) => {
  const id = req.params.id;

  EducationAspect.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving educationAspect with id=" + id
      });
    });
};

// Update a data item by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  EducationAspect.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "educationAspect was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update educationAspect with id=${id}. Maybe item was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating educationAspect with id=" + id
      });
    });
};

// Delete a data item with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  EducationAspect.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "educationAspect was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete educationAspect with id=${id}. Maybe item was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not educationAspect with id=" + id
      });
    });
};

// Delete all data from the database.
exports.deleteAll = (req, res) => {
  EducationAspect.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} educationAspects were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all items."
      });
    });
};
