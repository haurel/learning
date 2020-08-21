const express = require('express');
const { request } = require('../../app');
const router = express.Router();

router.get('/', (req, res, next)=>{
    res.status(200).json({
        message: 'GET requests to /user'
    })
});

router.post('/', (req, res, next)=>{
    res.status(200).json({
        message: 'POST requests to /user'
    });
});

router.get('/:id', (req, res, next)=>{
    const id = req.params.id; //extract id 

    if(id === '0')
        res.status(200).json({
            message: 'Admin ID',
            id: id
        });
    else res.status(200).json({
        message: 'You passed an ID: ' + id
    });

});

router.patch('/:id', (req, res, next)=>{
    const id = req.params.id; //extract id 
    res.status(200).json({
        message: 'Updated user with id: ' + id
    });
})

router.delete('/:id', (req, res, next)=>{
    const id = req.params.id; //extract id 
    res.status(200).json({
        message: 'Deleted user with id: ' + id
    })
})

module.exports = router;