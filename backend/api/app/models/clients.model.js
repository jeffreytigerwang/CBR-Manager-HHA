module.exports = (sequelize, Sequelize) => {
    const Client = sequelize.define("Client", {
        first_name: {
            type: Sequelize.STRING
        },
        last_name: {
            type: Sequelize.STRING
        },
        location_gps: {
            type: Sequelize.STRING
        },
        location_zone: {
            type: Sequelize.STRING
        },
        village_number: {
            type: Sequelize.INTEGER
        },
        date_joined: {
            type: Sequelize.DATE
        },
        gender: {
            type: Sequelize.STRING
        },
        age: {
            type: Sequelize.INTEGER
        },
        contact_number: {
            type: Sequelize.STRING
        },
        caregiver_present: {
            type: Sequelize.BOOLEAN
        },
        caregiver_contact: {
            type: Sequelize.INTEGER
        },
        photo: {
            type: Sequelize.BLOB('long')
        },
        disability: {
            type: Sequelize.STRING
        },
        health_id: {
            type: Sequelize.INTEGER
        },
        education_id: {
            type: Sequelize.INTEGER
        },
        social_id: {
            type: Sequelize.INTEGER
        },
        visits_id: {
            type: Sequelize.INTEGER
        },
        referral_id: {
            type: Sequelize.INTEGER
        }

    });

    return Client;
};
