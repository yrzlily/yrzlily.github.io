<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/layui/css/layui.css">

    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" ></script>
    <script src="/static/layui/layui.js"></script>

    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

    <title><@block name="title"></@block></title>
    <@block name="head">

    </@block>
    <style>
        .layui-quote-nm{color: #666; background: #fbfbfb;}
        .layui-fluid{margin-top: 10px;}
        #app{height: 100%; overflow: auto;}
    </style>
</head>
<body>
<@block name="body">

</@block>
<div id="app">
    <@block name="app">

    </@block>
</div>
<@block name="script">

</@block>
</body>
</html>