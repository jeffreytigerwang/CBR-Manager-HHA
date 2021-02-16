module.exports = (sequelize, Sequelize) => {
    const HealthProgress = sequelize.define("HealthProgress", {
        help_provided: {
            type: Sequelize.STRING
        },
        goal_outcome: {
            type: Sequelize.STRING
        },
        conclusion: {
            type: Sequelize.STRING
        }

    });

    return HealthProgress;
};
