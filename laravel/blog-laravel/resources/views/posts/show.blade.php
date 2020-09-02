@extends('layouts.app')

@section('content')
    <a href="/posts" class="btn btn-default">Go Back </a>
    <h1> {{ $post->title }}</h1>
    <div>
        <!--
            Folosim !! pt CKEDITOR doarece nu formateaza html 
            <p><strong> { body } </p></strong> -> afisajul normal fara !! ci cu {}

        -->
        {!! $post->body !!}
    </div>
    <hr>
    <small>Writte on {{ $post->created_at }}</small>
    <hr>
    <a href="/posts/{{ $post->id }}/edit" class="btn btn-secondary">Edit</a>

    {!! Form::open(['action' => ['PostsController@destroy', $post->id], 'method' => 'POST', 'class' => 'float-sm-right']) !!}
        {{ Form::hidden('_method', 'DELETE') }}
        {{ Form::submit('Delete', ['class' => 'btn btn-danger'])}}
    {!! Form::close() !!}
@endsection