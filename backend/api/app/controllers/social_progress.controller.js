const db = require("../models");
const SocialProgress = db.social_progress;
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
  const socialProgress = {
    isGoalCancelled: req.body.isGoalCancelled,
    isGoalOngoing: req.body.isGoalOngoing,
    isGoalConcluded: req.body.isGoalConcluded,
    isSocialAdviceChecked: req.body.isSocialAdviceChecked,
    isSocialAdvocacyChecked: req.body.isSocialAdvocacyChecked,
    isSocialRefChecked: req.body.isSocialRefChecked,
    isSocialEncouragementChecked: req.body.isSocialEncouragementChecked,
    socialAdviceDesc: req.body.socialAdviceDesc,
    socialAdvocacyDesc: req.body.socialAdvocacyDesc,
    socialRefDesc: req.body.socialRefDesc,
    socialEncouragementDesc: req.body.socialEncouragementDesc,
    socialOutcomeDesc: req.body.socialOutcomeDesc,
    socialGoalStatus: req.body.socialGoalStatus,
    clientId: req.body.clientId,
    visitId: req.body.visitId
  };

  // Save item in database
  SocialProgress.create(socialProgress)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Error occured creating a socialProgress."
      });
    });

};

// Retrieve all data from the database.
exports.findAll = (req, res) => {
  const clientId = req.query.clientId;
  var condition = clientId ? { clientId: { [Op.like]: `%${clientId}%` }} : null;
  console.log('clientId: ' + clientId);
  console.log('condition: ' + condition);

  SOcialProgress.findAll({ where: condition })
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving socialProgress."
      });
    });
};

// Find a single socialProgress with an id
exports.findOne = (req, res) => {
  const id = req.params.id;

  SocialProgress.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving socialProgress with id=" + id
      });
    });
};

// Update a data item by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  SocialProgress.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "socialProgress was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update socialProgress with id=${id}. Maybe item was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating socialProgress with id=" + id
      });
    });
};

// Delete a data item with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  SocialProgress.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "socialProgress was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete socialProgress with id=${id}. Maybe item was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not socialProgress with id=" + id
      });
    });
};

// Delete all data from the database.
exports.deleteAll = (req, res) => {
  SocialProgress.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} socialProgress were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all items."
      });
    });
};
