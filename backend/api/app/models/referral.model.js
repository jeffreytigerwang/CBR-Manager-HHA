module.exports = (sequelize, Sequelize) => {
    const Referral = sequelize.define("Referrals", {
        clientId: {
            type: Sequelize.INTEGER
        },
        visitId: {
            type: Sequelize.INTEGER
        },
        requirePhysiotherapy: {
            type: Sequelize.BOOLEAN
        },
        requireProsthetic: {
            type: Sequelize.BOOLEAN
        },
        requireOrthotic: {
            type: Sequelize.BOOLEAN
        },
        requireWheelchair: {
            type: Sequelize.BOOLEAN
        },
        requireOther: {
            type: Sequelize.BOOLEAN
        },
        otherDescription: {
            type: Sequelize.STRING
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
        otherDisability: {
            type: Sequelize.BOOLEAN
        },
        isInjuryAboveKnee: {
            type: Sequelize.BOOLEAN
        },
        isInjuryBelowKnee: {
            type: Sequelize.BOOLEAN
        },
        isIntermediateWheelchairUser: {
            type: Sequelize.BOOLEAN
        },
        hipWidth: {
            type: Sequelize.INTEGER
        },
        hasExistingWheelchair: {
            type: Sequelize.BOOLEAN
        },
        canRepairWheelchair: {
            type: Sequelize.BOOLEAN
        },
        outcome: {
            type: Sequelize.STRING
        }
    });

    return Referral;
};
