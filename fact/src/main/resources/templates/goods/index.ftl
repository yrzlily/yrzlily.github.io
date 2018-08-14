<@override name="title">商品列表</@override>
<@override name="head">
<style>

</style>
</@override>
<@override name="app">
<div class="layui-fluid">
    <blockquote class="layui-elem-quote layui-quote-nm">后台系统 &gt; 商品列表</blockquote>
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
    <table id="goods" lay-filter="goods"></table>
</div>
</@override>
<@override name="script">
<#--价格-->
<script type="text/html" id="price">
    ￥{{d.price}}
</script>
<#--封面-->
<script type="text/html" id="images">
    <a class="checkImage" data-src='{{d.images}}' style="cursor: pointer;">点击查看</a>
</script>
<#--分类-->
<script type="text/html" id="cate">
    {{# if(d.type.typeName){ }}
    {{d.type.typeName}}
    {{# }else{ }}
    无属性
    {{# } }}
</script>
<#--状态-->
<script type="text/html" id="status">

    {{# if(d.status=="0"){ }}
    上架中
    {{# }else{ }}
    下架中
    {{# } }}
</script>
<#--工具栏-->
<script type="text/html" id="editBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="photo">相册</a>
    <a href="attr/{{d.cats}}/{{d.id}}" class="layui-btn layui-btn-xs layui-btn-primary" lay-event="photo">规格属性</a>
    <a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="specifications">规格匹配</a>
</script>
<script>
    layui.use('table', function(){
        var table = layui.table,
                $ = layui.jquery;

        //第一个实例
        var tableIns = table.render({
            elem: '#goods'
            ,height: 500
            ,url: '/goods/list'
            ,page: true
            ,limit: 10
            ,request: {
                limitName: 'size'
            }
            ,cols: [[
                {checkbox: true},
                {field: 'id', title: 'ID', width:80, sort: true },
                {field: 'name', title: '商品名称', width:200},
                {field: 'images', title: '封面', width:120   , templet: '#images'},
                {field: 'cate', title: '分类', width:180 , sort: true  , templet: '#cate'},
                {field: 'num', title: '库存', width:180  },
                {field: 'price', title: '价格', width:180 , templet: '#price'},
                {field: 'status', title: '状态', width:150 , templet: '#status'},
                {field: 'update_time', title: '更新时间', width:230  },
                {fixed: 'right', width:280, align:'center', toolbar: '#editBar'}
            ]]

        });

        //监听工具条
        table.on('tool(goods)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
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
                layer.open({
                    type: 2,
                    shadeClose:true,
                    maxmin:true,
                    area:['800px' , '600px'],
                    content: '/goods/edit/'+data.id,
                    end:function () {
                        table.reload('goods');
                    }
                });
            } else if(layEvent === 'specifications'){
                layer.open({
                    type: 2,
                    shadeClose:true,
                    maxmin:true,
                    area:['800px' , '600px'],
                    content: '/goods/specifications/'+data.cats+'/'+data.id,
                    end:function () {
                        table.reload('goods');
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
                area:['800px' , '500px'],
                end:function () {
                    table.reload('goods');
                }
            });
        }).on('click' , '#search' , function () {

            tableIns.reload({
                where: {
                    search: $('[name="search"]').val(),
                    cate: $('[name="cate"]').val()
                }
            });

        }).on('click' , '.checkImage' , function () {
                var src = $(this).data("src");
                console.log(src);
            layer.open({
                type: 1,
                offset:'0',
                area: '500px',
                shadeClose :true,
                content: '<img src="'+src+'" width="100%" />'
            });
        });

    });
</script>
</@override>
<@extends name="/common/base.ftl" />