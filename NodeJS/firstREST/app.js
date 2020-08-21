const express = require('express');
const morgan = require('morgan');

const app = express();

const userRoutes = require('./api/routes/user');


app.use(morgan('dev'));

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



