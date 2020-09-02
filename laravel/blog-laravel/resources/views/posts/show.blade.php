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
@endsection