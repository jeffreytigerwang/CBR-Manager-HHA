module.exports = (sequelize, Sequelize) => {
    const EducationProgress = sequelize.define("EducationProgress", {
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