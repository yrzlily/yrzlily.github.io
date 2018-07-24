<@override name="title">文章列表</@override>
<@override name="head">
<style>

</style>
</@override>
<@override name="app">
<div class="layui-fluid">
    <blockquote class="layui-elem-quote layui-quote-nm">后台系统 &gt; 文章列表</blockquote>
    <div class="layui-inline">
        <button id="add" class="layui-btn">添加</button>
    </div>
    <form class="layui-form layui-inline">
        <div class="layui-inline">
            <input type="text" name="search"  placeholder="请输入名称" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-inline">
            <button type="button" id="search" class="layui-btn">搜索</button>
        </div>
    </form>
    <table id="article" lay-filter="article"></table>
</div>
</@override>
<@override name="script">
<script type="text/html" id="editBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        var tableIns = table.render({
            elem: '#article'
            ,height: 500
            ,url: '/article/list'
            ,page: true
            ,limit: 15
            ,request: {
                limitName: 'size'
            }
            ,cols: [[
                {field: '_id', title: 'ID', width:220, sort: true, fixed: 'left'}
                ,{field: 'title', title: '文章标题', width:150}
                ,{field: 'by', title: '作者', width:150}
                ,{field: 'timestamp', title: '更新时间', width:200}
                ,{fixed: 'right', width:160, align:'center', toolbar: '#editBar'}
            ]]

        });

        //监听工具条
        table.on('tool(article)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if(layEvent === 'detail'){ //查看
                //do somehing
            } else if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        url:'delete/'+data._id,
                        type:'get',
                        success:function (data) {
                            console.log(data);
                        }
                    });
                });
            } else if(layEvent === 'edit'){ //编辑
                //do something
                layer.open({
                    type: 2,
                    shadeClose:true,
                    content: '/article/edit/'+data.id,
                    area:['600px' , '400px'],
                    end:function () {
                        table.reload('user');
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
                content: 'add',
                maxmin:true,
                area:['800px' , '600px'],
                end:function () {
                    table.reload('article');
                }
            });
        }).on('click' , '#search' , function () {

            tableIns.reload({
                where: {
                    username: $('[name="search"]').val()
                }
            });

        });

    });
</script>
</@override>
<@extends name="/common/base.ftl" />