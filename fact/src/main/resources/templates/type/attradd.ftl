<@override name="title"></@override>
<@override name="head">

</@override>
<@override name="app">
<div  class="layui-fluid">
    <form class="layui-form" action="">
        <input type="hidden" name="tid" value="${tid}" />
        <div class="layui-form-item">
            <label class="layui-form-label">属性名称</label>
            <div class="layui-input-block">
                <input type="text" name="typeAttributesName" required  lay-verify="required" placeholder="属性名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">属性类型</label>
            <div class="layui-input-block">
                <select name="typeAttributesType" lay-verify="required" title="属性">
                    <option value=""></option>
                    <option value="1">文字</option>
                    <option value="2">图片</option>
                    <option value="3">图文</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序</label>
            <div class="layui-input-block">
                <input type="text" name="sort" required  lay-verify="required|number" placeholder="排序" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="typeAttrAdd">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</@override>
<@override name="script">
<script>
    layui.use('form', function(){
        var form = layui.form,
            $ = layui.jquery;


        form.on('submit(typeAttrAdd)', function(data){

            $.ajax({
                url:"attrAdd",
                data: data.field,
                dataType : 'json',
                type:"POST",
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
            return false;
        });
    });
</script>
</@override>
<@extends name="/common/base.ftl" />