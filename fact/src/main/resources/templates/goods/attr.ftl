<@override name="title"></@override>
<@override name="head">

</@override>
<@override name="app">
<div class="layui-fluid">
    <a href="JavaScript:history.go(-1)" class="layui-btn">返回上级</a>
    <form class="layui-form" id="form" lay-filter="attrBar">
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
                    <button type="button" data-gid="${gid}" data-tid="${typeAttr.id}" data-obj="good_${typeAttr_index}" style="float: right; height: 20px;" class="layui-btn layui-btn-xs addBar">添加</button>
                </th>
            </tr>
            </thead>
            <tbody class="good_${typeAttr_index}">
            <#list goodsAttrList as goodAttr>
                <#if typeAttr.id == goodAttr.goodsAttrTid >
                    <tr class="goodLength">
                        <td>
                            <input type="hidden" name="goodsAttrs[${x}].id" value="${goodAttr.id}" />
                            <input type="hidden" title="goodsAttrTid" value="${typeAttr.id}" name="goodsAttrs[${x}].goodsAttrTid">
                            <input type="hidden" title="goodsAttrGid" value="${gid}" name="goodsAttrs[${x}].goodsAttrGid">
                            <input type="text" required lay-verify="required" value="${goodAttr.goodsAttrName}" name="goodsAttrs[${x}].goodsAttrName" class="layui-input" title=""/>
                        </td>
                        <td><input type="text" required lay-verify="required|number" value="${goodAttr.goodsAttrPrice}" name="goodsAttrs[${x}].goodsAttrPrice" class="layui-input" title=""></td>
                        <td>
                            <button data-id="${goodAttr.id}" type="button" class="layui-btn layui-btn-danger delete">删除</button>
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
    <tr class="goodLength">
        <td>
            <input type="hidden" title="goodsAttrTid" class="goodsAttrTid" value="[tid]" name="goodsAttrs[[size]].goodsAttrTid">
            <input type="hidden" title="goodsAttrGid" class="goodsAttrGid" value="[gid]" name="goodsAttrs[[size]].goodsAttrGid">
            <input type="text" required lay-verify="required"  name="goodsAttrs[[size]].goodsAttrName" class="layui-input" title=""/>
        </td>
        <td><input type="text" required lay-verify="required|number" value="" name="goodsAttrs[[size]].goodsAttrPrice" class="layui-input" title=""></td>
        <td>
            <button data-id="" type="button" class="layui-btn layui-btn-danger delete">删除</button>
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

        $(document).on('click' , '.addBar' , function () {

            var gid = $(this).data('gid'),
                    tid = $(this).data('tid'),
                    obj = '.' + $(this).data('obj'),
                     box = $(obj),
                    mark = $(this).data('obj'),
                     html = $('#textBox').html();


            var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
            var source = html.replace(reg , function (node, key) {
                return {
                    'size': $(obj).parents('table').find('.goodLength').length,
                    'gid': gid ,
                    'tid': tid
                }[key];
            });
            box.append($(source));
            form.render();

        }).on('click' , '.delete'  ,function () {
            var id = $(this).data('id');

            if(id!=''){

                layer.confirm('是否删除该属性', {icon: 3, title:'删除'}, function(index){
                    //do something
                    $.ajax({
                        url:'/goods/attrDel/'+id,
                        type:'Post',
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
                    layer.close(index);
                });



            }else{
                $(this).parents("tr").remove();
            }

        });

    });



</script>
</@override>
<@extends name="/common/base.ftl" />