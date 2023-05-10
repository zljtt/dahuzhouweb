<template>
  <a-layout-header style="position: fixed; width: 100%; z-index: 9999">
    <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="horizontal"
        style="width: 50%; float: right; max-height: 64px"
    >
      <router-link to="/home" style="width: 40%">
        <a-menu-item key="home" style="display: flex; align-items: center; justify-content: center">
          <span style="font-size: 1.5em">大湖洲</span>
        </a-menu-item>
      </router-link>
      <router-link to="/home" style="width: 40%">
        <a-menu-item key="wiki" style="float: right">WIKI</a-menu-item>
      </router-link>
      <router-link to="/editor" style="width: 40%">
        <a-menu-item key="editor" style="float: right">编辑器</a-menu-item>
      </router-link>
      <router-link to="/about" style="width: 40%">
        <a-menu-item key="about" style="float: right">关于我们</a-menu-item>
      </router-link>
      <router-link to="/home" style="width: 40%">
        <a-menu-item key="contact" style="float: right">联系我们</a-menu-item>
      </router-link>
      <router-link to="/home" style="width: 40%">
        <a-menu-item key="login" style="float: right">登录</a-menu-item>
      </router-link>
    </a-menu>
  </a-layout-header>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import axios from "axios";

export default defineComponent({
  name: 'user-header',
  props: {
    selected: {
      type: String,
      required: true,
      default: "home"
    }
  },
  setup(props) {
    console.log("setup");
    return {
      selectedKeys: ref<string[]>([props.selected]),
    }
  }
});
</script>