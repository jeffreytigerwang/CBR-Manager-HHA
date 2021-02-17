const db = require("../models");
const Disability = db.disability;
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
  const disability = {
    clientId: req.body.clientId,
    amputeeDisability: req.body.amputeeDisability,
    polioDisability: req.body.polioDisability,
    spinalCordInjuryDisability: req.body.spinalCordInjuryDisability,
    cerebralPalsyDisability: req.body.cerebralPalsyDisability,
    spinalBifidaDisability: req.body.spinalBifidaDisability,
    hydrocephalusDisability: req.body.hydrocephalusDisability,
    visualImpairmentDisability: req.body.visualImpairmentDisability,
    hearingImpairmentDisability: req.body.hearingImpairmentDisability,
    doNotKnowDisability: req.body.doNotKnowDisability,
    otherDisability: req.body.otherDisability
  };

  // Save item in database
  Disability.create(disability)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Error occured creating a disabilities model."
      });
    });

};

// Retrieve all data from the database.
exports.findAll = (req, res) => {

  Disability.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving disabilities."
      });
    });
};

// Find a single item with an id
exports.findOne = (req, res) => {
  const id = req.params.id;

  Disability.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving disability with id=" + id
      });
    });
};

// Update a data item by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  Disability.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "Disability was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update Disability with id=${id}. Maybe item was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating Disability with id=" + id
      });
    });
};

// Delete a data item with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  Disability.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "Disability was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete Disability with id=${id}. Maybe item was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not delete disability with id=" + id
      });
    });
};

// Delete all data from the database.
exports.deleteAll = (req, res) => {
  Disability.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} Disabilities were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all items."
      });
    });
};
