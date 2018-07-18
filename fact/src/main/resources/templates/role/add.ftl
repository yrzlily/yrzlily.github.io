<@override name="title">编辑权限</@override>
<@override name="head">

</@override>
<@override name="app">
<div class="layui-fluid">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">权限名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" value="" required  lay-verify="required" placeholder="权限名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权限标识</label>
            <div class="layui-input-block">
                <input type="text" name="roles" value="" required  lay-verify="required" placeholder="权限标识" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开启权限</label>
            <div class="layui-input-block">
                <input type="radio" name="available"  value="1" title="是" checked>
                <input type="radio" name="available"  value="0" title="否" >
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">添加</button>
            </div>
        </div>
    </form>
</div>
</@override>
<@override name="script">
<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form,
                layer = layui.layer,
                index = parent.layer.getFrameIndex(window.name),
                $ = layui.jquery;

        //监听提交
        form.on('submit(formDemo)', function(data){
            console.log(data.field);
            $.ajax({
                url:"/role/edit",
                data: data.field,
                dataType : 'json',
                type:"POST",
                success:function (data) {
                    if(data.code === 0){
                        layer.msg(data.msg , {
                            icon:1,
                            end:function (data) {
                                parent.layer.close(index);
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