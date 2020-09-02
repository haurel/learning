<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;

class UsersController extends Controller
{
    public function getAllUsers(){
        return view('users', ['users' => User::all()]);
    }

    public function getUserById($id){
        return view('user', ['user' => User::where(['id' => $id])->first()]);
            
    }
}
