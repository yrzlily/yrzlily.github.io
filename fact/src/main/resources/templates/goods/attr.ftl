<@override name="title"></@override>
<@override name="head">

</@override>
<@override name="app">
<div class="layui-fluid">
    <a href="/goods/index" class="layui-btn">返回上级</a>
    <form class="layui-form" id="form">
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col width="200">
            </colgroup>
            <#assign x = 0 >
            <#list typeAttrList as typeAttr>
            <thead>
            <tr>
                <th>${typeAttr.typeAttributesName}</th>
                <th>价格</th>
                <th>
                    <label style="float: left;">操作</label>
                    <button type="button" onclick="cloneBar(${gid} , ${typeAttr.id} , 'good_${typeAttr_index}')" style="float: right; height: 20px;" class="layui-btn layui-btn-xs">添加</button>
                </th>
            </tr>
            </thead>
            <tbody>
            <#list goodsAttrList as goodAttr>
                <#if typeAttr.id == goodAttr.goodsAttrTid >
                    <tr class="good_${typeAttr_index}">
                        <td>
                            <input type="hidden" name="goodsAttrs[${x}].id" value="${goodAttr.id}" />
                            <input type="hidden" title="goodsAttrTid" value="${typeAttr.id}" name="goodsAttrs[${x}].goodsAttrTid">
                            <input type="hidden" title="goodsAttrGid" value="${gid}" name="goodsAttrs[${x}].goodsAttrGid">
                            <input type="text" required lay-verify="required" value="${goodAttr.goodsAttrName}" name="goodsAttrs[${x}].goodsAttrName" class="layui-input" title=""/>
                        </td>
                        <td><input type="text" required lay-verify="required|number" value="${goodAttr.goodsAttrPrice}" name="goodsAttrs[${x}].goodsAttrPrice" class="layui-input" title=""></td>
                        <td>
                            <button data-id="${goodAttr.id}" class="layui-btn layui-btn-danger">删除</button>
                        </td>
                    </tr>
                    <#assign x = x +1 />
                </#if>
            </#list>
            </tbody>

            </#list>
        </table>

        <button lay-submit type="submit" id="save" class="layui-btn" lay-filter="save">更新</button>
    </form>
</div>
</@override>
<@override name="script">
<script type="text/html" id="textBox">
    <tr class="[class]">
        <td>
            <input type="hidden" title="goodsAttrTid" class="goodsAttrTid" value="[tid]" name="goodsAttrs[[size]].goodsAttrTid">
            <input type="hidden" title="goodsAttrGid" class="goodsAttrGid" value="[gid]" name="goodsAttrs[[size]].goodsAttrGid">
            <input type="text" required lay-verify="required"  name="goodsAttrs[[size]].goodsAttrName" class="layui-input" title=""/>
        </td>
        <td><input type="text" required lay-verify="required|number" value="" name="goodsAttrs[[size]].goodsAttrPrice" class="layui-input" title=""></td>
        <td>
            <button data-id="" class="layui-btn layui-btn-danger">删除</button>
        </td>
    </tr>
</script>
<script>
    layui.use(['form'], function(){
        var form = layui.form,
            $ = layui.jquery;

        form.on('submit(save)', function(data){
            $.ajax({
                url:"/goods/attrSave",
                dataType : 'json',
                data: data.field,
                type:"POST",
                success:function (data) {
                    console.log(data);
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
            return false;
        });
    });

    function cloneBar(gid , tid , obj) {
        var dom = "."+obj;
        var box = $(dom).parent();
        var html = $('#textBox').html();

        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
        var source = html.replace(reg , function (node, key) {
            return {
                'size': $(dom).length,
                'class': obj,
                'gid': gid ,
                'tid': tid
            }[key];
        });
        box.append($(source));
    }
</script>
</@override>
<@extends name="/common/base.ftl" />