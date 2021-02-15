module.exports = (sequelize, Sequelize) => {
    const User = sequelize.define("User", {
        first_name: {
            type: Sequelize.STRING
        },
        last_name: {
            type: Sequelize.STRING
        },
        email: {
            type: Sequelize.STRING
        },
        password: {
            type: Sequelize.STRING
        },
        id: {
            type: Sequelize.INTEGER
        },
        priority_level: {
            type: Sequelize.STRING
        },
        zone: {
            type: Sequelize.STRING
        }

    })
};