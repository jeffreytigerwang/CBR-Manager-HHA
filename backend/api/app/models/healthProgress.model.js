module.exports = (sequelize, Sequelize) => {
    const HealthProgress = sequelize.define("HealthProgress", {
        id: {
            type: Sequelize.STRING
        },
        help_provided: {
            type: Sequelize.STRING
        },
        goal_outcome: {
            type: Sequelize.STRING
        },
        conclusion: {
            type: Sequelize.STRING
        }

    })
};