<@override name="title">规格匹配</@override>
<@override name="head">
<style>

</style>
</@override>
<@override name="app">
<div class="layui-fluid">
    <form class="layui-form" id="specs" action="">
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col width="200">
                <col>
            </colgroup>
            <thead>
            <tr>
                <#list typeAttrList as typeAttr>
                <th>${typeAttr.typeAttributesName}</th>
                </#list>
                <th>库存</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <input type="hidden" name="gid" value="${gid}" />
                <#list typeAttrList as typeAttr>
                    <td>
                        <select name="list[${typeAttr_index}]" lay-verify="" title="选择属性">
                            <option value="">选择${typeAttr.typeAttributesName}</option>
                            <#list goodsAttrList as goodAttr>
                                <#if typeAttr.id == goodAttr.goodsAttrTid >
                                    <option value="${goodAttr.id}">${goodAttr.goodsAttrName}</option>
                                </#if>
                            </#list>
                        </select>
                    </td>
                </#list>
                <td>
                    <input type="number" name="num" required  lay-verify="required|number" placeholder="库存" class="layui-input">
                </td>
                <td>
                    <button lay-submit type="submit" class="layui-btn" lay-filter="specAdd">添加</button>
                </td>
            </tr>
            <#list goodsSpec as spec>
            <tr class="edits">
                <input type="hidden" name="id" value="${spec.id}" title="" />
                <#list spec.goodsAttrs as attr>
                    <td>
                        ${attr.goodsAttrName}
                    </td>
                </#list>
                <td>
                    <input type="number" name="num" value="${spec.num}" required  lay-verify="required|number" placeholder="库存" class="layui-input">
                </td>
                <td>
                    <button class="layui-btn layui-btn-warm edit-btn" data-id="${spec.id}" type="button">编辑</button>
                    <button class="layui-btn layui-btn-danger del-btn" data-id="${spec.id}" type="button">删除</button>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </form>
</div>
</@override>
<@override name="script">
<script>
    layui.use('form', function(){
        var form = layui.form,
            $ = layui.jquery;

        //监听提交
        form.on('submit(specAdd)', function(data){

            $.ajax({
                url:"/goods/addSpec",
                dataType: 'json',
                data: data.field,
                type:"POST",
                success:function (data) {
                    if(data.code === 0){
                        location.reload();
                    }else{
                        layer.msg(data.msg, {
                            icon:2
                        });
                    }
                }
            });
            return false;

        });
        
        $(document).on('click' , '.edit-btn' , function () {
            var num = $(this).parents('.edits').find('input[name="num"]').val(),
                    id = $(this).data('id');

            $.ajax({
                url:'/goods/editSpec',
                data:{
                    id:id,
                    num:num
                },
                type:'post',
                success:function (data) {
                    if(data.code === 0){
                        location.reload();
                    }else{
                        layer.msg(data.msg, {
                            icon:2
                        });
                    }
                }
            });

        }).on('click' , '.del-btn' , function () {
                var id = $(this).data('id');
                $.ajax({
                    url:'/goods/delSpec/'+id,
                    type:'post',
                    success:function (data) {
                        if(data.code === 0){
                            location.reload();
                        }else{
                            layer.msg(data.msg, {
                                icon:2
                            });
                        }
                    }
                });
        });

    });
</script>
</@override>
<@extends name="/common/base.ftl" />