const express = require('express');
const mongoose = require('mongoose');


const router = express.Router();


const User = require('../models/user');

router.get('/', (req, res, next)=>{
    User.find()
        .select("firstName lastName _id")
        .exec()
        .then(docs => {
            const personalResponse = {
                countUser: docs.length,
                users: docs.map(doc =>{
                    return{
                        firstName: doc.firstName,
                        lastName: doc.lastName,
                        fullName: doc.firstName + " " + doc.lastName,
                        _id: doc._id,
                        request:{
                            type: 'GET',
                            url: 'http://localhost:3000/user/' + doc._id,
                        }
                    }
                })
            }

            
            //console.log(docs);
            if(docs.length >= 0){
                res.status(200).json(personalResponse);
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
});

router.post('/', (req, res, next)=>{
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
                message: 'Created user successefully',
                createdUser:{
                    firstName: result.firstName,
                    lastName: result.lastName,
                    fullName: result.firstName + " " + result.lastName,
                    _id: result._id,
                    request: {
                        type: 'GET',
                        url: 'http://localhost:3000/user/' + result._id,
                    }
                }
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
    const id = req.params.id; 
    User.findById(id)
        .exec()
        .then(doc =>{      
            const personalResponse = {
                firstName: doc.firstName,
                lastName: doc.lastName,
                fullName: doc.firstName + " " + doc.lastName,
                _id: doc._id,
                request: {
                    type: 'GET',
                    description: 'Get all users',
                    url: 'http://localhost:3000/user',
                }
            }
                
                
            if(doc){
                res.status(200).json(personalResponse);
            }else{
                res.status(404).json({
                    message: "No valid entry found for provided ID"
                })
            }
        })
        .catch(err =>{
            res.status(500).json({error: err});
        })
    /*User.findOne({ firstName : id})
        .exec()
        .then(doc =>{      
            const personalResponse = {
                firstName: doc.firstName,
                lastName: doc.lastName,
                fullName: doc.firstName + " " + doc.lastName,
                _id: doc._id,
                request: {
                    type: 'GET',
                    description: 'Get all users',
                    url: 'http://localhost:3000/user',
                }
            }
                
                
            if(doc){
                res.status(200).json(personalResponse);
            }else{
                res.status(404).json({
                    message: "No valid entry found for provided ID"
                })
            }
        })
        .catch(err =>{
            console.log(err);
            res.status(500).json({error: err});
        })*/
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
            res.status(200).json({
                message: "User updated",
                request: {
                    type: 'GET',
                    url: 'http://localhost:3000/user/' + id,
                }
            });            
        })
        .catch(err =>{
            console.log(err);
            res.status(500).json({
                error: err
            })
        });

})

router.delete('/:id', (req, res, next)=>{
    const id = req.params.id;
    User.remove({ _id: id })
        .exec()
        .then(result =>{
            res.status(200).json({
                message: "User deleted",
                request:{
                    type: 'POST',
                    url: 'http://localhost:3000/user',
                    body: { firstName: 'String', lastName: 'String'}
                }
            })
        })
        .catch(err =>{
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
})

module.exports = router;