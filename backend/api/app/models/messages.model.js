module.exports = (sequelize, Sequelize) => {
    const Messages = sequelize.define("Messages", {
        userId: {
            type: Sequelize.INTEGER
        },
        firstName: {
            type: Sequelize.STRING
        },
        lastName: {
            type: Sequelize.STRING
        },
        message: {
            type: Sequelize.STRING
        },
        postDate: {
            type: Sequelize.DATE
        },
        pic: {
            type: Sequelize.BLOB
        },
    });

    return Messages;
};
