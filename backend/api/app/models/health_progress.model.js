module.exports = (sequelize, Sequelize) => {
    const HealthProgress = sequelize.define("HealthProgress", {
        helpProvided: {
            type: Sequelize.STRING
        },
        goalOutcome: {
            type: Sequelize.STRING
        },
        conclusion: {
            type: Sequelize.STRING
        }

    });

    return HealthProgress;
};
