<@override name="title">用户编辑</@override>
<@override name="head">

</@override>
<@override name="app">
<div class="layui-fluid">
    <form class="layui-form" action="">
        <input type="hidden" value="${user.id}" name="id">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名称</label>
            <div class="layui-input-block">
                <input type="text" name="username" value="${user.username}" required  lay-verify="required" placeholder="用户名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户头像</label>
            <div class="layui-input-block">
                <input type="text" readonly="readonly" value="${user.avatar}" name="avatar" required  lay-verify="required" placeholder="头像地址" autocomplete="off" class="layui-input" style="width: calc(100% - 61px); display: inline-block;">
                <button type="button" class="layui-btn" id="avatar" >
                    <i class="layui-icon">&#xe67c;</i>
                </button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户电话</label>
            <div class="layui-input-block">
                <input type="text" name="phone" value="${user.phone}" required  lay-verify="required" placeholder="用户电话" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">登录密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" value=""  placeholder="不修改则留空" autocomplete="off" class="layui-input">
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
                <button class="layui-btn" lay-submit lay-filter="editUser">修改</button>
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

        <#list user.roles as users>
                $('input[class="roles"][value ="${users.id}"]').attr('checked' , true);
        </#list>

        layui.use(['form' , 'upload'], function(){
            var form = layui.form,
                    layer = layui.layer,
                    upload = layui.upload,
                    $ = layui.jquery;

            //执行实例
            var uploadInst = upload.render({
                elem: '#avatar' //绑定元素
                ,url: '/file/upload' //上传接口
                ,done: function(res){
                    //上传完毕回调
                    $('input[name="avatar"]').val(res.data);
                }
                ,error: function(){
                    //请求异常回调
                }
            });

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