<@override name="title">商品分类管理</@override>
<@override name="head">

</@override>
<@override name="app">
<div class="layui-fluid">
    <blockquote class="layui-elem-quote layui-quote-nm">
        后台系统 &gt; 商品列表
        &gt; <a href="?pid=0">顶部菜单</a>
        <#if !type??>

        <#else >
            <#if type.types??>
                &gt; &gt; <a href="?pid=${type.types.id}">${type.types.typeName}</a>
            </#if>

            &gt; <a href="?pid=${type.id}">${type.typeName}</a>
        </#if>
    </blockquote>
    <div class="layui-inline">
        <#if !type??>

        <#else >
            <#if type.types??>
                <a href="?pid=${type.types.id}" class="layui-btn">返回上一级：${type.types.typeName}</a>
                <#else>
                <a href="?pid=0" class="layui-btn">顶部菜单</a>
            </#if>
        </#if>
    </div>
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
    <div class="layui-inline">
        <button id="delete" class="layui-btn layui-btn-danger">删除</button>
    </div>
    <table id="type" lay-filter="type"></table>
</div>
</@override>
<@override name="script">
<#--工具栏-->
<script type="text/html" id="editBar">
    <a class="layui-btn layui-btn-xs" href="?pid={{d.id}}" lay-event="child">下级</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
</script>
<script>
    layui.use('table', function() {
        var table = layui.table,
                $ = layui.jquery;

        //第一个实例
        var tableIns = table.render({
            elem: '#type'
            ,height: type
            ,url: '/type/list?pid='+${pid}
            ,page: true
            ,limit: 10
            ,request: {
                limitName: 'size'
            }
            ,cols: [[
                {checkbox: true},
                {field: 'id', title: 'ID', width:80, sort: true },
                {field: 'typeName', title: '分类名称', width:200},
                {field: 'sort', title: '排序' , sort: true , width:80},
                {field: 'update_time', title: '修改时间' , sort: true , width:220},
                {fixed: 'right', width:160, align:'center', toolbar: '#editBar'}
            ]]

        });

        //监听工具条
        table.on('tool(type)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
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
                        url:'del/'+data.id,
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
                    maxmin:true,
                    area:['600px' , '300px'],
                    content: '/type/edit/'+data.id,
                    end:function () {
                        table.reload('type');
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
                content: 'add?pid=${pid}',
                maxmin:true,
                area:['600px' , '300px'],
                end:function () {
                    table.reload('type');
                }
            });
        }).on('click' , '#search' , function () {

            tableIns.reload({
                where: {
                    search: $('[name="search"]').val(),
                }
            });

        }).on('click' , '#delete' , function () {
            var checkStatus = table.checkStatus('type').data;

            for (var i =0 ; i<checkStatus.length; i++){
                $.ajax({
                    url:'del/'+checkStatus[i].id,
                    type:'post',
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
            }
        });

    });
</script>
</@override>
<@extends name="/common/base.ftl" />