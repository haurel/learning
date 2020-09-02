<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    //return view('welcome');

    return view('welcome');
});


/* Route::get('/helloWorld', function(){
    return view('hello', ['users' => App\User::all()]);
}); */

Route::get('/users', 'UsersController@getAllUsers');
Route::get('/user/{id}', 'UsersController@getUserById');

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');
