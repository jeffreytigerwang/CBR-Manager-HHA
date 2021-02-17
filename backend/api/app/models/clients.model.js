module.exports = (sequelize, Sequelize) => {
    const Client = sequelize.define("Client", {
        firstName: {
            type: Sequelize.STRING
        },
        lastName: {
            type: Sequelize.STRING
        },
        locationGps: {
            type: Sequelize.STRING
        },
        locationZone: {
            type: Sequelize.STRING
        },
        villageNumber: {
            type: Sequelize.INTEGER
        },
        dateJoined: {
            type: Sequelize.DATE
        },
        gender: {
            type: Sequelize.STRING
        },
        age: {
            type: Sequelize.INTEGER
        },
        contactNumber: {
            type: Sequelize.STRING
        },
        caregiverPresent: {
            type: Sequelize.BOOLEAN
        },
        caregiverContact: {
            type: Sequelize.INTEGER
        },
        photo: {
            type: Sequelize.BLOB('long')
        },
        healthId: {
            type: Sequelize.INTEGER
        },
        educationId: {
            type: Sequelize.INTEGER
        },
        socialId: {
            type: Sequelize.INTEGER
        },
        visitsId: {
            type: Sequelize.INTEGER
        },
        referralId: {
            type: Sequelize.INTEGER
        }

    });

    return Client;
};
