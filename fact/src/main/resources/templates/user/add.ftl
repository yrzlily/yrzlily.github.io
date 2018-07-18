<@override name="title">用户编辑</@override>
<@override name="head">

</@override>
<@override name="app">
<div class="layui-fluid">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名称</label>
            <div class="layui-input-block">
                <input type="text" name="username" required  lay-verify="required" placeholder="用户名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户头像</label>
            <div class="layui-input-block">
                <input type="text" name="avatar" required  lay-verify="required" placeholder="头像地址" autocomplete="off" class="layui-input" style="width: calc(100% - 61px); display: inline-block;">
                <button type="button" class="layui-btn" id="test1" >
                    <i class="layui-icon">&#xe67c;</i>
                </button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户电话</label>
            <div class="layui-input-block">
                <input type="text" name="phone" required  lay-verify="required" placeholder="用户电话" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">登录密码</label>
            <div class="layui-input-block">
                <input type="password" name="password"  placeholder="不修改则留空" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">用户权限</label>
            <div class="layui-input-block">
                <#list role as roles>
                    <input type="checkbox" class="roles" value="${roles.id}" title="${roles.name}" lay-skin="primary">
                </#list>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="editUser">添加</button>
            </div>
        </div>
    </form>
</div>
</@override>
<@override name="script">
    <script>

        //获取选中权限角色
        function getCK(id){
            var value = [];
            for(var i = 0; i < id.length; i++){
                if(id[i].checked)
                    value.push(id[i].value);
            }

            return value;
        }

        layui.use('form', function(){
            var form = layui.form,
                    layer = layui.layer,
                    $ = layui.jquery;



            form.on('submit(editUser)', function(data){

                var arr = getCK($('input[class="roles"]'));
                data.field.roles = arr.join();


                $.ajax({
                    url:"/user/edit",
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