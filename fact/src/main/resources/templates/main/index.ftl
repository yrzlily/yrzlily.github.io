<@override name="title">管理员中心</@override>
<@override name="head">
<style>
    #content , .tac{height: 100%;}
    .header{padding: 0;}
    .main-header{padding: 0 60px;}
    .main-center{width: 100%; height: 100%; padding: 0;}
    iframe{height: calc(99.7% - 60px); width: 100%;}
    .aside{height: 100%;}
    .leftBox{height: 100%; width: 250px !important;}
    .menuLeft{height: 100%;}
    .tac .cols{height: 100%;}
</style>
</@override>
<@override name="app">
<el-container id="content">
    <el-aside class="leftBox">
        <el-row class="tac">
            <el-col :span="24" class="cols">
                <el-menu
                        class="menuLeft"
                        default-active="2"
                        class="el-menu-vertical-demo"
                        background-color="#545c64"
                        text-color="#fff"
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
                    </el-submenu>
                </el-menu>
            </el-col>
        </el-row>
    </el-aside>
    <el-main class="main-center">
        <el-header>

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
                router(data){
                   this.$refs.windowBox.setAttribute('src' , data.route);
                }
            }
        });
    </script>
</@override>
<@extends name="/common/base.ftl" />