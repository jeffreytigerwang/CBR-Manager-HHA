module.exports = (sequelize, Sequelize) => {
    const Client = sequelize.define("Client", {
        firstName: {
            type: Sequelize.STRING
        },
        lastName: {
            type: Sequelize.STRING
        },
        gpsLocation: {
            type: Sequelize.STRING
        },
        zoneLocation: {
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
        caregiverPresentForInterview: {
            type: Sequelize.BOOLEAN
        },
        caregiverContactNumber: {
            type: Sequelize.INTEGER
        },
        photo: {
            type: Sequelize.BLOB('long')
        }


    });

    return Client;
};
