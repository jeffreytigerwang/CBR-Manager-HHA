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
        healthProgressId: {
            type: Sequelize.INTEGER
        },
        educationProgressId: {
            type: Sequelize.INTEGER
        },
        socialProgressId: {
            type: Sequelize.INTEGER
        }
    });

    return Visits;
};
