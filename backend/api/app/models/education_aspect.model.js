module.exports = (sequelize, Sequelize) => {
    const Education = sequelize.define("Education", {
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

    return Education;
};
