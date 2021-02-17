module.exports = (sequelize, Sequelize) => {
    const Education = sequelize.define("Education", {
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

    return Education;
};
