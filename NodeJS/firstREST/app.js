const express = require('express');
const morgan = require('morgan');
const boydParser = require('body-parser');
const mongoose = require('mongoose');


const app = express();

const userRoutes = require('./api/routes/user');
const bodyParser = require('body-parser');


mongoose.connect(
    'mongodb+srv://root:rootpass@cluster0.ajh8h.mongodb.net/<dbname>?retryWrites=true&w=majority',
)

app.use(morgan('dev'));
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());


//CORS error
app.use((req, res, next)=>{
    res.header('Access-Control-ALlow-Origin', '*');
    res.header('Access-Control-Allow-Headers', 
        'Origin, X-Requested-With, Content-Type, Accept, Authorization')
    if(req.method === 'OPTIONS'){
        res.header('Acces-Control-Allow-Methods', 'PUT, POST,PATCH, DELETE, GET');
        return res.status(200).json({});
    }
    next();
});

app.use('/user', userRoutes);
/*
app.use((req, res, next)=>{
    res.status(200).json({
        message: "It works!"
    });
});*/

app.use((req, rest, next)=>{
    const error = new Error('Not found');
    error.status = 404;
    next(error);
});

app.use((error, req, res, next)=>{
    res.status(error.status || 500);
    res.json({
        error:{
            message: error.message
        }
    })
})

module.exports = app;



