module.exports = (sequelize, Sequelize) => {
    const Health = sequelize.define("Health", {
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

    return Health;
};
