module.exports = (sequelize, Sequelize) => {
    const Social = sequelize.define("Social", {
        riskLevel: {
            type: Sequelize.STRING
        },
        requirements: {
            type: Sequelize.STRING
        },
        goal: {
            type: Sequelize.STRING
        },
        clientId: {
            type: Sequelize.INTEGER
        }

    });
    return Social;
};
