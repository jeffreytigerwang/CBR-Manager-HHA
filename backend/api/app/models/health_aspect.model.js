module.exports = (sequelize, Sequelize) => {
    const Health = sequelize.define("Health", {
        riskLevel: {
            type: Sequelize.STRING
        },
        requirements: {
            type: Sequelize.STRING
        },
        goal: {
            type: Sequelize.STRING
        },
        progressIds: {
            type: Sequelize.ARRAY(Sequelize.INTEGER)
        }

    });

    return Health;
};
