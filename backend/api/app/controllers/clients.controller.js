const db = require("../models");
const Clients = db.clients;
const Op = db.Sequelize.Op;

// Create and Save a new data
exports.create = (req, res) => {
  // Validate Request
  if (!req.body.first_name) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create Item
  const client = {
    firstName: req.body.firstName,
    lastName: req.body.lastName,
    locationGps: req.body.locationGps,
    locationZone: req.body.locationZone,
    villageNumber: req.body.villageNumber,
    dateJoined: req.body.dateJoined,
    gender: req.body.gender,
    age: req.body.age,
    contactNumber: req.body.contactNumber,
    caregiverContact: req.body.caregiverContact,
    caregiverPresent: req.body.caregiverPresent,
    photo: req.body.photo
  };

  // Save item in database
  Clients.create(client)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Error occured creating a client."
      });
    });

};

// Retrieve all data from the database.
exports.findAll = (req, res) => {

  Clients.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving clients."
      });
    });
};

// Find a single client with an id
exports.findOne = (req, res) => {
  const id = req.params.id;

  Clients.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving Client with id=" + id
      });
    });
};

// Update a data item by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  Clients.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "client was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update client with id=${id}. Maybe item was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating client with id=" + id
      });
    });
};

// Delete a data item with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  Clients.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "client was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete client with id=${id}. Maybe item was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not client with id=" + id
      });
    });
};

// Delete all data from the database.
exports.deleteAll = (req, res) => {
  Clients.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} clients were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all items."
      });
    });
};
