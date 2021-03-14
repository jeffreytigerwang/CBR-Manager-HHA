
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

  var isEmpty = true;
  if (usersNotEmpty || clientsNotEmpty) {
    isEmpty = false;
  }

  console.log('isEmpty value is: ' + isEmpty);

  // otherwise run code
  if (!isEmpty) {
    console.log('Cant seed data, database is not empty');
  } else {
    var seed;
    seed = await db.users.create({
            firstName: 'devBypass',
            lastName: 'teamMars',
            phoneNumber: '123',
            password: '123',
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
            caregiverPresentForInterview: false,
    });
  }
  await db.sequelize.close();
}

seedData();
