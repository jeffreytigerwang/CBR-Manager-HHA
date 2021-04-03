module.exports = (sequelize, Sequelize) => {
    const Visits = sequelize.define("Visit", {
        isCBRChecked: {
            type: Sequelize.BOOLEAN
        },
        isDCRChecked: {
            type: Sequelize.BOOLEAN
        },
        isDCRFUChecked: {
            type: Sequelize.BOOLEAN
        },
        isHealthChecked: {
            type: Sequelize.BOOLEAN
        },
        isEducationChecked: {
            type: Sequelize.BOOLEAN
        },
        isSocialChecked: {
            type: Sequelize.BOOLEAN
        },
        purposeOfVisit: {
            type: Sequelize.STRING
        },
        dateOfVisit: {
            type: Sequelize.DATE
        },
        workerName: {
            type: Sequelize.STRING
        },
        visitGpsLocation: {
            type: Sequelize.STRING
        },
        visitZoneLocation: {
            type: Sequelize.STRING
        },
        villageNumber: {
            type: Sequelize.INTEGER
        },
        clientId: {
            type: Sequelize.INTEGER
        },
        visitId: {
            type: Sequelize.INTEGER
        }

    });

    return Visits;
};
