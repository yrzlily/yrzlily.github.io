<@override name="title">添加分类</@override>
<@override name="head">

</@override>
<@override name="app">
<div class="layui-fluid">
    <form class="layui-form" action="">
        <input type="hidden" name="parentID" value="${pid}" />
        <div class="layui-form-item">
            <label class="layui-form-label">分类名称</label>
            <div class="layui-input-block">
                <input type="text" name="typeName" required  lay-verify="required" placeholder="分类名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">分类图标</label>
            <div class="layui-input-block">
                <input type="text" readonly="readonly"  name="images" required  lay-verify="required" placeholder="分类图标" autocomplete="off" class="layui-input" style="width: calc(100% - 61px); display: inline-block;">
                <button type="button" class="layui-btn" id="images" >
                    <i class="layui-icon">&#xe67c;</i>
                </button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序</label>
            <div class="layui-input-block">
                <input type="number" name="sort" required  lay-verify="required" placeholder="排序" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="addType">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</@override>
<@override name="script">
<script>
    layui.use(['form' , 'upload' , 'layedit'], function() {
        var form = layui.form,
                layer = layui.layer,
                upload = layui.upload,
                layedit = layui.layedit,
                $ = layui.jquery;

        //执行实例
        var uploadInst = upload.render({
            elem: '#images' //绑定元素
            ,url: '/file/upload' //上传接口
            ,done: function(res){
                //上传完毕回调
                $('input[name="images"]').val(res.data);
            }
            ,error: function(){
                //请求异常回调
            }
        });

        //监听提交
        form.on('submit(addType)', function(data){

            $.ajax({
                url:"add",
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