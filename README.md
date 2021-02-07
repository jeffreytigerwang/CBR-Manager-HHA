## Notes for Use

In server.js file, comment out the code below to stop reseting database every
time server.js runs:

```
db.sequelize.sync({force: true}).then(() => {
  console.log('Drop and Resync Db');
  initial();
});
```

Ensure the following code runs instead:

```
db.sequelize.sync();
```
