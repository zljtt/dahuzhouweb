<template>
  <a-layout style="min-height: 100vh">
    <the-header selected=""/>
    <a-layout-content style="margin-top: 64px">
      <router-view/>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">

import {defineComponent, ref} from 'vue';
import TheHeader from '@/components/the-header.vue';

export default defineComponent({
  components: {
    TheHeader
  },
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
