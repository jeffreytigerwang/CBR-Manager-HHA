const db = require("../models");
const Messages = db.message;
const Op = db.Sequelize.Op;

// Create and Save a new data
exports.create = (req, res) => {
  // Validate Request
  if (!req.body.userId) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create Item
  const message = {
    userId: req.body.userId,
    firstName: req.body.firstName,
    lastName: req.body.lastName,
    message: req.body.message,
    postDate: req.body.postDate,
    pic: req.body.pic
  };

  // Save item in database
  Messages.create(message)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Error occured creating a message."
      });
    });

};

// Retrieve all data from the database.
exports.findAll = (req, res) => {

  Messages.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving messages."
      });
    });
};

// Find a single user with an id
exports.findOne = (req, res) => {
  const id = req.params.id;

  Messages.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving message with id=" + id
      });
    });
};

// Update a data item by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  Messages.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "message was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update message with id=${id}. Maybe item was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating message with id=" + id
      });
    });
};

// Delete a data item with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  Messages.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "message was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete message with id=${id}. Maybe item was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not find message with id=" + id
      });
    });
};

// Delete all data from the database.
exports.deleteAll = (req, res) => {
  Messages.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} messages were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all items."
      });
    });
};
