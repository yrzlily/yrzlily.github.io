<@override name="title">管理员登录</@override>
<@override name="head">
    <style>
        html , body{padding: 0; margin: 0; height: 100%;}
        .login_box{width: 600px; margin: 0 auto; margin-top: 10%; box-shadow: 0 0 5px #cccccc; text-align: center;}
        .login_box h1{font-family: 'Hiragino Sans GB'; padding-bottom: 25px;}
    </style>
</@override>
<@override name="app">
    <el-main class="login_box">
        <h1>Fact后台管理系统</h1>
        <el-form :model="loginForm" status-icon :rules="loginRules" ref="loginForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户名" prop="username">
                <el-input type="username" v-model="loginForm.username" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
                <el-input type="password" v-model="loginForm.pass" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button style="float: left;" type="primary" @click="submitForm('loginForm')">登陆</el-button>
            </el-form-item>
        </el-form>
    </el-main>
</@override>
<@override name="script">
<script>
    var app = new Vue({
        el: "#app",
        data(){
            var validateUsername = function(rule , value , callback)  {
                if(value === ''){
                    callback(new Error('请输入用户名'));
                }else{
                    callback();
                }
            };
            var validatePass = function(rule, value, callback){
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    callback();
                }
            };
            return {
                loginForm: {
                    username: '',
                    pass: ''
                },
                loginRules: {
                    username: [
                        { validator: validateUsername, trigger: 'blur' }
                    ],
                    pass: [
                        { validator: validatePass, trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                var that = this;
                this.$refs[formName].validate(function(valid) {
                    if (valid) {
                        axios.post('/login/check' , {
                            username:that.loginForm.username,
                            password:that.loginForm.pass
                        }).then(function (response) {
                            console.log(response.data);

                            if(response.data.code === 0){
                                location.href = '/main/index';
                            }

                        }).catch(function (error) {

                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
    });
</script>
</@override>
<@extends name="/common/base.ftl" />