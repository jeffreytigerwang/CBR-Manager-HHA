module.exports = (sequelize, Sequelize) => {
    const Disability = sequelize.define("Disability", {
        clientId: {
            type: Sequelize.INTEGER
        },
        amputeeDisability: {
            type: Sequelize.BOOLEAN
        },
        polioDisability: {
            type: Sequelize.BOOLEAN
        },
        spinalCordInjuryDisability: {
            type: Sequelize.BOOLEAN
        },
        cerebralPalsyDisability: {
            type: Sequelize.BOOLEAN
        },
        spinaBifidaDisability: {
            type: Sequelize.BOOLEAN
        },
        hydrocephalusDisability: {
            type: Sequelize.BOOLEAN
        },
        visualImpairmentDisability: {
            type: Sequelize.BOOLEAN
        },
        hearingImpairmentDisability: {
            type: Sequelize.BOOLEAN
        },
        doNotKnowDisability: {
            type: Sequelize.BOOLEAN
        },
        otherDisability: {
            type: Sequelize.BOOLEAN
        },
        describeOtherDisability: {
            type: Sequelize.STRING
        }

    });

    return Disability;
};
