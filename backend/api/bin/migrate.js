
var db = require('../app/models/index.js');

// lat between 2.18 and 3.56
// lon between 30.08 and 33.34
const gpsLocations = ["3.53, 31.36", "3.26, 32.36", "3.07, 31.10",
                      "3.28, 31.28", "3.22, 31.73", "3.25, 31.87",
                      "2.67, 31.83", "2.23, 30.90", "3.10, 32.67",
                      "2.5, 30.99", "3.21, 31.22", "3.77, 3.10"];


async function seedData() {
  await db.sequelize.sync();
  // test database is empty

  const usersNotEmpty = await db.users.findByPk(1).then(user => {
    console.log('user returned: ' + user);
    if (user) { return 1; }
    else { return 0; }
  })

  const clientsNotEmpty = await db.clients.findByPk(1).then(client => {
    console.log('client returned: ' + client);
    if (client) { return 1; }
    else { return 0; }
  })

  const healthAspectNotEmpty = await db.health_aspect.findByPk(1).then(data => {
    console.log('health aspect returned: ' + data);
    if (data) { return 1; }
    else { return 0; }
  })

  const visitsNotEmpty = await db.visits.findByPk(1).then(data => {
    console.log('visits returned: ' + data);
    if (data) { return 1; }
    else { return 0; }
  })

  const healthProgressNotEmpty = await db.health_progress.findByPk(1).then(data => {
    console.log('health progress returned: ' + data);
    if (data) { return 1; }
    else { return 0; }
  })

  const disabilityNotEmpty = await db.disability.findByPk(1).then(data => {
    console.log('disability returned: ' + data);
    if (data) { return 1; }
    else { return 0; }
  })

  const socialAspectNotEmpty = await db.social_aspect.findByPk(1).then(data => {
    console.log('social aspect returned: ' + data);
    if (data) { return 1; }
    else { return 0; }
  })

  const educationAspectNotEmpty = await db.education_aspect.findByPk(1).then(data => {
    console.log('education aspect returned: ' + data);
    if (data) { return 1; }
    else { return 0; }
  })




  var isEmpty = true;
  if (usersNotEmpty || clientsNotEmpty || healthAspectNotEmpty
      || visitsNotEmpty || healthProgressNotEmpty || disabilityNotEmpty
      || socialAspectNotEmpty || educationAspectNotEmpty) {
    isEmpty = false;
  }

  console.log('isEmpty value is: ' + isEmpty);

  // otherwise run code
  if (!isEmpty) {
    console.log('Cant seed data, database is not empty');
  } else {
    var seed;

    // create all users

    seed = await db.users.create({
            firstName: 'Team',
            lastName: 'Mars',
            phoneNumber: '123',
            password: 'IrkUvqBct8QmbhJdl18khDfsDYksvhzO7100fWU3SDQ=',
            zones: '',
            userType: 'Admin'
    });
    seed = await db.users.create({
            firstName: 'Amadi',
            lastName: 'Yusuf',
            phoneNumber: '6666666666',
            password: 'password',
            zones: 'Bidibidi Zone 2',
            userType: 'CBR Worker'
    });
    seed = await db.users.create({
            firstName: 'Eniola',
            lastName: 'Muhammad',
            phoneNumber: '5555555555',
            password: 'password',
            zones: 'Bidibidi Zone 1',
            userType: 'CBR Worker'
    });
    seed = await db.users.create({
            firstName: 'Gbenga',
            lastName: 'Muhammad',
            phoneNumber: '4444444444',
            password: 'password',
            zones: 'Bidibidi Zone 2',
            userType: 'CBR Worker'
    });
    seed = await db.users.create({
            firstName: 'Darasimi',
            lastName: 'Ali',
            phoneNumber: '1111111111',
            password: 'password',
            zones: 'Palorinya Zone 2',
            userType: 'admin'
    });
    seed = await db.users.create({
            firstName: 'Kikelomo',
            lastName: 'Ali',
            phoneNumber: '7787787788',
            password: 'password',
            zones: 'Palorinya Zone 3',
            userType: 'CBR Worker'
    });
    seed = await db.users.create({
            firstName: 'Bisi',
            lastName: 'Garba',
            phoneNumber: '7788788788',
            password: 'password',
            zones: 'Bidibidi Zone 3',
            userType: 'CBR Worker'
    });
    seed = await db.users.create({
            firstName: 'Olawale',
            lastName: 'Umar',
            phoneNumber: '1788283788',
            password: 'password',
            zones: 'Bidibidi Zone 2',
            userType: 'CBR Worker'
    });
    seed = await db.users.create({
            firstName: 'Damola',
            lastName: 'Usman',
            phoneNumber: '1688289988',
            password: 'password',
            zones: 'Bidibidi Zone 3',
            userType: 'CBR Worker'
    });
    seed = await db.users.create({
            firstName: 'Olaoluwa',
            lastName: 'Abubakar',
            phoneNumber: '1283283788',
            password: 'password',
            zones: 'Bidibidi Zone 5',
            userType: 'CBR Worker'
    });



    // create client #1 info

    seed = await db.clients.create({
            firstName: 'Najwa',
            lastName: 'Sarpong',
            clientId: 1234,
            gpsLocation: gpsLocations[0],
            zoneLocation: 'BidiBidi',
            villageNumber: 5,
            dateJoined: '2021-02-10',
            gender: 'female',
            age: 19,
            contactNumber: '2222222222',
            caregiverContactNumber: 1234562222,
            caregiverPresentForInterview: false,
    });
    seed = await db.disability.create({
            clientId: 1234,
            amputeeDisability: false,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: false,
            visualImpairmentDisability: false,
            hearingImpairmentDisability: false,
            doNotKnowDisability: false,
            otherDisability: false,
            specifyDisability: ''
    });
    seed = await db.health_aspect.create({
            rateHealth: 'Critical risk',
            describeHealth: 'Najwa has trouble walking',
            setGoalForHealth: 'Najwa needs a cane or wheelchair',
            clientId: 1234
    });
    seed = await db.education_aspect.create({
            rateEducation: 'Low risk',
            describeEducation: 'Najwa is well educated',
            setGoalForEducation: 'Read a few more books',
            clientId: 1234
    });
    seed = await db.social_aspect.create({
            rateSocialStatus: 'Low risk',
            describeSocialStatus: 'Najwa is social',
            setGoalForSocialStatus: 'continue healthy social life',
            clientId: 1234
    });


    // create client #1 visits data

    seed = await db.visits.create({
            isCBRChecked:	true,
            isDCRChecked:	true,
            isDCRFUChecked:	false,
            isHealthChecked: true,
            isEducationChecked: false,
            isSocialChecked: false,
            purposeOfVisit: 'checking on ability to walk',
            dateOfVisit: '2021-02-10',
            workerName: 'Amadi Yusuf',
            visitGpsLocation: gpsLocations[0],
            visitZoneLocation: 'Bidibidi Zone 1',
            villageNumber: 5,
            clientId: 1234,
            visitId: 9000
    });
    seed = await db.referrals.create({
            clientId: 1234,
            visitId: 1234,
            requirePhysiotherapy: true,
            requireProsthetic: false,
            requireOrthotic: false,
            requireWheelchair: true,
            requireOther: false,
            otherDescription: "",
            amputeeDisability: false,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: false,
            visualImpairmentDisability: false,
            otherDisability: false,
            isInjuryAboveKnee: false,
            isInjuryBelowKnee: true,
            isInjuryAboveElbow: false,
            isInjuryBelowElbow: false,
            isIntermediateWheelchairUser: false,
            hipWidth: 32,
            hasExistingWheelchair: false,
            canRepairWheelchair: false,
            outcome: "client's son will got to the health center on her behalf",
            resolved: true

    });
    seed = await db.health_progress.create({
            isWheelChairChecked: true,
            isProstheticChecked: false,
            isOrthoticChecked: false,
            isWheelChairRepairChecked: false,
            isReferralToHCChecked: true,
            isHealthAdviceChecked: true,
            isHealthAdvocacyChecked: false,
            isHealthEncouragementChecked: false,
            wheelChairDesc: "may need a wheelchair for weak legs",
            prostheticDesc: "",
            orthoticDesc: "",
            wheelChairRepairDesc: "",
            referralToHCDesc: "given direction and phone number",
            healthAdviceDesc: "should get children to help with chores",
            healthAdvocacyDesc: "",
            healthEncouragementDesc: "",
            healthOutcomeDesc: "",
            healthGoalDesc: "",
            clientId: 1234,
            visitId: 9000
     });
     seed = await db.visits.create({
            isHealthChecked: true,
            isEducationChecked: false,
            isSocialChecked: false,
            purposeOfVisit: 'checking if she was able to get a wheelchair',
            dateOfVisit: '2021-02-20',
            workerName: 'Amadi Yusuf',
            visitGpsLocation: gpsLocations[0],
            visitZoneLocation: 'Bidibidi Zone 1',
            villageNumber: 5,
            clientId: 1234,
            visitId: 9001
    });
    seed = await db.health_progress.create({
            isWheelChairChecked: true,
            isProstheticChecked: false,
            isOrthoticChecked: false,
            isWheelChairRepairChecked: false,
            isReferralToHCChecked: false,
            isHealthAdviceChecked: true,
            isHealthAdvocacyChecked: false,
            isHealthEncouragementChecked: false,
            wheelChairDesc: "her kid went to health center and got her a wheelchair",
            prostheticDesc: "",
            orthoticDesc: "",
            wheelChairRepairDesc: "",
            referralToHCDesc: "",
            healthAdviceDesc: "",
            healthAdvocacyDesc: "",
            healthEncouragementDesc: "",
            healthOutcomeDesc: "",
            healthGoalDesc: "",
            clientId: 1234,
            visitId: 9000
    });
    seed = await db.visits.create({
            isHealthChecked: true,
            isEducationChecked: false,
            isSocialChecked: false,
            purposeOfVisit: 'heard her wheelchair broke',
            dateOfVisit: '2021-02-25',
            workerName: 'Amadi Yusuf',
            visitGpsLocation: gpsLocations[0],
            visitZoneLocation: 'Bidibidi Zone 1',
            villageNumber: 5,
            clientId: 1234,
            visitId: 9002
    });
    seed = await db.health_progress.create({
            isWheelChairChecked: false,
            isProstheticChecked: false,
            isOrthoticChecked: false,
            isWheelChairRepairChecked: true,
            isReferralToHCChecked: false,
            isHealthAdviceChecked: false,
            isHealthAdvocacyChecked: false,
            isHealthEncouragementChecked: false,
            wheelChairDesc: "",
            prostheticDesc: "",
            orthoticDesc: "",
            wheelChairRepairDesc: "Left wheel is broken. Set up appointment with health center",
            referralToHCDesc: "",
            healthAdviceDesc: "",
            healthAdvocacyDesc: "",
            healthEncouragementDesc: "",
            healthOutcomeDesc: "",
            healthGoalDesc: "Make sure she fixes her wheel chair",
            clientId: 1234,
            visitId: 9000
    });

    // create client #2

    seed = await db.clients.create({
            firstName: 'Vashon',
            lastName: 'Sarpong',
            clientId: 1270,
            gpsLocation: gpsLocations[1],
            zoneLocation: 'Palorinya',
            villageNumber: 1,
            dateJoined: '2021-02-10',
            gender: 'male',
            age: 65,
            contactNumber: '1111111111',
            caregiverContactNumber: 1234562222,
            caregiverPresentForInterview: false,
    });
    seed = await db.disability.create({
            clientId: 1270,
            amputeeDisability: false,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: false,
            visualImpairmentDisability: false,
            hearingImpairmentDisability: true,
            doNotKnowDisability: false,
            otherDisability: false,
            specifyDisability: ''
    });
    seed = await db.health_aspect.create({
            rateHealth: 'High risk',
            describeHealth: 'Cant hear well out of both ears',
            setGoalForHealth: 'Going to help Vashon and his family learn sign language',
            clientId: 1270
    });
    seed = await db.education_aspect.create({
            rateEducation: 'Low risk',
            describeEducation: 'Vashon is very smart but uninterested in continuing education in his elderly years',
            setGoalForEducation: 'Find game to help him keep his mind healthy',
            clientId: 1270
    });
    seed = await db.social_aspect.create({
            rateSocialStatus: 'Medium risk',
            describeSocialStatus: 'Vashon spends a lot of time with his family, but doesnt see other people much \
            as he is hard of hearing',
            setGoalForSocialStatus: 'Help Vashon find other elderly friends',
            clientId: 1270
    });

    // create client #2 visits data

    seed = await db.visits.create({
            isHealthChecked: true,
            isEducationChecked: false,
            isSocialChecked: false,
            purposeOfVisit: 'checking on ability hear',
            dateOfVisit: '2021-02-11',
            workerName: 'Amadi Yusuf',
            visitGpsLocation: gpsLocations[1],
            visitZoneLocation: 'Palorinya Zone 1',
            villageNumber: 1,
            clientId: 1270,
            visitId: 8000
    });
    seed = await db.referrals.create({
            clientId: 1270,
            visitId: 8000,
            requirePhysiotherapy: false,
            requireProsthetic: false,
            requireOrthotic: false,
            requireWheelchair: false,
            requireOther: true,
            otherDescription: "May need hearing aids, or to learn sign",
            amputeeDisability: false,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: false,
            visualImpairmentDisability: false,
            otherDisability: false,
            isInjuryAboveKnee: false,
            isInjuryBelowKnee: false,
            isInjuryAboveElbow: false,
            isInjuryBelowElbow: false,
            isIntermediateWheelchairUser: false,
            hipWidth: 32,
            hasExistingWheelchair: false,
            canRepairWheelchair: false,
            outcome: "Needs to see a doctor to test hearing ability",
            resolved: true

    });

    seed = await db.health_progress.create({
            isWheelChairChecked: false,
            isProstheticChecked: false,
            isOrthoticChecked: false,
            isWheelChairRepairChecked: false,
            isReferralToHCChecked: true,
            isHealthAdviceChecked: true,
            isHealthAdvocacyChecked: false,
            isHealthEncouragementChecked: false,
            wheelChairDesc: "",
            prostheticDesc: "",
            orthoticDesc: "",
            wheelChairRepairDesc: "",
            referralToHCDesc: "given direction and phone number",
            healthAdviceDesc: "Going to teach Vashon and his family to sign",
            healthAdvocacyDesc: "",
            healthEncouragementDesc: "",
            healthOutcomeDesc: "",
            healthGoalDesc: "",
            clientId: 1270,
            visitId: 8000
     });
     seed = await db.visits.create({
            isHealthChecked: true,
            isEducationChecked: false,
            isSocialChecked: false,
            purposeOfVisit: 'checking eye sight',
            dateOfVisit: '2021-02-20',
            workerName: 'Amadi Yusuf',
            visitGpsLocation: gpsLocations[1],
            visitZoneLocation: 'Palorinya Zone 1',
            villageNumber: 1,
            clientId: 1270,
            visitId: 8001
    });
    seed = await db.health_progress.create({
            isWheelChairChecked: false,
            isProstheticChecked: false,
            isOrthoticChecked: false,
            isWheelChairRepairChecked: false,
            isReferralToHCChecked: false,
            isHealthAdviceChecked: true,
            isHealthAdvocacyChecked: false,
            isHealthEncouragementChecked: true,
            wheelChairDesc: "",
            prostheticDesc: "",
            orthoticDesc: "",
            wheelChairRepairDesc: "",
            referralToHCDesc: "",
            healthAdviceDesc: "Vashon is having trouble learning to sign",
            healthAdvocacyDesc: "",
            healthEncouragementDesc: "Giving tips on learning sign language",
            healthOutcomeDesc: "",
            healthGoalDesc: "",
            clientId: 1270,
            visitId: 8001
    });
    seed = await db.visits.create({
            isHealthChecked: true,
            isEducationChecked: false,
            isSocialChecked: false,
            purposeOfVisit: 'found some learn to sign books',
            dateOfVisit: '2021-02-25',
            workerName: 'Amadi Yusuf',
            visitGpsLocation: gpsLocations[1],
            visitZoneLocation: 'Palorinya Zone 1',
            villageNumber: 1,
            clientId: 1270,
            visitId: 8002
    });
    seed = await db.health_progress.create({
            isWheelChairChecked: false,
            isProstheticChecked: false,
            isOrthoticChecked: false,
            isWheelChairRepairChecked: false,
            isReferralToHCChecked: true,
            isHealthAdviceChecked: true,
            isHealthAdvocacyChecked: false,
            isHealthEncouragementChecked: false,
            wheelChairDesc: "",
            prostheticDesc: "",
            orthoticDesc: "",
            wheelChairRepairDesc: "",
            referralToHCDesc: "",
            healthAdviceDesc: "Time for monthly check up at health center",
            healthAdvocacyDesc: "",
            healthEncouragementDesc: "",
            healthOutcomeDesc: "",
            healthGoalDesc: "Make sure grandkids learn to sign with grandpa",
            clientId: 1270,
            visitId: 8002
    });

    // adding in bulk clients

    seed = await db.clients.create({
            firstName: 'Kanesha',
            lastName: 'Tersoo',
            clientId: 1230,
            gpsLocation: gpsLocations[2],
            zoneLocation: 'Palorinya Basecamp',
            villageNumber: 3,
            dateJoined: '2021-03-10',
            gender: 'female',
            age: 56,
            contactNumber: '7777777777',
            caregiverContactNumber: 1234562222,
            caregiverPresentForInterview: true,
    });
    seed = await db.disability.create({
            clientId: 1230,
            amputeeDisability: false,
            polioDisability: true,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: true,
            spinaBifidaDisability: false,
            hydrocephalusDisability: false,
            visualImpairmentDisability: false,
            hearingImpairmentDisability: false,
            doNotKnowDisability: false,
            otherDisability: false,
            specifyDisability: ''
    });
    seed = await db.health_aspect.create({
            rateHealth: 'Medium risk',
            describeHealth: 'has been living with complications with awhile, but not much pain',
            setGoalForHealth: 'get her to see a doctor at clinic more often',
            clientId: 1230
    });
    seed = await db.education_aspect.create({
            rateEducation: 'Critical risk',
            describeEducation: 'cant read, and needs assistance',
            setGoalForEducation: 'find volunteer to read newspaper once a week',
            clientId: 1230
    });
    seed = await db.social_aspect.create({
            rateSocialStatus: 'High risk',
            describeSocialStatus: 'Is not close with family, so needs to spend more time in community',
            setGoalForSocialStatus: 'need to find a good club for her to join',
            clientId: 1230
    });

    seed = await db.visits.create({
            isHealthChecked: false,
            isEducationChecked: false,
            isSocialChecked: true,
            purposeOfVisit: 'initial consultation',
            dateOfVisit: '2021-03-01',
            workerName: 'Eniola Muhammad',
            visitGpsLocation: gpsLocations[7],
            visitZoneLocation: 'Palorinya Basecamp',
            villageNumber: 4,
            clientId: 1230,
            visitId: 3123
    });
    seed = await db.referrals.create({
            clientId: 1230,
            visitId: 3123,
            requirePhysiotherapy: false,
            requireProsthetic: false,
            requireOrthotic: false,
            requireWheelchair: false,
            requireOther: true,
            otherDescription: "Needs assistance learning to read",
            amputeeDisability: false,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: false,
            visualImpairmentDisability: false,
            otherDisability: false,
            isInjuryAboveKnee: false,
            isInjuryBelowKnee: false,
            isInjuryAboveElbow: false,
            isInjuryBelowElbow: false,
            isIntermediateWheelchairUser: false,
            hipWidth: 32,
            hasExistingWheelchair: false,
            canRepairWheelchair: false,
            outcome: "CBR worker to follow up on reading progress",
            resolved: false

    });


    seed = await db.clients.create({
            firstName: 'Abioye',
            lastName: 'Deka',
            clientId: 1220,
            gpsLocation: gpsLocations[2],
            zoneLocation: 'Bidibidi Zone 4',
            villageNumber: 4,
            dateJoined: '2021-01-15',
            gender: 'male',
            age: 37,
            contactNumber: '1111111111',
            caregiverContactNumber: 1234562222,
            caregiverPresentForInterview: true,
    });
    seed = await db.disability.create({
            clientId: 1220,
            amputeeDisability: false,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: false,
            visualImpairmentDisability: true,
            hearingImpairmentDisability: false,
            doNotKnowDisability: false,
            otherDisability: false,
            specifyDisability: ''
    });
    seed = await db.health_aspect.create({
            rateHealth: 'Medium risk',
            describeHealth: 'cant see at all, but family helps him',
            setGoalForHealth: 'help find new tools to help him become more independent',
            clientId: 1220
    });
    seed = await db.education_aspect.create({
            rateEducation: 'Low risk',
            describeEducation: 'used to be a school teacher',
            setGoalForEducation: 'make sure family reads things to him more often',
            clientId: 1220
    });
    seed = await db.social_aspect.create({
            rateSocialStatus: 'Medium risk',
            describeSocialStatus: 'socializes with family, but periods pass when he isnt spoken to',
            setGoalForSocialStatus: 'need to speak with family and ensure his needs are met every day',
            clientId: 1220
    });
    seed = await db.visits.create({
            isHealthChecked: true,
            isEducationChecked: false,
            isSocialChecked: true,
            purposeOfVisit: 'initial consultation',
            dateOfVisit: '2021-01-13',
            workerName: 'Eniola Muhammad',
            visitGpsLocation: gpsLocations[8],
            visitZoneLocation: 'Palorinya Zone 3',
            villageNumber: 6,
            clientId: 1220,
            visitId: 3999
    });
    seed = await db.referrals.create({
            clientId: 1220,
            visitId: 3999,
            requirePhysiotherapy: false,
            requireProsthetic: false,
            requireOrthotic: false,
            requireWheelchair: false,
            requireOther: true,
            otherDescription: "requires learning braile",
            amputeeDisability: false,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: false,
            visualImpairmentDisability: true,
            otherDisability: false,
            isInjuryAboveKnee: false,
            isInjuryBelowKnee: false,
            isInjuryAboveElbow: false,
            isInjuryBelowElbow: false,
            isIntermediateWheelchairUser: false,
            hipWidth: 32,
            hasExistingWheelchair: false,
            canRepairWheelchair: false,
            outcome: "Client is very educated, but can no longer see. Must learn braile" +
            "to keep mental ability up",
            resolved: true
    });



    seed = await db.clients.create({
            firstName: 'Lakista',
            lastName: 'Onai',
            clientId: 2220,
            gpsLocation: gpsLocations[3],
            zoneLocation: 'Bidibidi Zone 4',
            villageNumber: 4,
            dateJoined: '2021-01-15',
            gender: 'female',
            age: 25,
            contactNumber: '1111111111',
            caregiverContactNumber: 1234562222,
            caregiverPresentForInterview: true,
    });
    seed = await db.disability.create({
            clientId: 2220,
            amputeeDisability: false,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: false,
            visualImpairmentDisability: true,
            hearingImpairmentDisability: false,
            doNotKnowDisability: false,
            otherDisability: false,
            specifyDisability: ''
    });
    seed = await db.health_aspect.create({
            rateHealth: 'Low risk',
            describeHealth: 'healthy for their age',
            setGoalForHealth: 'more vegetables for well rounded diet',
            clientId: 2220
    });
    seed = await db.education_aspect.create({
            rateEducation: 'Low risk',
            describeEducation: 'very smart',
            setGoalForEducation: 'make sure they read more',
            clientId: 2220
    });
    seed = await db.social_aspect.create({
            rateSocialStatus: 'Medium risk',
            describeSocialStatus: 'doesnt have many people to talke to, and has periods of depression',
            setGoalForSocialStatus: 'join our self help groups and socializing events each week',
            clientId: 2220
    });

    seed = await db.visits.create({
            isHealthChecked: true,
            isEducationChecked: true,
            isSocialChecked: true,
            purposeOfVisit: 'initial consultation',
            dateOfVisit: '2020-12-23',
            workerName: 'Ali Darasimi',
            visitGpsLocation: gpsLocations[9],
            visitZoneLocation: 'Palorinya Zone 3',
            villageNumber: 6,
            clientId: 2220,
            visitId: 3998
    });
    seed = await db.referrals.create({
            clientId: 2220,
            visitId: 3998,
            requirePhysiotherapy: true,
            requireProsthetic: true,
            requireOrthotic: false,
            requireWheelchair: false,
            requireOther: false,
            otherDescription: "",
            amputeeDisability: false,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: true,
            visualImpairmentDisability: true,
            otherDisability: false,
            isInjuryAboveKnee: false,
            isInjuryBelowKnee: false,
            isInjuryAboveElbow: false,
            isInjuryBelowElbow: false,
            isIntermediateWheelchairUser: false,
            hipWidth: 32,
            hasExistingWheelchair: false,
            canRepairWheelchair: false,
            outcome: "needs social assistance",
            resolved: false
    });


    seed = await db.clients.create({
            firstName: 'Bimpe',
            lastName: 'Umar',
            clientId: 3220,
            gpsLocation: gpsLocations[4],
            zoneLocation: 'Bidibidi Zone 1',
            villageNumber: 3,
            dateJoined: '2021-01-15',
            gender: 'female',
            age: 35,
            contactNumber: '1111111111',
            caregiverContactNumber: 1234562222,
            caregiverPresentForInterview: true,
    });
    seed = await db.disability.create({
            clientId: 3220,
            amputeeDisability: false,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: false,
            visualImpairmentDisability: true,
            hearingImpairmentDisability: false,
            doNotKnowDisability: false,
            otherDisability: false,
            specifyDisability: ''
    });
    seed = await db.health_aspect.create({
            rateHealth: 'Low risk',
            describeHealth: 'healthy for their age',
            setGoalForHealth: 'more vegetables for well rounded diet',
            clientId: 3220
    });
    seed = await db.education_aspect.create({
            rateEducation: 'Low risk',
            describeEducation: 'very smart',
            setGoalForEducation: 'make sure they read more',
            clientId: 3220
    });
    seed = await db.social_aspect.create({
            rateSocialStatus: 'Medium risk',
            describeSocialStatus: 'doesnt have many people to talke to, and has periods of depression',
            setGoalForSocialStatus: 'join our self help groups and socializing events each week',
            clientId: 3220
    });

    seed = await db.visits.create({
            isHealthChecked: true,
            isEducationChecked: true,
            isSocialChecked: true,
            purposeOfVisit: 'initial consultation',
            dateOfVisit: '2020-11-15',
            workerName: 'Kikelomo Darasimi',
            visitGpsLocation: gpsLocations[10],
            visitZoneLocation: 'Palorinya Zone 2',
            villageNumber: 6,
            clientId: 3220,
            visitId: 3997
    });
    seed = await db.referrals.create({
            clientId: 3220,
            visitId: 3997,
            requirePhysiotherapy: true,
            requireProsthetic: true,
            requireOrthotic: true,
            requireWheelchair: true,
            requireOther: false,
            otherDescription: "",
            amputeeDisability: true,
            polioDisability: true,
            spinalCordInjuryDisability: true,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: true,
            visualImpairmentDisability: true,
            otherDisability: false,
            isInjuryAboveKnee: false,
            isInjuryBelowKnee: false,
            isInjuryAboveElbow: false,
            isInjuryBelowElbow: false,
            isIntermediateWheelchairUser: false,
            hipWidth: 32,
            hasExistingWheelchair: false,
            canRepairWheelchair: false,
            outcome: "needs education assistance",
            resolved: true
    });




    seed = await db.clients.create({
            firstName: 'Tosin',
            lastName: 'Sani',
            clientId: 4220,
            gpsLocation: gpsLocations[5],
            zoneLocation: 'Bidibidi Zone 5',
            villageNumber: 2,
            dateJoined: '2021-01-15',
            gender: 'male',
            age: 45,
            contactNumber: '1111111111',
            caregiverContactNumber: 1234562222,
            caregiverPresentForInterview: true,
    });
    seed = await db.disability.create({
            clientId: 4220,
            amputeeDisability: true,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: true,
            visualImpairmentDisability: true,
            hearingImpairmentDisability: false,
            doNotKnowDisability: false,
            otherDisability: false,
            specifyDisability: ''
    });
    seed = await db.health_aspect.create({
            rateHealth: 'Low risk',
            describeHealth: 'healthy for their age',
            setGoalForHealth: 'more vegetables for well rounded diet',
            clientId: 4220
    });
    seed = await db.education_aspect.create({
            rateEducation: 'Low risk',
            describeEducation: 'very smart',
            setGoalForEducation: 'make sure they read more',
            clientId: 4220
    });
    seed = await db.social_aspect.create({
            rateSocialStatus: 'Medium risk',
            describeSocialStatus: 'doesnt have many people to talke to, and has periods of depression',
            setGoalForSocialStatus: 'join our self help groups and socializing events each week',
            clientId: 4220
    });

    seed = await db.visits.create({
            isHealthChecked: true,
            isEducationChecked: true,
            isSocialChecked: true,
            purposeOfVisit: 'initial consultation',
            dateOfVisit: '2020-09-17',
            workerName: 'Kikelomo Darasimi',
            visitGpsLocation: gpsLocations[12],
            visitZoneLocation: 'Palorinya Zone 1',
            villageNumber: 3,
            clientId: 4220,
            visitId: 3996
    });
    seed = await db.referrals.create({
            clientId: 4220,
            visitId: 3996,
            requirePhysiotherapy: true,
            requireProsthetic: true,
            requireOrthotic: true,
            requireWheelchair: true,
            requireOther: false,
            otherDescription: "",
            amputeeDisability: true,
            polioDisability: true,
            spinalCordInjuryDisability: true,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: true,
            visualImpairmentDisability: true,
            otherDisability: true,
            isInjuryAboveKnee: true,
            isInjuryBelowKnee: true,
            isInjuryAboveElbow: true,
            isInjuryBelowElbow: false,
            isIntermediateWheelchairUser: true,
            hipWidth: 32,
            hasExistingWheelchair: false,
            canRepairWheelchair: false,
            outcome: "needs health assistance",
            resolved: true
    });



    seed = await db.clients.create({
            firstName: 'Ayomide',
            lastName: 'Usman',
            clientId: 5220,
            gpsLocation: gpsLocations[6],
            zoneLocation: 'Bidibidi Zone 5',
            villageNumber: 2,
            dateJoined: '08-Apr-2021',
            gender: 'male',
            age: 45,
            contactNumber: '1111111111',
            caregiverContactNumber: 1234562222,
            caregiverPresentForInterview: true,
    });
    seed = await db.disability.create({
            clientId: 5220,
            amputeeDisability: true,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: true,
            visualImpairmentDisability: true,
            hearingImpairmentDisability: false,
            doNotKnowDisability: false,
            otherDisability: false,
            specifyDisability: ''
    });
    seed = await db.health_aspect.create({
            rateHealth: 'Low risk',
            describeHealth: 'healthy for their age',
            setGoalForHealth: 'more vegetables for well rounded diet',
            clientId: 5220
    });
    seed = await db.education_aspect.create({
            rateEducation: 'Low risk',
            describeEducation: 'very smart',
            setGoalForEducation: 'make sure they read more',
            clientId: 5220
    });
    seed = await db.social_aspect.create({
            rateSocialStatus: 'Medium risk',
            describeSocialStatus: 'doesnt have many people to talke to, and has periods of depression',
            setGoalForSocialStatus: 'join our self help groups and socializing events each week',
            clientId: 5220
    });

    seed = await db.visits.create({
            isHealthChecked: true,
            isEducationChecked: true,
            isSocialChecked: true,
            purposeOfVisit: 'initial consultation',
            dateOfVisit: '2020-08-04',
            workerName: 'Bisi Garba',
            visitGpsLocation: gpsLocations[12],
            visitZoneLocation: 'Palorinya Zone 2',
            villageNumber: 3,
            clientId: 5220,
            visitId: 3995
    });
    seed = await db.referrals.create({
            clientId: 5220,
            visitId: 3995,
            requirePhysiotherapy: true,
            requireProsthetic: true,
            requireOrthotic: true,
            requireWheelchair: true,
            requireOther: false,
            otherDescription: "",
            amputeeDisability: true,
            polioDisability: true,
            spinalCordInjuryDisability: true,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: true,
            visualImpairmentDisability: true,
            otherDisability: true,
            isInjuryAboveKnee: true,
            isInjuryBelowKnee: true,
            isInjuryAboveElbow: true,
            isInjuryBelowElbow: false,
            isIntermediateWheelchairUser: true,
            hipWidth: 32,
            hasExistingWheelchair: false,
            canRepairWheelchair: false,
            outcome: "needs health assistance",
            resolved: false
    });

    // bulk referrals + disabilities to get stats numbers up
    //
    seed = await db.referrals.create({
            clientId: 6220,
            visitId: 3994,
            requirePhysiotherapy: true,
            requireProsthetic: true,
            requireOrthotic: false,
            requireWheelchair: true,
            requireOther: false,
            otherDescription: "",
            amputeeDisability: true,
            polioDisability: false,
            spinalCordInjuryDisability: true,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: true,
            hydrocephalusDisability: true,
            visualImpairmentDisability: false,
            otherDisability: true,
            isInjuryAboveKnee: true,
            isInjuryBelowKnee: false,
            isInjuryAboveElbow: true,
            isInjuryBelowElbow: false,
            isIntermediateWheelchairUser: true,
            hipWidth: 32,
            hasExistingWheelchair: true,
            canRepairWheelchair: true,
            outcome: "needs social assistance",
            resolved: false
    });
    seed = await db.referrals.create({
            clientId: 6220,
            visitId: 3994,
            requirePhysiotherapy: true,
            requireProsthetic: true,
            requireOrthotic: false,
            requireWheelchair: true,
            requireOther: false,
            otherDescription: "",
            amputeeDisability: true,
            polioDisability: false,
            spinalCordInjuryDisability: true,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: true,
            hydrocephalusDisability: true,
            visualImpairmentDisability: false,
            otherDisability: true,
            isInjuryAboveKnee: true,
            isInjuryBelowKnee: false,
            isInjuryAboveElbow: true,
            isInjuryBelowElbow: false,
            isIntermediateWheelchairUser: true,
            hipWidth: 32,
            hasExistingWheelchair: true,
            canRepairWheelchair: true,
            outcome: "needs social assistance",
            resolved: false
    });
    seed = await db.referrals.create({
            clientId: 6220,
            visitId: 3993,
            requirePhysiotherapy: true,
            requireProsthetic: true,
            requireOrthotic: false,
            requireWheelchair: true,
            requireOther: false,
            otherDescription: "",
            amputeeDisability: true,
            polioDisability: false,
            spinalCordInjuryDisability: true,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: true,
            hydrocephalusDisability: true,
            visualImpairmentDisability: false,
            otherDisability: true,
            isInjuryAboveKnee: true,
            isInjuryBelowKnee: false,
            isInjuryAboveElbow: true,
            isInjuryBelowElbow: false,
            isIntermediateWheelchairUser: true,
            hipWidth: 32,
            hasExistingWheelchair: true,
            canRepairWheelchair: true,
            outcome: "needs health assistance",
            resolved: true
    });
    seed = await db.referrals.create({
            clientId: 6220,
            visitId: 3992,
            requirePhysiotherapy: true,
            requireProsthetic: true,
            requireOrthotic: false,
            requireWheelchair: true,
            requireOther: false,
            otherDescription: "",
            amputeeDisability: true,
            polioDisability: false,
            spinalCordInjuryDisability: true,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: true,
            hydrocephalusDisability: true,
            visualImpairmentDisability: false,
            otherDisability: true,
            isInjuryAboveKnee: true,
            isInjuryBelowKnee: false,
            isInjuryAboveElbow: true,
            isInjuryBelowElbow: false,
            isIntermediateWheelchairUser: true,
            hipWidth: 32,
            hasExistingWheelchair: true,
            canRepairWheelchair: true,
            outcome: "needs education assistance",
            resolved: false
    });
    seed = await db.disability.create({
            clientId: 6220,
            amputeeDisability: true,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: true,
            spinaBifidaDisability: false,
            hydrocephalusDisability: true,
            visualImpairmentDisability: true,
            hearingImpairmentDisability: false,
            doNotKnowDisability: false,
            otherDisability: false,
            specifyDisability: ''
    });
    seed = await db.disability.create({
            clientId: 6220,
            amputeeDisability: true,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: true,
            spinaBifidaDisability: false,
            hydrocephalusDisability: true,
            visualImpairmentDisability: true,
            hearingImpairmentDisability: false,
            doNotKnowDisability: false,
            otherDisability: false,
            specifyDisability: ''
    });
    seed = await db.disability.create({
            clientId: 6220,
            amputeeDisability: true,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: true,
            spinaBifidaDisability: true,
            hydrocephalusDisability: false,
            visualImpairmentDisability: true,
            hearingImpairmentDisability: true,
            doNotKnowDisability: true,
            otherDisability: false,
            specifyDisability: ''
    });
    seed = await db.disability.create({
            clientId: 6220,
            amputeeDisability: true,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: false,
            spinaBifidaDisability: false,
            hydrocephalusDisability: true,
            visualImpairmentDisability: true,
            hearingImpairmentDisability: true,
            doNotKnowDisability: false,
            otherDisability: true,
            specifyDisability: 'Bad back'
    });
    seed = await db.disability.create({
            clientId: 6220,
            amputeeDisability: true,
            polioDisability: false,
            spinalCordInjuryDisability: false,
            cerebralPalsyDisability: true,
            spinaBifidaDisability: true,
            hydrocephalusDisability: false,
            visualImpairmentDisability: false,
            hearingImpairmentDisability: false,
            doNotKnowDisability: false,
            otherDisability: false,
            specifyDisability: ''
    });
    seed = await db.disability.create({
            clientId: 6220,
            amputeeDisability: true,
            polioDisability: true,
            spinalCordInjuryDisability: true,
            cerebralPalsyDisability: true,
            spinaBifidaDisability: true,
            hydrocephalusDisability: false,
            visualImpairmentDisability: true,
            hearingImpairmentDisability: true,
            doNotKnowDisability: true,
            otherDisability: true,
            specifyDisability: 'Who knows'
    });
    seed = await db.disability.create({
            clientId: 6220,
            amputeeDisability: true,
            polioDisability: true,
            spinalCordInjuryDisability: true,
            cerebralPalsyDisability: true,
            spinaBifidaDisability: true,
            hydrocephalusDisability: false,
            visualImpairmentDisability: true,
            hearingImpairmentDisability: true,
            doNotKnowDisability: true,
            otherDisability: true,
            specifyDisability: 'Who knows'
    });
    seed = await db.disability.create({
            clientId: 6220,
            amputeeDisability: true,
            polioDisability: true,
            spinalCordInjuryDisability: true,
            cerebralPalsyDisability: true,
            spinaBifidaDisability: true,
            hydrocephalusDisability: false,
            visualImpairmentDisability: true,
            hearingImpairmentDisability: true,
            doNotKnowDisability: true,
            otherDisability: true,
            specifyDisability: 'Who knows'
    });

    // Messages
    seed = await db.message.create({
            userId:	1,
            firstName: "Team",
            lastName: "Mars",
            message: "Hello Everyone! The messages services is now active. Please make a test message" +
            "from the app!",
            postDate: "2021-01-11T06:44:08.000Z"
    });
    seed = await db.message.create({
            userId:	2,
            firstName: "Bisi",
            lastName: "Garba",
            message: "Hello Everyone! Testing testing!",
            postDate: "2021-01-11T06:47:08.000Z"
    });
    seed = await db.message.create({
            userId:	3,
            firstName: "Kikelomo",
            lastName: "Ali",
            message: "Hello Bisi, happy to hear from you",
            postDate: "2021-01-11T06:48:08.000Z"
    });
    seed = await db.message.create({
            userId:	2,
            firstName: "Bisi",
            lastName: "Garba",
            message: "Hello Kikelomo, how is your family?",
            postDate: "2021-01-11T07:39:08.000Z"
    });
    seed = await db.message.create({
            userId:	2,
            firstName: "Bisi",
            lastName: "Garba",
            message: "Hello Kikelomo? Did you get my messages?",
            postDate: "2021-01-11T08:44:08.000Z"
    });
    seed = await db.message.create({
            userId:	3,
            firstName: "Kikelomo",
            lastName: "Ali",
            message: "Yes Bisi. But I am trying to get my work done",
            postDate: "2021-01-11T08:48:08.000Z"
    });
    seed = await db.message.create({
            userId:	4,
            firstName: "Damola",
            lastName: "Usman",
            message: "Did someone eat my sandwhich from the refrigerator?",
            postDate: "2021-02-10T10:43:08.000Z"
    });
    seed = await db.message.create({
            userId:	5,
            firstName: "Amadi",
            lastName: "Yusuf",
            message: "I am sorry Damola, I might have eaten your sandwhich",
            postDate: "2021-02-10T10:48:08.000Z"
    });
    seed = await db.message.create({
            userId:	5,
            firstName: "Amadi",
            lastName: "Yusuf",
            message: "I will make it up for you by visiting your last two clients",
            postDate: "2021-02-10T10:49:08.000Z"
    });
    seed = await db.message.create({
            userId:	7,
            firstName: "Olaoluwa",
            lastName: "Abubakar",
            message: "The service around Bibdibidi Zone 3 is not great. Please remember to " +
            "sync your data before entering that location",
            postDate: "2021-02-10T10:48:08.000Z"
    });
    seed = await db.message.create({
            userId:	6,
            firstName: "Gbenga",
            lastName: "Muhammad",
            message: "If anyone is heading out to the Palorinya settlement, there" +
            " is an accident on the bridge. Try to take another route",
            postDate: "2021-03-01T12:48:08.000Z"
    });
    seed = await db.message.create({
            userId:	8,
            firstName: "Najwa",
            lastName: "Owuli",
            message: "Hello admin center? My son is ill and I cannot make the rest" +
            " of my visits. Is that okay?",
            postDate: "2021-04-10T10:48:08.000Z"
    });















    console.log('All seed data implemented');
  }
  await db.sequelize.close();
}

seedData();
