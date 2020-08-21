const express = require('express');
const app = express();

const userRoutes = require('./api/routes/user');


app.use('/user', userRoutes);

/*
app.use((req, res, next)=>{
    res.status(200).json({
        message: "It works!"
    });
});*/

module.exports = app;



