<@override name="title">分类列表</@override>
<@override name="head">

</@override>
<@override name="app">
<div class="layui-fluid">
    <blockquote class="layui-elem-quote layui-quote-nm">后台系统 &gt; 分类列表</blockquote>
    <div class="layui-inline">
        <button id="add" class="layui-btn">添加</button>
    </div>
    <form class="layui-form layui-inline">
        <div class="layui-inline">
            <input type="text" name="name"  placeholder="请输入名称" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-inline">
            <button type="button" id="search" class="layui-btn">搜索</button>
        </div>
    </form>
    <table id="cate" lay-filter="cate"></table>
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
        var tableIns = table.render({
            elem: '#cate'
            ,height: 500
            ,url: '/cate/list'
            ,page: true
            ,limit: 15
            ,method:'post'
            ,request: {
                limitName: 'size'
            }
            ,cols: [[
                {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'},
                {field: 'name', title: '分类名称', width:150},
                {field: 'sort', title: '分类排序',sort: true, width:120},
                {fixed: 'right', width:150, align:'center', toolbar: '#toolBar'}
            ]]

        });

        //监听工具条
        table.on('tool(cate)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
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
                    content: '/cate/edit/'+data.id,
                    area:['600px' , '400px'],
                    end:function () {
                        table.reload('cate');
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
                content: '/cate/add/',
                area:['600px' , '400px'],
                end:function () {
                    table.reload('cate');
                }
            });
        }).on('click' , '#search' , function () {

            tableIns.reload({
                where: {
                    name: $('[name="name"]').val()
                }
            });

        });

    });
</script>
</@override>
<@extends name="/common/base.ftl" />