module.exports = (sequelize, Sequelize) => {
    const SocialProgress = sequelize.define("SocialProgress", {
        helpProvided: {
            type: Sequelize.STRING
        },
        goalOutcome: {
            type: Sequelize.STRING
        },
        conclusion: {
            type: Sequelize.STRING
        },
        clientId: {
            type: Sequelize.INTEGER
        },
        visitId: {
            type: Sequelize.INTEGER
        }


    });

    return SocialProgress;
};
