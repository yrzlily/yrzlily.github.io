<@override name="title">导航设置</@override>
<@override name="head">
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
</@override>
<@override name="app">
<el-container>
    <el-main>
        <el-row :gutter="0">
            <el-col :span="6"  style="float: left;">
                <el-tabs type="border-card">
                    <el-tab-pane label="菜单列表">
                        <div class="grid-content bg-purple">
                            <el-tree
                                    default-expand-all
                                    draggable
                                    :highlight-current = true
                                    :data="data"
                                    :props="defaultProps"
                                    :allow-drop="allowDrop"
                                    :expand-on-click-node="false"
                                    @node-click="handleNodeClick"
                                    @node-drop="handleDrop">
                            </el-tree>
                        </div>
                    </el-tab-pane>
                </el-tabs>
            </el-col>
            <el-col :span="17" style="float: right;">
                <div class="grid-content bg-purple">
                    <el-tabs type="border-card">
                        <el-tab-pane label="添加">
                            <el-form
                                    :label-position="labelPosition"
                                    ref="navValidateForm"
                                    label-width="80px"
                                    :model="navValidateForm">
                                <el-form-item label="导航名称" prop="name" >
                                    <el-input v-model="navValidateForm.name"></el-input>
                                </el-form-item>
                                <el-form-item label="导航图标" prop="images">
                                    <el-input v-model="navValidateForm.images"></el-input>
                                </el-form-item>
                                <el-form-item label="导航地址" prop="url">
                                    <el-input v-model="navValidateForm.url"></el-input>
                                </el-form-item>
                                <el-form-item
                                        label="导航排序"
                                        prop="sort">
                                    <el-input type="number" v-model="navValidateForm.sort"></el-input>
                                </el-form-item>
                                <el-form-item label="上级导航" prop="parent_name">
                                    <el-input v-model="navValidateForm.parent_name" placeholder="请输入内容"  :disabled="true"></el-input>
                                </el-form-item>
                                <el-form-item label="上级id" prop="parent_id" hidden>
                                    <el-input v-model="navValidateForm.parent_id" type="hidden"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary" @click="submitForm('navValidateForm')">添加</el-button>
                                    <el-button @click="resetForm('navValidateForm')">重置</el-button>
                                </el-form-item>
                            </el-form>
                        </el-tab-pane>
                        <el-tab-pane label="修改">
                            <el-form :label-position="labelPosition" ref="editValidateForm" label-width="80px" :model="editValidateForm">
                                <el-form-item label="导航名称" prop="name" >
                                    <el-input v-model="editValidateForm.name"></el-input>
                                </el-form-item>
                                <el-form-item label="导航图标" prop="images">
                                    <el-input v-model="editValidateForm.images"></el-input>
                                </el-form-item>
                                <el-form-item label="导航地址" prop="url">
                                    <el-input v-model="editValidateForm.url"></el-input>
                                </el-form-item>
                                <el-form-item label="导航排序" prop="sort">
                                    <el-input type="number" v-model="editValidateForm.sort"></el-input>
                                </el-form-item>
                                <el-form-item label="上级导航" prop="parent_name">
                                    <el-input v-model="editValidateForm.parent_name" placeholder="请输入内容"  :disabled="true"></el-input>
                                </el-form-item>
                                <el-form-item label="上级id" prop="parent_id" hidden>
                                    <el-input v-model="editValidateForm.parent_id" type="hidden"></el-input>
                                </el-form-item>
                                <el-form-item label="id" prop="id" hidden>
                                    <el-input v-model="editValidateForm.id" type="hidden"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary" @click="editForm('editValidateForm')">修改</el-button>
                                    <el-button type="danger" @click="delForm('editValidateForm')">删除</el-button>
                                </el-form-item>
                            </el-form>
                        </el-tab-pane>
                    </el-tabs>
                </div>
            </el-col>
        </el-row>
    </el-main>
