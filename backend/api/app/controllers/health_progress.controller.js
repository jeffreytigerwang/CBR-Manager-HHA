const db = require("../models");
const HealthProgress = db.health_progress;
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

  //var visitId = uuid.v4()

  // Create Item
  const healthProgress = {
    isGoalCancelled: req.body.isGoalCancelled,
    isGoalOngoing: req.body.isGoalOngoing,
    isGoalConcluded: req.body.isGoalConcluded,
    isWheelChairChecked: req.body.isWheelChairChecked,
    isProstheticChecked: req.body.isProstheticChecked,
    isOrthoticChecked: req.body.isOrthoticChecked,
    isWheelChairRepairChecked: req.body.isWheelChairRepairChecked,
    isReferralToHCChecked: req.body.isReferralToHCChecked,
    isHealthAdviceChecked: req.body.isHealthAdviceChecked,
    isHealthAdvocacyChecked: req.body.isHealthAdvocacyChecked,
    isHealthEncouragementChecked: req.body.isHealthEncouragementChecked,
    wheelChairDesc: req.body.wheelChairDesc,
    prostheticDesc: req.body.prostheticDesc,
    orthoticDesc: req.body.orthoticDesc,
    wheelChairRepairDesc: req.body.wheelChairRepairDesc,
    referralToHCDesc: req.body.referralToHCDesc,
    healthAdviceDesc: req.body.healthAdviceDesc,
    healthAdvocacyDesc: req.body.healthAdvocacyDesc,
    healthEncouragementDesc: req.body.healthEncouragementDesc,
    healthOutcomeDesc: req.body.healthOutcomeDesc,
    healthGoalStatus: req.body.healthGoalStatus,
    clientId: req.body.clientId,
    visitId: req.body.visitId
  };

  // Save item in database
  HealthProgress.create(healthProgress)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Error occured creating a healthProgress."
      });
    });

};

// Retrieve all data from the database.
exports.findAll = (req, res) => {
  const clientId = req.query.clientId;
  var condition = clientId ? { clientId: { [Op.like]: `%${clientId}%` }} : null;
  console.log('clientId: ' + clientId);
  console.log('condition: ' + condition);

  HealthProgress.findAll({ where: condition })
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving healthProgress."
      });
    });
};

// Find a single healthProgress with an id
exports.findOne = (req, res) => {
  const id = req.params.id;

  HealthProgress.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving healthProgress with id=" + id
      });
    });
};

// Update a data item by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  HealthProgress.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "healthProgress was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update healthProgress with id=${id}. Maybe item was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating healthProgress with id=" + id
      });
    });
};

// Delete a data item with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  HealthProgress.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "healthProgress was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete healthProgress with id=${id}. Maybe item was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not healthProgress with id=" + id
      });
    });
};

// Delete all data from the database.
exports.deleteAll = (req, res) => {
  HealthProgress.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} healthProgress were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all items."
      });
    });
};
