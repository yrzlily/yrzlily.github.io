<@override name="title">管理员中心</@override>
<@override name="head">
<style>
    #content , .tac{height: 100%;}
    .header{padding: 0;}
    .main-header{padding: 0 60px;}
    .main-center{width: 100%; height: 100%; padding: 0; overflow: hidden;}
    iframe{height: calc(99.7% - 60px); width: 100%;}
    .aside{height: 100%;}
    .leftBox{height: 100%; width: 250px !important;}
    iframe{height: 99%; width: 100%;}
    .aside{height: 100%;}
    .leftBox{height: 100%; background-color: #393d49;}
    .menuLeft{height: calc(100% - 60px); border-right: 0;}
    .tac .cols{height: 100%;}
    .el-header{padding: 0;background-color: #393D49;}
</style>
</@override>
<@override name="app">
<el-container id="content">
    <el-aside class="leftBox">
        <el-row class="tac">
            <el-col :span="24" class="cols">
                <div class="logo" style="width: 100%; height: 60px; overflow: hidden; text-align: center; line-height: 60px; font-size: 30px; color: #fff;">
                    Fact
                </div>
                <el-menu
                        class="menuLeft"
                        default-active="2"
                        class="el-menu-vertical-demo"
                        background-color="#393d49"
                        text-color="#ffffffb3"
                        active-text-color="#ffd04b">
                    <el-submenu v-for="item in menu" :key="item.id" :index="item.name" :data="item" :route="item.url">
                        <template slot="title" >
                            <i :class="item.images"></i>
                            <span>{{item.name}}</span>
                        </template>
                        <label >
                            <el-menu-item
                                    v-for="val in item.nav"
                                    :key="val.id"
                                    :route="val.url"
                                    @click="router"
                                    :index="val.name">
                                {{val.name}}
                            </el-menu-item>
                        </label>
                </el-menu>
            </el-col>
        </el-row>
    </el-aside>
    <el-main class="main-center">
        <el-header>
            <ul class="layui-nav layui-layout-right" >
                <#--<li class="layui-nav-item">-->
                    <#--<a href="">控制台<span class="layui-badge">9</span></a>-->
                <#--</li>-->
                <li class="layui-nav-item">
                    <a href="/user/logout">退出</a>
                </li>
                <li class="layui-nav-item">
                    <a href=""><img src="${user.avatar}" class="layui-nav-img">${user.username}</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">修改信息</a></dd>
                        <dd><a href="javascript:;">安全管理</a></dd>
                        <dd><a href="javascript:;">退了</a></dd>
                    </dl>
                </li>
            </ul>
        </el-header>
        <iframe src="" ref="windowBox"></iframe>
    </el-main>
</el-container>
</@override>
<@override name="script">
    <script>

        var app = new Vue({
            el:"#app",
            data:{
                menu : {}
            },
            mounted :function () {
                var that = this;
                axios.get("/nav/parentId/0").then(
                    function (response) {
                        axios.get("/nav/parentId/0").then(
                            function (response) {
                                that.menu = response.data.data[0];
                            }
                        )
                    }
                )
            },
            methods: {
                router(data) {
                    this.$refs.windowBox.setAttribute('src', data.route);
                    localStorage.setItem("src" , data.route);
                    console.log(localStorage.getItem("src"));
                }
            }
        });

        var href = localStorage.getItem("src");
        if(href){
            app.$refs.windowBox.setAttribute('src', href);
        }

    </script>
</@override>
<@extends name="/common/base.ftl" />