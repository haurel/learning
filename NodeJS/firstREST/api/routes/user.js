const express = require('express');
const mongoose = require('mongoose');


const router = express.Router();


const User = require('../models/user');

router.get('/', (req, res, next)=>{
    User.find()
        .exec()
        .then(docs =>{
            console.log(docs);
            if(docs.length >= 0){
                res.status(200).json(docs);
            }else{
                res.status(404).json({
                    message: "No entries found"
                });
            }
        })
        .catch(err =>{
            console.log(err);
            res.status(500).json(err)
        });
    /*
    res.status(200).json({
        message: 'GET requests to /user'
    })*/
});

router.post('/', (req, res, next)=>{
    /*const user = {
        firstName: req.body.firstName,
        lastName: req.body.lastName
    };*/

    const user = new User({
        _id: new mongoose.Types.ObjectId(),
        firstName: req.body.firstName,
        lastName: req.body.lastName
    });

    //Method save by mongoose to save in db
    user.save()
        .then(result =>{
            console.log(result);
            res.status(200).json({
                message: 'POST requests to /user',
                createdUser: result
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            })
        });
});

router.get('/:id', (req, res, next)=>{
    const id = req.params.id; //extract id 

    /*if(id === '0')
        res.status(200).json({
            message: 'Admin ID',
            id: id
        });
    else res.status(200).json({
        message: 'You passed an ID: ' + id
    });*/

    User.findById(id)
        .exec()
        .then(doc =>{
            console.log(doc);
            if(doc){ //doc !== null
                res.status(200).json(doc);
            }else{
                res.status(404).json({
                    message: "No valid entry found for provided ID"
                })
            }
            
        })
        .catch(err =>{
            console.log(err);
            res.status(500).json({error: err});
        })

});

router.patch('/:id', (req, res, next)=>{
    const id = req.params.id;
    const updateOperations = {};

    for(const ops of req.body){
        updateOperations[ops.propName] = ops.value;
    }

    User.update({ _id: id}, { $set: updateOperations})
        .exec()
        .then(result =>{
            console.log(result);
            res.status(200).json(result);            
        })
        .catch(err =>{
            console.log(err);
            res.status(500).json({
                error: err
            })
        });


    //User.update({ _id: id}, { $set: { firstName: req.body.newFirstName, lastName: req.body.newLastName }});

    /*const id = req.params.id; //extract id 
    res.status(200).json({
        message: 'Updated user with id: ' + id
    });*/
})

router.delete('/:id', (req, res, next)=>{
    const id = req.params.id;
    User.remove({ _id: id })
        .exec()
        .then(result =>{
            res.status(200).json(result)
        })
        .catch(err =>{
            console.log(err);
            res.status(500).json({
                error: err
            });
        });

    /*
    const id = req.params.id; //extract id 
    res.status(200).json({
        message: 'Deleted user with id: ' + id
    })*/
})

module.exports = router;