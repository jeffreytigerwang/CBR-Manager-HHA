module.exports = (sequelize, Sequelize) => {
    const EducationProgress = sequelize.define("EducationProgress", {
        isGoalCancelled: {
            type: Sequelize.BOOLEAN
        },
        isGoalOngoing: {
            type: Sequelize.BOOLEAN
        },
        isGoalConcluded: {
            type: Sequelize.BOOLEAN
        },
        isEducationAdviceChecked: {
            type: Sequelize.BOOLEAN
        },
        isEducationAdvocacyChecked: {
            type: Sequelize.BOOLEAN
        },
        isEducationReferralChecked: {
            type: Sequelize.BOOLEAN
        },
        isEducationEncouragementChecked: {
            type: Sequelize.BOOLEAN
        },
        educationAdviceDesc: {
            type: Sequelize.STRING
        },
        educationAdvocacyDesc: {
            type: Sequelize.STRING
        },
        educationReferralDesc: {
            type: Sequelize.STRING
        },
        educationEncouragementDesc: {
            type: Sequelize.STRING
        },
        educationOutcomeDesc: {
            type: Sequelize.STRING
        },
        educationGoalStatus: {
            type: Sequelize.STRING
        },
        clientId: {
            type: Sequelize.INTEGER
        },
        visitId: {
            type: Sequelize.INTEGER
        }

    });

    return EducationProgress;
};
