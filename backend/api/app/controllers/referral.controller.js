const db = require("../models");
const Refferals = db.refferals;
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
  const refferal = {
    clientId: req.body.clientId,
    visitId: req.body.visitId,
    requirePhysiotherapy: req.body.requirePhysiotherapy,
    requireProsthetic: req.body.requireProsthetic,
    requireOrthotic: req.body.requireOrthotic,
    requireWheelchair: req.body.requireWheelchair,
    requireOther: req.body.requireOther,
    otherDescription: req.body.otherDescription,
    amputeeDisability: req.body.amputeeDisability,
    polioDisability: req.body.polioDisability,
    spinalCordInjuryDisability: req.body.spinalCordInjuryDisability,
    cerebralPalsyDisability: req.body.cerebralPalsyDisability,
    spinaBifidaDisability: req.body.spinaBifidaDisability,
    hydrocephalusDisability: req.body.hydrocephalusDisability,
    visualImpairmentDisability: req.body.visualImpairmentDisability,
    otherDisability: req.body.otherDisability,
    isInjuryAboveKnee: req.body.isInjuryAboveKnee,
    isInjuryBelowKnee: req.body.isInjuryBelowKnee,
    isIntermediateWheelchairUser: req.body.isIntermediateWheelchairUser,
    hipWidth: req.body.hipWidth,
    hasExistingWheelchair: req.body.hasExistingWheelchair,
    canRepairWheelchair: req.body.canRepairWheelchair,
    outcome: req.body.outcome
  };

  // Save item in database
  Referrals.create(referral)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Error occured creating a referral."
      });
    });

};

// Retrieve all data from the database.
exports.findAll = (req, res) => {
  Referrals.findAll()
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving referrals."
      });
    });
};

// Find a single referral with an id
exports.findOne = (req, res) => {
  const id = req.params.id;

  Referrals.findByPk(id)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message: "Error retrieving Referral with id=" + id
      });
    });
};

// Update a data item by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  Referrals.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "referral was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update referral with id=${id}. Maybe item was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating referral with id=" + id
      });
    });
};

// Delete a data item with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  Referrals.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "referral was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete referral with id=${id}. Maybe item was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not referral with id=" + id
      });
    });
};

// Delete all data from the database.
exports.deleteAll = (req, res) => {
  Referrals.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} referrals were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all items."
      });
    });
};
