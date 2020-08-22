const mongoose = require("mongoose");
const User = require("../models/user");

let chai = require('chai');
let chainHttp = require('chai-http');
let server = require('../../server');
const { expect } = require("chai");
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

});