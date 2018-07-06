<@override name="title">管理员中心</@override>
<@override name="head">
<style>
    #content , .tac{height: 100%;}
    .header{padding: 0;}
    .main-header{padding: 0 60px;}
    .main-center{width: 100%; height: 100%; padding: 0;}
    iframe{height: 99%; width: 100%;}
    .aside{height: 100%;}
    .leftBox{height: 100%;}
    .menuLeft{height: 100%;}
    .tac .cols{height: 100%;}
</style>
</@override>
<@override name="app">
<el-container id="content">
    <el-aside class="leftBox">
        <el-row class="tac">
            <el-col :span="12" class="cols">
                <el-menu
                        class="menuLeft"
                        default-active="2"
                        class="el-menu-vertical-demo"
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
                </el-menu>
            </el-col>
        </el-row>
    </el-aside>

    <el-main class="main-center">
        <iframe id="window-box" src=""></iframe>
    </el-main>
</el-container>
</@override>
<@override name="script">
    <script>
        var app = new Vue({
            el:"#app",
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
                }
            }
        });
    </script>
</@override>
<@extends name="/common/base.ftl" />