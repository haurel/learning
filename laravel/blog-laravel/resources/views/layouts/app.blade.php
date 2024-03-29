<!DOCTYPE html>
<html lang="{{ config('app.locale') }}">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device=width, initial-scale=1">
        <link href="{{ asset('css/app.css') }}" rel="stylesheet">
        <title> {{ config('app.name', 'BLOG') }}</title>
    </head>

    <body>
        @include('inc.navbar')
        <main role="main" class="container"> 
            @include('inc.messages')
            @yield('content')
        </main>
        
        <script src="{{ asset('ckeditor/ckeditor.js') }}"></script>
        <script> CKEDITOR.replace('article-ckeditor'); </script>
    </body>
</html>