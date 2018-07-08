<@override name="title">管理员中心</@override>
<@override name="head">
<style>
    #content , .tac{height: 100%;}
    .header{padding: 0;}
    .main-header{padding: 0 60px;}
    .main-center{width: 100%; height: 100%; padding: 0;}
<<<<<<< HEAD
    iframe{height: calc(99.7% - 60px); width: 100%;}
    .aside{height: 100%;}
    .leftBox{height: 100%; width: 250px !important;}
=======
    iframe{height: 99%; width: 100%;}
    .aside{height: 100%;}
    .leftBox{height: 100%;}
>>>>>>> e1c0ec91b72d8f6829402a71d79f11ff6662bd90
    .menuLeft{height: 100%;}
    .tac .cols{height: 100%;}
</style>
</@override>
<@override name="app">
<el-container id="content">
    <el-aside class="leftBox">
        <el-row class="tac">
<<<<<<< HEAD
            <el-col :span="24" class="cols">
=======
            <el-col :span="12" class="cols">
>>>>>>> e1c0ec91b72d8f6829402a71d79f11ff6662bd90
                <el-menu
                        class="menuLeft"
                        default-active="2"
                        class="el-menu-vertical-demo"
<<<<<<< HEAD
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
=======
                        @open="handleOpen"
                        @close="handleClose"
                        background-color="#545c64"
                        text-color="#fff"
                        active-text-color="#ffd04b">
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>导航一</span>
                        </template>
                        <el-menu-item-group>
                            <template slot="title">分组一</template>
                            <el-menu-item index="1-1">选项1</el-menu-item>
                            <el-menu-item index="1-2">选项2</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group title="分组2">
                            <el-menu-item index="1-3">选项3</el-menu-item>
                        </el-menu-item-group>
                        <el-submenu index="1-4">
                            <template slot="title">选项4</template>
                            <el-menu-item index="1-4-1">选项1</el-menu-item>
                        </el-submenu>
                    </el-submenu>
                    <el-menu-item index="2">
                        <i class="el-icon-menu"></i>
                        <span slot="title">导航二</span>
                    </el-menu-item>
                    <el-menu-item index="3" disabled>
                        <i class="el-icon-document"></i>
                        <span slot="title">导航三</span>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <i class="el-icon-setting"></i>
                        <span slot="title">导航四</span>
                    </el-menu-item>
>>>>>>> e1c0ec91b72d8f6829402a71d79f11ff6662bd90
                </el-menu>
            </el-col>
        </el-row>
    </el-aside>
<<<<<<< HEAD
    <el-main class="main-center">
        <el-header>

        </el-header>
        <iframe src="" ref="windowBox"></iframe>
=======

    <el-main class="main-center">
        <iframe id="window-box" src=""></iframe>
>>>>>>> e1c0ec91b72d8f6829402a71d79f11ff6662bd90
    </el-main>
</el-container>
</@override>
<@override name="script">
    <script>
        var app = new Vue({
            el:"#app",
<<<<<<< HEAD
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
=======
            data(){
                return {
                    index:1
                }
            },
            methods: {
                handleOpen(key, keyPath) {
                    console.log(key, keyPath);
                },
                handleClose(key, keyPath) {
                    console.log(key, keyPath);
>>>>>>> e1c0ec91b72d8f6829402a71d79f11ff6662bd90
                }
            }
        });
    </script>
</@override>
<@extends name="/common/base.ftl" />