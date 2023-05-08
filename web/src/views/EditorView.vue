<template>
  <a-layout>
    <editor-sidebar/>
    <a-layout-content style="margin: 16px 16px">
      <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
        <router-view/>
      </div>
      <the-footer/>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, ref, onMounted} from 'vue';
import EditorSidebar from '@/components/editor-sidebar.vue'; // @ is an alias to /src
import TheFooter from '@/components/the-footer.vue'; // @ is an alias to /src
import axios from "axios";

export default defineComponent({
  name: 'EditorView',
  components: {
    EditorSidebar,
    TheFooter
  },
  setup() {
    console.log("main editor setup");
  }
});
</script>
