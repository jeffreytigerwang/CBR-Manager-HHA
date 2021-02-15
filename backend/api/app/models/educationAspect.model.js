module.exports = (sequelize, Sequelize) => {
    const Education = sequelize.define("Education", {
        id: {
            type: Sequelize.STRING
        },
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
            
        }

    })
};