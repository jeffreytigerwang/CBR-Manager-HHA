module.exports = (sequelize, Sequelize) => {
    const User = sequelize.define("User", {
        firstName: {
            type: Sequelize.STRING
        },
        lastName: {
            type: Sequelize.STRING
        },
        phoneNumber: {
            type: Sequelize.STRING,
	    unique: true
        },
        password: {
            type: Sequelize.STRING
        },
        priorityLevel: {
            type: Sequelize.STRING
        },
        zones: {
            type: Sequelize.STRING
        },
        userType: {
            type: Sequelize.STRING
        }

    });

    return User;
};
