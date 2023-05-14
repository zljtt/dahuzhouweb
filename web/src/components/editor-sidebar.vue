<template>
  <a-layout-sider v-model:collapsed="collapsed" style="margin-top: -100px">
    <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline" @click="toggleClick"
            style="margin-top: 100px; height: 100vh; width: 100%;">
      <a-menu-item key='user'>
        <user-outlined/>
        <span>USER</span>
      </a-menu-item>
      <a-menu-item key='server'>
        <desktop-outlined/>
        <span>Server</span>
      </a-menu-item>
      <a-sub-menu key="file">
        <template #title>
          <span>
            <file-outlined/>
          </span>
        </template>
        <a-menu-item key='texture'>Texture</a-menu-item>
        <a-menu-item key='dialog'>Dialog</a-menu-item>
        <a-menu-item key='quest'>Quest</a-menu-item>
        <a-menu-item key='wiki'>Wiki</a-menu-item>
      </a-sub-menu>
    </a-menu>
  </a-layout-sider>
</template>
<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import {DesktopOutlined, FileOutlined, PieChartOutlined, TeamOutlined, UserOutlined} from "@ant-design/icons-vue";
import axios from "axios/index";


export default defineComponent({
  name: 'editor-sidebar',
  components: {
    PieChartOutlined,
    DesktopOutlined,
    UserOutlined,
    TeamOutlined,
    FileOutlined,
  },
  data() {
    return {
      collapsed: ref<boolean>(true),
      selectedKeys: ref()
    };
  },
  methods: {
    toggleClick(content: any) {
      switch (content.key) {
        case 'server':
          this.$router.push('/editor/server');
          break;
        case 'file':
          this.$router.push('/editor/file');
          break;
        case 'dialog':
          this.$router.push('/editor/dialog');
          break;
        case 'wiki':
          this.$router.push('/editor/wiki');
          break;
      }
      // Handle the menu item click event here
    }
  }

});


</script>

