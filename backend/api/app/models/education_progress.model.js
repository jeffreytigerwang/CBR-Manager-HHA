module.exports = (sequelize, Sequelize) => {
    const EducationProgress = sequelize.define("EducationProgress", {
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

    return EducationProgress;
};
