//https://semaphoreci.com/community/tutorials/dockerizing-a-node-js-web-application
// adapted tests from this tutorial
const db = require('./app/models');

beforeAll(async () => {
    await db.sequelize.sync();
});

// TESTS FOR CLIENT MODEL

test('create client', async () => {
    expect.assertions(1);
    const client = await db.clients.create({
        id: 1,
        firstName: 'Kainoa',
        lastName: 'Seaman'
    });
    expect(client.id).toEqual(1);
});

test('get client', async () => {
    expect.assertions(2);
    const client = await db.clients.findByPk(1);
    expect(client.firstName).toEqual('Kainoa');
    expect(client.lastName).toEqual('Seaman');
});

test('delete client', async () => {
    expect.assertions(1);
    await db.clients.destroy({
        where: {
            id: 1
        }
    });
    const client = await db.clients.findByPk(1);
    expect(client).toBeNull();
});

// TESTS FOR USER MODEL

test('create user', async () => {
    expect.assertions(1);
    const user = await db.users.create({
        id: 1,
        firstName: 'Kainoa',
        lastName: 'Seaman',
        email: 'kseaman@sfu.ca',
        password: 'password'
    });
    expect(user.id).toEqual(1);
});

test('unique user email', async () => {
    expect.assertions(1);
    try {
       const user = await db.users.create({
           id: 2,
           firstName: 'Kane',
           lastName: 'Seaman',
           email: 'kseaman1@sfu.ca',
           password: 'password'
       });
    } catch (e) {
      expect(e.message).toEqual('Validation error');
    }
});

test('delete user', async () => {
    expect.assertions(1);
    await db.users.destroy({
        where: {
            id: 1
        }
    });
    const user = await db.users.findByPk(1);
    expect(user).toBeNull();
});

afterAll(async () => {
    await db.sequelize.close();
});
