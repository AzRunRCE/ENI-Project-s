const jwt = require("jsonwebtoken")
const { SECRET } = require("./env")
const { check, validationResult } = require("express-validator")
const express = require("express");
const { uuid } = require("uuidv4")
const swaggerAutogen = require('swagger-autogen')()
const outputFile = './swagger_output.json' 
swaggerAutogen(outputFile, ['index.js'])

const swaggerUi = require('swagger-ui-express'); 
const swaggerDocument = require(outputFile); 

const app = express()
app.use(express.urlencoded());
app.use(express.json());


const users = [{
    id: 1,
    userName: 'admin',
    password: "passw0rd"
}]
const cities = [];

function extractBearerToken(headerValue) { 
    if (typeof headerValue !== 'string') { return false } 
    const matches = headerValue.match(/(bearer)\s+(\S+)/i)
    return matches && matches[2] 
}
    
function checkTokenMiddleware(req, res, next) { 
    const token = req.headers.authorization && extractBearerToken(req.headers.authorization) 
    if (!token) { 
        return res.status(401).json({ message: 'Error. Need a token' }) 
    } 
    jwt.verify(token, SECRET, (err, decodedToken) => { 
        if (err) { 
            res.status(401).json({ message: 'Error. Bad token' }) 
        } else {
             return next()
        } 
    })
} 


app.post("/authentication_token", (req, res) => {
    const user = users.find(u => u.password === req.body.password && u.userName === req.body.username);

    if (user == null) {
        return res.status(400).json({ error: "Error: Wrong login or password" })
    }
    else {
        const token = jwt.sign({
            id: user.id,
            username: user.userName
        }, SECRET, { expiresIn: "3 hours" })

        return res.json({ acces_token: token });
    }

});


app.get('/cities', checkTokenMiddleware,  async (req, res) => { 
   return res.json(cities);
});


app.post('/cities', checkTokenMiddleware, 
    check('name') .isLength({ min: 3 }) 
    .withMessage('City name must be at least 3 characters long'), async (req, res) => { 
    const errors = validationResult(req)

    if (!errors.isEmpty()) { 
        return res.status(422).json(errors.array()) 
    } 
    let city = { name: req.body.name, uuid: uuid() };

    cities.push(city);

    return res.status(201).json(city) 
})



app.use('/docs', swaggerUi.serve, swaggerUi.setup(swaggerDocument));


app.listen(3000);