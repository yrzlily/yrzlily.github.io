<@override name="title">用户列表</@override>
<@override name="head">
<style>

</style>
</@override>
<@override name="app">
<div class="layui-fluid">
    <blockquote class="layui-elem-quote layui-quote-nm">后台系统 &gt; 用户列表</blockquote>
    <table id="user" lay-filter="user"></table>
</div>
</@override>
<@override name="script">
<script type="text/html" id="editBar">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#user'
            ,height: 500
            ,url: '/user/list'
            ,page: true
            ,limit: 15
            ,request: {
                limitName: 'size'
            }
            ,cols: [[
                {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'username', title: '用户名', width:150}
                ,{field: 'phone', title: '电话', width:150}
                ,{fixed: 'right', width:160, align:'center', toolbar: '#editBar'}
            ]]

        });

    });
</script>
</@override>
<@extends name="/common/base.ftl" />