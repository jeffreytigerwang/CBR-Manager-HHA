module.exports = (sequelize, Sequelize) => {
    const EducationProgress = sequelize.define("EducationProgress", {
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
    
    return EducationProgress;
};
