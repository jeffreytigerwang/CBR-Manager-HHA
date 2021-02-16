module.exports = (sequelize, Sequelize) => {
    const Social = sequelize.define("Social", {
        risk_level: {
            type: Sequelize.STRING
        },
        requirements: {
            type: Sequelize.STRING
        },
        goal: {
            type: Sequelize.STRING
        },
        progress_ids: {
            type: Sequelize.ARRAY(Sequelize.INTEGER)
        }

    });
    return Social;
};
