const mongoose = require("mongoose");
const faker = require("faker");
const User = require("../models/user");

let chai = require('chai');
let chainHttp = require('chai-http');
let server = require('../../server');
const { expect } = require("chai");
const { fake } = require("faker");
let should = chai.should();


chai.use(chainHttp);

chai.use(require('chai-like'));
chai.use(require('chai-things'));

describe('Users', () => {

	beforeEach((done) => { 
		User.deleteMany({}, (err) => { 
            done();
		});		
    });


    describe('## localhost:3000/user   ->   create new user', () =>{
        it('Check POST new user without parameters', (done)=>{
            chai.request(server)
                .post('/user')
                .send({})
                .end((err, res)=>{
                    should.not.exist(err);
                    res.should.have.status(500);
                    res.body.should.have.property("error").to.contain({
                        message: "User validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required."
                    });
                    done();
                });
        });


        it('it should POST new user', (done)=>{
            chai.request(server)
                .post('/user')
                .send({ firstName : 'test', lastName : 'test'})
                .then((res)=>{
                    const body = res.body;
                    expect(res.status).equal(200);
                    expect(body).to.contain.property('message');
                    expect(body).to.deep.property('createdUser').to.contain({ 
                        firstName : 'test', 
                        lastName : 'test',
                        fullName: 'test test'
                    });
                    done();
                })
                .catch((err)=> console.log(err));
        });
    });


    describe('## localhost:3000/user   ->   get all users', ()=>{
        it('it should GET all users', (done)=>{
            chai.request(server)
                .get('/user')
                .end((err, res)=>{
                    res.should.have.status(200);
                    res.body.should.be.a('object');
                    res.body.should.have.property('countUser');
                    res.body.should.have.property('users');
                    res.body.should.have.property('countUser').eq(0);
                    done();
                });
        });
    });

    describe('## localhost:3000/user/:id  ->   update user', ()=>{
        it('it should update user with specific id', (done)=>{
            const user = new User({
                _id: new mongoose.Types.ObjectId(),
                firstName : faker.name.firstName(),
                lastName : faker.name.lastName()
            });

            user.save()
                .then(result =>{
                    chai.request(server)
                        .patch('/user/' + result._id)
                        .send([{ firstName: faker.name.firstName, lastName: faker.name.lastName }])
                        .then((res) =>{
                            res.should.have.status(200);
                        })
                        .catch((err)=> console.log(err));
                        done();
                });
        });

        it('it should update user with specific id just firstName', (done)=>{
            const user = new User({
                _id: new mongoose.Types.ObjectId(),
                firstName : faker.name.firstName(),
                lastName : faker.name.lastName()
            });

            user.save()
                .then(result =>{
                    chai.request(server)
                        .patch('/user/' + result._id)
                        .send([{ firstName: faker.name.firstName }])
                        .then((res) =>{
                            res.should.have.status(200);
                        })
                        .catch((err)=> console.log(err));
                        done();
                });
        });

        it('it should update user with specific id just lastName', (done)=>{
            const user = new User({
                _id: new mongoose.Types.ObjectId(),
                firstName : faker.name.firstName(),
                lastName : faker.name.lastName()
            });

            user.save()
                .then(result =>{
                    chai.request(server)
                        .patch('/user/' + result._id)
                        .send([{ lastName: faker.name.lastName }])
                        .then((res) =>{
                            res.should.have.status(200);
                        })
                        .catch((err)=> console.log(err));
                        done();
                });
        });
    });


    describe('## localhost:3000/user/:id   ->   delete user with specific id', ()=>{
        it('it should DELETE user with specific id', (done)=>{
            const user = new User({
                _id: new mongoose.Types.ObjectId(),
                firstName : faker.name.firstName(),
                lastName : faker.name.lastName()
            });

            user.save()
                .then(result =>{
                    chai.request(server)
                        .delete('/user/' + result._id)
                        .end((err, res) => {
                            res.should.have.status(200);
                            done();
                        });
                        
                });
        });
    });

});
