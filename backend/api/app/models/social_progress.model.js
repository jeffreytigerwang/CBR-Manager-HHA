module.exports = (sequelize, Sequelize) => {
    const SocialProgress = sequelize.define("SocialProgress", {
        isGoalCancelled: {
            type: Sequelize.BOOLEAN
        },
        isGoalOngoing: {
            type: Sequelize.BOOLEAN
        },
        isGoalConcluded: {
            type: Sequelize.BOOLEAN
        },
        isSocialAdviceChecked: {
            type: Sequelize.BOOLEAN
        },
        isSocialAdvocacyChecked: {
            type: Sequelize.BOOLEAN
        },
        isSocialRefChecked: {
            type: Sequelize.BOOLEAN
        },
        isSocialEncouragementChecked: {
            type: Sequelize.BOOLEAN
        },
        socialAdviceDesc: {
            type: Sequelize.STRING
        },
        socialAdvocacyDesc: {
            type: Sequelize.STRING
        },
        socialRefDesc:{
            type: Sequelize.STRING
        },
        socialEncouragementDesc: {
            type: Sequelize.STRING
        },
        socialOutcomeDesc: {
            type: Sequelize.STRING
        },
        socialGoalStatus: {
            type: Sequelize.STRING
        },
        clientId: {
            type: Sequelize.INTEGER
        },
        visitId: {
            type: Sequelize.INTEGER
        }

    });

    return SocialProgress;
};
