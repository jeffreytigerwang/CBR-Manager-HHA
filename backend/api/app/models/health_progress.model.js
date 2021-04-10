module.exports = (sequelize, Sequelize) => {
    const HealthProgress = sequelize.define("HealthProgress", {
        isGoalCancelled: {
            type: Sequelize.BOOLEAN
        },
        isGoalOngoing: {
            type: Sequelize.BOOLEAN
        },
        isGoalConcluded: {
            type: Sequelize.BOOLEAN
        },
        isWheelChairChecked: {
            type: Sequelize.BOOLEAN
        },
        isProstheticChecked: {
            type: Sequelize.BOOLEAN
        },
        isOrthoticChecked: {
            type: Sequelize.BOOLEAN
        },
        isWheelChairRepairChecked: {
            type: Sequelize.BOOLEAN
        },
        isReferralToHCChecked: {
            type: Sequelize.BOOLEAN
        },
        isHealthAdviceChecked: {
            type: Sequelize.BOOLEAN
        },
        isHealthAdvocacyChecked: {
            type: Sequelize.BOOLEAN
        },
        isHealthEncouragementChecked: {
            type: Sequelize.BOOLEAN
        },
        wheelChairDesc: {
            type: Sequelize.STRING
        },
        prostheticDesc: {
            type: Sequelize.STRING
        },
        orthoticDesc: {
            type: Sequelize.STRING
        },
        wheelChairRepairDesc: {
            type: Sequelize.STRING
        },
        referralToHCDesc: {
            type: Sequelize.STRING
        },
        healthAdviceDesc: {
            type: Sequelize.STRING
        },
        healthAdvocacyDesc: {
            type: Sequelize.STRING
        },
        healthEncouragementDesc: {
            type: Sequelize.STRING
        },
        healthOutcomeDesc: {
            type: Sequelize.STRING
        },
        healthGoalStatus: {
            type: Sequelize.STRING
        },
        clientId: {
            type: Sequelize.INTEGER
        },
        visitId: {
            type: Sequelize.INTEGER
        }

    });

    return HealthProgress;
};
