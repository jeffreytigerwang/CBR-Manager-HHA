module.exports = (sequelize, Sequelize) => {
    const Visits = sequelize.define("Visit", {
        purpose: {
            type: Sequelize.STRING
        },
        lifeAspect: {
            type: Sequelize.STRING
        },
        date: {
            type: Sequelize.DATE
        },
        workerName: {
            type: Sequelize.STRING
        },
        locationGps: {
            type: Sequelize.STRING
        },
        locationZone: {
            type: Sequelize.STRING
        },
        villageNumber: {
            type: Sequelize.INTEGER
        },
        clientId: {
            type: Sequelize.INTEGER
        }

    });

    return Visits;
};
