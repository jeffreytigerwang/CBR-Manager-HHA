module.exports = (sequelize, Sequelize) => {
    const Education = sequelize.define("Education", {
        rateEducation: {
            type: Sequelize.STRING
        },
        describeEducation: {
            type: Sequelize.STRING
        },
        setGoalForEducation: {
            type: Sequelize.STRING
        },
        clientId: {
            type: Sequelize.INTEGER
        }

    });

    return Education;
};
