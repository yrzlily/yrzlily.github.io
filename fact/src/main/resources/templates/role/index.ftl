<@override name="title">用户权限</@override>
<@override name="head">

</@override>
<@override name="app">
<div class="layui-fluid">
    <blockquote class="layui-elem-quote layui-quote-nm">后台系统 &gt; 用户权限</blockquote>
    <table id="role" lay-filter="role"></table>
</div>
</@override>

<@override name="script">
<#--工具栏-->
<script type="text/html" id="toolBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    {{# if( d.role != 'admin'){ }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{# } }}
</script>
<script>
    layui.use('table', function(){
        var table = layui.table,
            $ = layui.jquery;

        //第一个实例
        table.render({
            elem: '#role'
            ,height: 500
            ,url: '/role/list'
            ,page: true
            ,limit: 15
            ,method:'post'
            ,request: {
                limitName: 'size'
            }
            ,cols: [[
                {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'},
                {field: 'name', title: '权限名称', width:150},
                {field: 'role', title: '权限标识', width:120},
                {fixed: 'right', width:150, align:'center', toolbar: '#toolBar'}
            ]]

        });

        //监听工具条
        table.on('tool(role)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令

                    $.ajax({

                    });

                });
            } else if(layEvent === 'edit'){ //编辑
                //do something


            }
        });
    });
</script>
</@override>
<@extends name="/common/base.ftl" />