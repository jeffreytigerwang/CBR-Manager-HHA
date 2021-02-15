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
    first_name: req.body.first_name,
    last_name: req.body.last_name,
    location_gps: req.body.location_gps,
    location_zone: req.body.location_zone,
    village_number: req.body.village_number,
    date_joined: req.body.date_joined,
    gender: req.body.gender,
    age: req.body.age,
    contact_number: req.body.contact_number,
    caregiver_contact: req.body.caregiver_contact,
    caregiver_present: req.body.caregiver_present,
    photo: req.body.photo,
    disability: req.body.disability,
    health_id: req.body.health_id,
    education_id: req.body.education_id,
    social_id: req.body.social_id,
    visits_id: req.body.visits_id,
    referral_id: req.body.referral_id
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
  const first_name = req.query.first_name;
  var condition = first_name ? { first_name: { [Op.like]: `%${first_name}%` } } : null;

  Clients.findAll({ where: condition })
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
