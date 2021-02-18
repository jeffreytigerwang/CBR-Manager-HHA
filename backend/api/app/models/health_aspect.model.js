module.exports = (sequelize, Sequelize) => {
    const Health = sequelize.define("Health", {
        rateHealth: {
            type: Sequelize.STRING
        },
        describeHealth: {
            type: Sequelize.STRING
        },
        setGoalForHealth: {
            type: Sequelize.STRING
        },
        clientId: {
            type: Sequelize.INTEGER
        }

    });

    return Health;
};
