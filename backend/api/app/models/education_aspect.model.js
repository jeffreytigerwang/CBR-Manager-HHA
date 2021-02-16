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
        progressIds: {
            type: Sequelize.ARRAY(Sequelize.INTEGER)
        }

    });

    return Education;
};
