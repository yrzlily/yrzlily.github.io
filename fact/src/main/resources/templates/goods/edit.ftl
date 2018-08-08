<@override name="title">添加商品</@override>
<@override name="head">
<style>

</style>
</@override>
<@override name="app">
<div class="layui-fluid">
    <form class="layui-form" action="">
        <input name="id" type="hidden" value="${goods.id}">
        <div class="layui-form-item">
            <label class="layui-form-label">标题</label>
            <div class="layui-input-block">
                <input type="text" name="name" value="${goods.name}" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">封面图片</label>
            <div class="layui-input-block">
                <input type="text" readonly="readonly" value="${goods.images}"  name="images" required  lay-verify="required" placeholder="封面图片" autocomplete="off" class="layui-input" style="width: calc(100% - 61px); display: inline-block;">
                <button type="button" class="layui-btn" id="images" >
                    <i class="layui-icon">&#xe67c;</i>
                </button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">价格</label>
            <div class="layui-input-block">
                <input type="number" name="price" required value="${goods.price}"  lay-verify="required" placeholder="价格" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">库存</label>
            <div class="layui-input-block">
                <input type="number" name="num" required value="${goods.num}" lay-verify="required" placeholder="库存" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序</label>
            <div class="layui-input-block">
                <input type="number" name="sort" required value="${goods.sort}" lay-verify="required" placeholder="排序" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">简介</label>
            <div class="layui-input-block">
                <textarea name="description" required lay-verify="required" placeholder="请输入简介" class="layui-textarea">${goods.description}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品状态</label>
            <div class="layui-input-block">
                <input type="radio" name="status" ${(goods.status=="0")?string("checked" , "")} value="0" title="上架">
                <input type="radio" name="status" ${(goods.status=="1")?string("checked" , "")} value="1" title="下架">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品详情</label>
            <div class="layui-input-block">
                <input type="hidden" value="${goods.content.id}" name="content.id" />
                <textarea name="content"  id="content" class="layui-textarea" title="商品详情">
                    <#if goods.content??  >
                        ${goods.content.content}
                        <#else>

                    </#if>
                </textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="editGoods">立即提交</button>
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

        //文本配置
        layedit.set({
            uploadImage: {
                url: '/file/artUpload' //接口url
                ,type: 'post' //默认post
            }
        });

        var textContent = layedit.build('content');

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

        form.on('submit(editGoods)', function(data){
            data.field.content = layedit.getContent(textContent);

            console.log(data.field);

            $.ajax({
                url:"/goods/edit",
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