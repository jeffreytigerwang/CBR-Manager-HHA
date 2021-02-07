/* 
    RESTful services
*/

// import libraries
var crypto = require('crypto');
var uuid = require('uuid');
var express = require('express');
var mysql = require('mysql');
var bodyParser = require('body-parser');




// connect to MySQL
var con = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '123',
    database: 'CRM_manager'
});

con.connect(function(err) {
    if (err) 
        console.log("dis!");
    else
        console.log("Connected!");
});

// password
var generateRandomString = function(length) {
    return crypto.randomBytes(Math.ceil(length / 2))
        .toString('hex')                // convert to hex format
        .slice(0, length);              // return required number of characters
}

var SHA512 = function(password, salt) {
    var hash = crypto.createHmac('sha512', salt);
    hash.update(password);
    var value = hash.digest('hex');
    return {
        salt: salt,
        passwordHash: value
    };  
}

function saltHashPassword(userPassword) {
    var salt = generateRandomString(16);   // Gen random string with 16 character to salt
    var passwordData = SHA512(userPassword, salt);
    return passwordData;
}

function checkHashPassword(userPassword, salt) {
    var passwordData = SHA512(userPassword, salt);
    return passwordData;
}




var app = express();
app.use(bodyParser.json());                         // Accept JSON Params
app.use(bodyParser.urlencoded({extended: true}));   // Accept URL Encoded params


app.post('/register/', (req,res,next) => {

    var post_data = req.body;                           // Get POST params

    var uid = uuid.v4();
    var plaint_password = post_data.password;           // Get password from post params
    var hash_data = saltHashPassword(plaint_password);
    var password = hash_data.passwordHash;              // Get hash value
    var salt = hash_data.salt;                          // Get salt
    
    var name = post_data.name;
    var email = post_data.email;

    con.query('SELECT * FROM CRB_login_db where email=?', [email], function(err, result, fields) {
        con.on('error', function(err) {
            console.log('MySQL error', err); 
        });

        if (result && result.length)
            res.json('user already exists!');

        else
        {
            console.log('uid:          '  + uid);
            console.log('name:          '  + name);
            console.log('email:          '  + email);
            console.log('password:          '  + password);
            console.log('salt:          '  + salt);
            con.query('INSERT INTO `CRB_login_db`(`unique_id`, `name`, `email`, `password`, `salt`, `create_at`, `update_at`) VALUES (?,?,?,?,?,NOW(),NOW())', 
            [uid,name,email,password,salt], function(err, result, fields) {
                con.on('error', function(err) {
                    console.log('[MySQL error]', err); 
                    res.json('Register error', err);
                });

                res.json('Register successful');
            })   
        }


    });


})


app.post('/login/', (req,res,next) => {
    var post_data = req.body;

    var user_password = post_data.password;
    var email = post_data.email;

    con.query('SELECT * FROM CRB_login_db where email=?', [email], function(err, result, fields) {
        con.on('error', function(err) {
            console.log('MySQL error', err); 
        });

        if (result && result.length)
        {
            var salt = result[0].salt;
            var encrypted_password = result[0].password;
            var hashed_password = checkHashPassword(user_password, salt).passwordHash;
            if (encrypted_password == hashed_password)
                res.end(JSON.stringify(result[0]));
            else
                res.end(JSON.stringify("Wrong password"));
        }
        else
        {
            res.json('User not exists!!!');
        }


    });


})

app.get('/', (req,res,next) => {
    console.log('password: 123456');
    var encrypt = saltHashPassword("123456");
    console.log('Encrypt: ' + encrypt.passwordHash);
    console.log('Salt: ' + encrypt.salt);
}) 


// start server
app.listen(3000, () => {
    console.log('connect success ful')
})