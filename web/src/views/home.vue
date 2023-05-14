<template>
  <a-layout>
    <a-layout-content style="margin: 16px 16px">
      <a-typography-title :level="1" style="margin-top: 20px">欢迎来到大湖洲</a-typography-title>
    </a-layout-content>
    <the-footer/>
  </a-layout>
</template>

<script lang="ts">

import {defineComponent, ref} from 'vue';
import TheHeader from '@/components/the-header.vue';
import TheFooter from '@/components/the-footer.vue';

export default defineComponent({
  components: {
    TheHeader,
    TheFooter
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
