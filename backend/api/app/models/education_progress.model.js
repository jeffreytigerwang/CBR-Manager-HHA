module.exports = (sequelize, Sequelize) => {
    const EducationProgress = sequelize.define("EducationProgress", {
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
            type: Sequelize.STRING
        }

    });

    return EducationProgress;
};
