const db = require("../models");
const EducationAspects = db.education_aspects;
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
  const educationAspect = {
    riskLevel: req.body.riskLevel,
    requirements: req.body.requirements,
    goal: req.body.goal,
    progressIds: req.body.progressIds

  };

  // Save item in database
  EducationAspects.create(educationAspect)
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

  EducationAspects.findAll()
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

  EducationAspects.findByPk(id)
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

  EducationAspects.update(req.body, {
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

  EducationAspects.destroy({
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
  EducationAspects.destroy({
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
