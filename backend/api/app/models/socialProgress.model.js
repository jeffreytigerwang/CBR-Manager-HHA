module.exports = (sequelize, Sequelize) => {
    const SocialProgress = sequelize.define("SocialProgress", {
        help_provided: {
            type: Sequelize.STRING
        },
        goal_outcome: {
            type: Sequelize.STRING
        },
        conclusion: {
            type: Sequelize.STRING
        }

    })
};