</el-container>
</@override>
<@override name="script">
<script>
    var app = new Vue({
        el: '#app',
        data() {
            return {
                data: [
                    {
                        id:0,
                        name:'顶部菜单',
                        nav:'',
                        parent_id:0
                    }
                ],
                defaultProps: {
                    children: 'nav',
                    label: 'name'
                },
                labelPosition: 'right',
                navValidateForm: {
                    name: '',
                    images: '',
                    url: '',
                    sort: '',
                    parent_name:'顶部菜单',
                    parent_id: 0
                },
                editValidateForm:{
                    id:'',
                    name: '',
                    images: '',
                    url: '',
                    sort: '',
                    parent_name:'',
                    parent_id: 0
                }
            };
        },
        mounted :function () {
            var that = this;
            axios.get("/nav/parentId/0").then(
                    function (response) {
                        that.data[0].nav = response.data.data[0];
                    }
            )
        },
        methods: {
            handleNodeClick(data , node) {
                //添加模块
                this.navValidateForm.parent_id = data.id;
                this.navValidateForm.parent_name = data.name;
                //编辑模块
                this.editValidateForm = data;
                this.editValidateForm.parent_id = data.parentId;
                this.editValidateForm.parent_name = node.parent.data.name;
            },
            editForm(formName){
                var that = this;
                this.$refs[formName].validate(function(valid)  {
                    if (valid) {
                        axios.post('/nav/edit',{
                            id:that.editValidateForm.id,
                            name:that.editValidateForm.name,
                            images:that.editValidateForm.images,
                            url:that.editValidateForm.url,
                            sort:that.editValidateForm.sort,
                            parentId:that.editValidateForm.parent_id
                        }).then(function (value) {
                            console.log(value.data);
                            if(value.data.code === -1 || value.data.code === -101){
                                that.$message.error(value.data.msg);
                            }else{
                                that.$message({
                                    message: value.data.msg,
                                    type: 'success',
                                    onClose:function () {
                                        location.reload();
                                    }
                                });
                            }
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            submitForm(formName) {
                var that = this;
                this.$refs[formName].validate(function(valid)  {
                    if (valid) {
                        axios.post('/nav/add',{
                            name:that.navValidateForm.name,
                            images:that.navValidateForm.images,
                            url:that.navValidateForm.url,
                            sort:that.navValidateForm.sort,
                            parentId:that.navValidateForm.parent_id
                        }).then(function (value) {
                            console.log(value.data);
                            if(value.data.code === -1 || value.data.code === -101){
                                that.$message.error(value.data.msg);
                            }else{
                                that.$message({
                                    message: value.data.msg,
                                    type: 'success',
                                    onClose:function () {
                                        location.reload();
                                    }
                                });
                            }
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            delForm(formName){
                var that = this;
                console.log(that.editValidateForm.id);
                this.$refs[formName].validate(function(valid)  {
                    if(valid){
                        axios.post('/nav/del',{
                            id:that.editValidateForm.id
                        }).then(function (value) {

                            if(value.data.code === -1 || value.data.code === -101){
                                that.$message.error(value.data.msg);
                            }else{
                                that.$message({
                                    message: value.data.msg,
                                    type: 'success',
                                    onClose:function () {
                                        location.reload();
                                    }
                                });
                            }
                        });
                    }else{
                        console.log('error submit!!');
                        return false;
                    }
                });

            },
            handleDrop(draggingNode, dropNode, dropType, ev) {
                var data = {
                    name:draggingNode.data.name,
                    url:draggingNode.data.url,
                    id:draggingNode.data.id,
                    parentId:'',
                    sort:draggingNode.data.sort
                };

                switch (dropType){
                    case 'before':
                        data.parentId = dropNode.data.parentId;
                        data.sort = dropNode.data.sort===0?0:dropNode.data.sort-1;
                        break;
                    case  'after':
                        data.parentId = dropNode.data.parentId;
                        data.sort = dropNode.data.sort===dropNode.data.sort+1;
                        break;
                    case  'inner':
                        data.parentId = dropNode.data.id;
                        break;
                }
                console.log(data);
                axios.post('/nav/move',{
                    url:data.url,
                    name:data.name,
                    id:data.id,
                    parentId:data.parentId,
                    sort:data.sort
                }).then(function (value) {
                    console.log(value);
                })
            },
            allowDrop(draggingNode, dropNode, type) {
                if(dropNode.data.id === 0 && type ==='prev' ){
                    return type !== 'prev';
                }else if(dropNode.data.id === 0 && type ==='next' ){
                    return type !== 'next';
                }else{
                    return true;
                }
            }
        }
    });
</script>
</@override>
<@extends name="/common/base.ftl" />