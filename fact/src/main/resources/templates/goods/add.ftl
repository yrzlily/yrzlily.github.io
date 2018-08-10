<@override name="title">添加商品</@override>
<@override name="head">
<style>

</style>
</@override>
<@override name="app">
<div class="layui-fluid">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">标题</label>
            <div class="layui-input-block">
                <input type="text" name="name" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">封面图片</label>
            <div class="layui-input-block">
                <input type="text" readonly="readonly"  name="images" required  lay-verify="required" placeholder="封面图片" autocomplete="off" class="layui-input" style="width: calc(100% - 61px); display: inline-block;">
                <button type="button" class="layui-btn" id="images" >
                    <i class="layui-icon">&#xe67c;</i>
                </button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品分类</label>
            <div class="layui-input-block">
                <input type="hidden" name="cats" required  lay-verify="required" placeholder="分类"  class="layui-input">
                <ul style="padding: 1%;" id="typeList"></ul>
                <div class="layui-form-mid layui-word-aux">（若不操作则默认分类为顶级）</div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">价格</label>
            <div class="layui-input-block">
                <input type="text" name="price" required  lay-verify="required" placeholder="价格" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">库存</label>
            <div class="layui-input-block">
                <input type="number" name="num" required  lay-verify="required" placeholder="库存" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序</label>
            <div class="layui-input-block">
                <input type="number" name="sort" required  lay-verify="required" placeholder="排序" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">简介</label>
            <div class="layui-input-block">
                <textarea name="description" placeholder="请输入简介" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品详情</label>
            <div class="layui-input-block">
                <textarea name="content" id="content" class="layui-textarea" title="商品详情"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="addGoods">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</@override>
<@override name="script">
<script>
    var fin;

    layui.use(['tree' ,'form' , 'upload' , 'layedit'], function() {
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

        $.ajax({
            url:'/type/tree',
            type:'post',
            success:function (data) {

                fin = setTree(data.data);

                console.log(fin);

                layui.tree({
                    elem: '#typeList' //传入元素选择器
                    ,nodes: [{
                        id:0
                        ,name: '顶部'
                        ,children: fin
                    }]
                    ,click: function(node){

                        $("input[name='cats']").val(node.id);
                    }
                });

            }
        });

        form.on('submit(addGoods)', function(data){
            data.field.content = layedit.getContent(textContent);

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


        function setTree(obj) {
            for (var i = 0 ; i < obj.length ; i++){
                obj[i].name = obj[i].typeName;

                if(obj[i].children){
                    setTree(obj[i].children)
                }
            }

            return obj;
        }

    });
</script>
</@override>
<@extends name="/common/base.ftl" />