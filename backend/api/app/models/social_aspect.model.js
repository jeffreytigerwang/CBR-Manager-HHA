module.exports = (sequelize, Sequelize) => {
    const Social = sequelize.define("Social", {
        rateSocialStatus: {
            type: Sequelize.STRING
        },
        describeSocialStatus: {
            type: Sequelize.STRING
        },
        setGoalForSocialStatus: {
            type: Sequelize.STRING
        },
        clientId: {
            type: Sequelize.INTEGER
        }

    });
    return Social;
};
