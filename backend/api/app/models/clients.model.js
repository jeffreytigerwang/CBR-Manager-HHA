module.exports = (sequelize, Sequelize) => {
    const Client = sequelize.define("Client", {
        firstName: {
            type: Sequelize.STRING
        },
        lastName: {
            type: Sequelize.STRING
        },
        clientId: {
            type: Sequelize.INTEGER
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
        careGiverFirstName: {
            type: Sequelize.STRING
        },
        careGiverLastName: {
            type: Sequelize.STRING
        },
        caregiverPresentForInterview: {
            type: Sequelize.BOOLEAN
        },
        caregiverContactNumber: {
            type: Sequelize.STRING
        },
        photo: {
            type: Sequelize.BLOB()
        },
        describeOtherDisability {
            type: Sequelize.STRING
        }


    });

    return Client;
};
