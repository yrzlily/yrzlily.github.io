<@override name="title">用户权限</@override>
<@override name="head">

</@override>
<@override name="app">
<div class="layui-fluid">
    <blockquote class="layui-elem-quote layui-quote-nm">后台系统 &gt; 用户权限</blockquote>
    <div class="layui-inline">
        <button id="add" class="layui-btn">添加</button>
    </div>
    <table id="role" lay-filter="role"></table>
</div>
</@override>

<@override name="script">
<#--工具栏-->
<script type="text/html" id="toolBar">
    {{# if( d.roles != 'admin'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
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
                {field: 'roles', title: '权限标识', width:120},
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
                        url:'delete/'+data.id,
                        type:'get',
                        success:function (data) {
                            if(data.code === 0){
                                layer.msg(data.msg , {
                                    icon:1,
                                    end:function (data) {
                                        location.reload();
                                    }
                                });
                            }else{
                                layer.msg(data.msg, {
                                    icon:2
                                });
                            }
                        }
                    });

                });
            } else if(layEvent === 'edit'){ //编辑

                layer.open({
                    type: 2,
                    shadeClose:true,
                    content: '/role/edit/'+data.id,
                    area:['600px' , '400px'],
                    end:function () {
                        table.reload('role');
                    }
                });
            }
        });

        //额外事件
        $(document).on('click' , '#add' , function () {
            layer.open({
                type: 2,
                shadeClose:true,
                title:'添加',
                content: '/role/add/',
                area:['600px' , '400px'],
                end:function () {
                    table.reload('role');
                }
            });
        });

    });
</script>
</@override>
<@extends name="/common/base.ftl" />