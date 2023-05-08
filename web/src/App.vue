<template>
  <a-layout style="min-height: 100vh">
    <template>
      <editor-sidebar/>
      <a-layout-content style="margin: 16px 16px">
        <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
          <router-view/>
        </div>
        <the-footer/>
      </a-layout-content>
    </template>
    <router-view/>
  </a-layout>
</template>

<script lang="ts">

import {defineComponent, ref} from 'vue';

export default defineComponent({
  components: {},
  data() {
    return {
      collapsed: ref<boolean>(false),
      selectedKeys: ref<string[]>(['1']),
    };
  },
});
</script>

<style>
#components-layout-demo-side .logo {
  height: 32px;
  margin: 16px;
  background: rgba(255, 255, 255, 0.3);
}

.site-layout .site-layout-background {
  background: #fff;
}

[data-theme='dark'] .site-layout .site-layout-background {
  background: #141414;
}

</style>
