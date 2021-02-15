module.exports = (sequelize, Sequelize) => {
    const Visits = sequelize.define("Visit", {
        purpose: {
            type: Sequelize.STRING
        },
        life_aspect: {
            type: Sequelize.STRING
        },
        date: {
            type: Sequelize.DATE
        },
        worker_name: {
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
        health_progress_id: {
            type: Sequelize.INTEGER
        },
        education_progress_id: {
            type: Sequelize.INTEGER
        },
        social_progress_id: {
            type: Sequelize.INTEGER
        }
    })
};
