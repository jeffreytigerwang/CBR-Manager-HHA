
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

  var isEmpty = true;
  if (usersNotEmpty || clientsNotEmpty || healthAspectNotEmpty
      || visitsNotEmpty || healthProgressNotEmpty) {
    isEmpty = false;
  }

  console.log('isEmpty value is: ' + isEmpty);

  // otherwise run code
  if (!isEmpty) {
    console.log('Cant seed data, database is not empty');
  } else {
    var seed;
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
            phoneNumber: '666-666-6666',
            password: 'password',
            zones: '',
            userType: 'Admin'
    });
    seed = await db.users.create({
            firstName: 'Eniola',
            lastName: 'Muhammad',
            phoneNumber: '555-555-5555',
            password: 'password',
            zones: 'Bidibidi Zone 1',
            userType: 'CBR Worker'
    });
    seed = await db.users.create({
            firstName: 'Gbenga',
            lastName: 'Muhammad',
            phoneNumber: '444-444-4444',
            password: 'password',
            zones: 'Bidibidi Zone 2',
            userType: 'CBR Worker'
    });
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
            contactNumber: '222-222-2222',
            caregiverContactNumber: '123-456-2222',
            caregiverPresentForInterview: false,
    });
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
            contactNumber: '111-111-1111',
            caregiverContactNumber: '123-456-2222',
            caregiverPresentForInterview: false,
    });
    seed = await db.health_aspect.create({
            rateHealth: 'medium risk',
            describeHealth: 'Najwa has trouble walking',
            setGoalForHealth: 'Najwa needs a cane or wheelchair',
            clientId: 1234
    });
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
    console.log('All seed data implemented');
  }
  await db.sequelize.close();
}

seedData();
