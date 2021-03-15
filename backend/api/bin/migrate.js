
var db = require('../app/models/index.js');


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

    // create users

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
            zones: '',
            userType: 'Admin'
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
    // create client #1 info

    seed = await db.clients.create({
            firstName: 'Najwa',
            lastName: 'Sarpong',
            clientId: 1234,
            gpsLocation: '49.23, -120.00',
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
            rateHealth: 'critical risk',
            describeHealth: 'Najwa has trouble walking',
            setGoalForHealth: 'Najwa needs a cane or wheelchair',
            clientId: 1234
    });
    seed = await db.education_aspect.create({
            rateEducation: 'low risk',
            describeEducation: 'Najwa is well educated',
            setGoalForEducation: 'Read a few more books',
            clientId: 1234
    });
    seed = await db.social_aspect.create({
            rateSocialStatus: 'low risk',
            describeSocialStatus: 'Najwa is social',
            setGoalForSocialStatus: 'continue healthy social life',
            clientId: 1234
    });


    // create client #1 visits data

    seed = await db.visits.create({
            isHealthChecked: true,
            isEducationChecked: false,
            isSocialChecked: false,
            purposeOfVisit: 'checking on ability to walk',
            dateOfVisit: '2021-02-10',
            workerName: 'Amadi Yusuf',
            visitGpsLocation: '49.99, -130.01',
            visitZoneLocation: 'Bidibidi Zone 1',
            villageNumber: 5,
            clientId: 1234,
            visitId: 9000
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
            visitGpsLocation: '49.99, -130.01',
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
            visitGpsLocation: '49.99, -130.01',
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
            gpsLocation: '49.23, -120.00',
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
            rateHealth: 'high risk',
            describeHealth: 'Cant hear well out of both ears',
            setGoalForHealth: 'Going to help Vashon and his family learn sign language',
            clientId: 1270
    });
    seed = await db.education_aspect.create({
            rateEducation: 'low risk',
            describeEducation: 'Vashon is very smart but uninterested in continuing education in his elderly years',
            setGoalForEducation: 'Find game to help him keep his mind healthy',
            clientId: 1270
    });
    seed = await db.social_aspect.create({
            rateSocialStatus: 'medium risk',
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
            visitGpsLocation: '49.99, -130.01',
            visitZoneLocation: 'Palorinya Zone 1',
            villageNumber: 1,
            clientId: 1270,
            visitId: 8000
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
            visitGpsLocation: '49.99, -130.01',
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
            visitGpsLocation: '49.99, -130.01',
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
            isHealthAdviceChecked: false,
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
            clientId: 1234,
            visitId: 9000
    });




    console.log('All seed data implemented');
  }
  await db.sequelize.close();
}

seedData();
