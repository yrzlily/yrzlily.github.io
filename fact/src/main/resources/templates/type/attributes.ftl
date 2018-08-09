<@override name="title">规格属性</@override>
<@override name="head">

</@override>
<@override name="app">
<div class="layui-fluid">
    <div class="layui-inline">
        <button id="add" class="layui-btn">添加</button>
    </div>
    <table id="typeAttributes" lay-filter="typeAttributes"></table>
</div>
</@override>
<@override name="script">
<#--属性类型-->
<script type="text/html" id="type">
    {{# if(d.typeAttributesType == "1"){ }}
    文字
    {{# } }}
    {{# if(d.typeAttributesType == "2"){ }}
    图片
    {{# } }}
    {{# if(d.typeAttributesType == "3"){ }}
    图文
    {{# } }}
</script>
<#--工具栏-->
<script type="text/html" id="editBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use('table', function() {
        var table = layui.table,
                $ = layui.jquery;

        var tableIns = table.render({
            elem: '#typeAttributes'
            ,height: 500
            ,url: '/type/attributesList/'+${tid}
            ,page: true
            ,limit: 10
            ,request: {
                limitName: 'size'
            }
            ,cols: [[
                {field: 'id', title: 'ID', width:80, sort: true },
                {field: 'typeAttributesName', title: '属性名称', width:180 },
                {field: 'typeAttributesType', title: '属性类型', width:180 , templet:"#type" },
                {field: 'sort', title: '排序', width:180 },
                {fixed: 'right', width:190, align:'center', toolbar: '#editBar'}
            ]]
        });

        //监听工具条
        table.on('tool(typeAttributes)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
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
                        url:'/type/attrDel/'+data.id,
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
                    content: '/type/attrEdit/'+data.id,
                    end:function () {
                        table.reload('typeAttributes');
                    }
                });
            }
        });

        $(document).on('click' , '#add' , function () {
            layer.open({
                type: 2,
                title:'添加属性',
                shadeClose:true,
                maxmin:true,
                area:['600px' , '300px'],
                content: '/type/attrAdd?tid='+${tid},
                end:function () {
                    table.reload('typeAttributes');
                }
            });
        })

    });
</script>
</@override>
<@extends name="/common/base.ftl" />