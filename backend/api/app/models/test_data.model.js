module.exports = (sequelize, Sequelize) => {
    const cbr_test_data = sequelize.define("cbr_test_data", {
        title: {
            type: Sequelize.STRING
        },
        description: {
            type: Sequelize.STRING
        },
        active: {
            type: Sequelize.BOOLEAN
        }
    });

    return cbr_test_data;
};
