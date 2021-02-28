module.exports = (sequelize, Sequelize) => {
    const User = sequelize.define("User", {
        firstName: {
            type: Sequelize.STRING
        },
        lastName: {
            type: Sequelize.STRING
        },
        email: {
            type: Sequelize.STRING,
            unique: true
        },
        password: {
            type: Sequelize.STRING
        },
        priorityLevel: {
            type: Sequelize.STRING
        },
        zone: {
            type: Sequelize.STRING
        }

    });

    return User;
};
