<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <title><@block name="title"></@block></title>
    <@block name="head">

    </@block>
    <style>
        #app{height: 100%;}
    </style>
</head>
<body>
<div id="app">
    <@block name="app">

    </@block>
</div>
<@block name="script">

</@block>
</body>
</html>